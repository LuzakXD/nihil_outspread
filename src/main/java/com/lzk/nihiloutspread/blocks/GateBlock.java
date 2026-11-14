package com.lzk.nihiloutspread.blocks;

import org.jetbrains.annotations.Nullable;

import com.lzk.nihiloutspread.init.ModSounds;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public abstract class GateBlock extends DiodeBlock {
    public static final BooleanProperty INPUT_A = BooleanProperty.create("input_a");
    public static final BooleanProperty INPUT_B = BooleanProperty.create("input_b");
    public static final BooleanProperty INVERTED = BooleanProperty.create("inverted");

    public GateBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(POWERED, false)
                .setValue(INPUT_A, false)
                .setValue(INPUT_B, false)
                .setValue(INVERTED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, INPUT_A, INPUT_B, INVERTED);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos,
            @Nullable Direction direction) {

        if (direction == null || !direction.getAxis().isHorizontal())
            return false;

        Direction front = state.getValue(FACING);
        Direction left = front.getClockWise();
        Direction right = front.getCounterClockWise();

        return direction == front || direction == left || direction == right;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected boolean shouldTurnOn(Level level, BlockPos pos, BlockState state) {
        Direction back = state.getValue(FACING);
        int l = level.getSignal(pos.relative(back.getClockWise()), back.getClockWise());
        int r = level.getSignal(pos.relative(back.getCounterClockWise()), back.getCounterClockWise());
        return logic(state, (l > 0), (r > 0));
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        if (!state.getValue(POWERED))
            return 0;
        return direction == state.getValue(FACING) ? 15 : 0;
    }

    @Override
    protected int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.getSignal(level, pos, direction);
    }

    @Override
    protected int getDelay(BlockState state) {
        return 2;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos fromPos,
            boolean isMoving) {
        if (!state.canSurvive(level, pos)) {
            super.neighborChanged(state, level, pos, neighborBlock, fromPos, isMoving);
            return;
        }
        if (!level.getBlockTicks().willTickThisTick(pos, this)) {
            level.scheduleTick(pos, this, this.getDelay(state));
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        }

        BlockState finalState = computeState(level, pos, state.cycle(INVERTED));

        float pitch = finalState.getValue(INVERTED) ? 0.55F : 0.5F;
        level.playSound(player, pos, ModSounds.HONEY_GATE_CLICK.get(), SoundSource.BLOCKS, 0.3F, pitch);

        boolean wasPowered = state.getValue(POWERED);
        level.setBlock(pos, finalState, 2);

        if (wasPowered != finalState.getValue(POWERED)) {
            this.updateNeighborsInFront(level, pos, finalState);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private BlockState computeState(Level level, BlockPos pos, BlockState state) {
        Direction back = state.getValue(FACING);
        boolean a = level.getSignal(pos.relative(back.getClockWise()), back.getClockWise()) > 0;
        boolean b = level.getSignal(pos.relative(back.getCounterClockWise()), back.getCounterClockWise()) > 0;
        boolean powered = logic(state, a, b);
        return state.setValue(INPUT_A, a).setValue(INPUT_B, b).setValue(POWERED, powered);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        return computeState(context.getLevel(), context.getClickedPos(), state);
    }

    private void refreshState(ServerLevel level, BlockPos pos, BlockState state) {
        boolean wasPowered = state.getValue(POWERED);
        BlockState newState = computeState(level, pos, state);

        if (newState != state) {
            level.setBlock(pos, newState, 2);
        }
        if (wasPowered != newState.getValue(POWERED)) {
            this.updateNeighborsInFront(level, pos, newState);
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        refreshState(level, pos, state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState,
            LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.DOWN && !this.canSurviveOn(level, facingPos, facingState)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    protected abstract boolean baseLogic(boolean a, boolean b);

    protected final boolean logic(BlockState state, boolean a, boolean b) {
        return baseLogic(a, b) ^ state.getValue(INVERTED);
    }

}
