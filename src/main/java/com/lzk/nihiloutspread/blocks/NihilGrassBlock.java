package com.lzk.nihiloutspread.blocks;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;

import com.lzk.nihiloutspread.init.ModBlocks;
import com.lzk.nihiloutspread.init.ModTags;

import net.minecraft.core.BlockPos;

public class NihilGrassBlock extends Block {

    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;

    public NihilGrassBlock(BlockBehaviour.Properties props) {
        super(props);

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(SNOWY, false));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        if (!level.isAreaLoaded(pos, 2)) {
            return;
        }

        BlockPos below = pos.below();

        if (level.getBlockState(below).is(ModTags.Blocks.CORRUPTIBLE_TO_NIHIL_DIRT)) {
            level.setBlockAndUpdate(below, ModBlocks.NIHIL_DIRT.get().defaultBlockState());
        }

        for (int i = 0; i < 4; i++) {
            BlockPos target = pos.offset(
                    random.nextInt(3) - 1,
                    random.nextInt(5) - 3,
                    random.nextInt(3) - 1);

            if (level.isOutsideBuildHeight(target)) {
                continue;
            }

            BlockState targetState = level.getBlockState(target);

            if (!targetState.is(ModTags.Blocks.CORRUPTIBLE_TO_NIHIL_GRASS)) {
                continue;
            }

            BlockState aboveState = level.getBlockState(target.above());

            if (!aboveState.is(BlockTags.REPLACEABLE) || !aboveState.getFluidState().isEmpty()) {
                continue;
            }

            level.setBlockAndUpdate(target, ModBlocks.NIHIL_GRASS_BLOCK.get().defaultBlockState());

            BlockPos belowTarget = target.below();
            if (level.getBlockState(belowTarget).is(ModTags.Blocks.CORRUPTIBLE_TO_NIHIL_DIRT)) {
                level.setBlockAndUpdate(belowTarget, ModBlocks.NIHIL_DIRT.get().defaultBlockState());
            }
        }
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 15;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SNOWY);
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            Direction direction,
            BlockState neighborState,
            LevelAccessor level,
            BlockPos pos,
            BlockPos neighborPos) {
        if (direction == Direction.UP) {
            boolean snowy = neighborState.is(Blocks.SNOW) || neighborState.is(Blocks.SNOW_BLOCK);

            return state.setValue(SNOWY, snowy);
        }

        return super.updateShape(
                state,
                direction,
                neighborState,
                level,
                pos,
                neighborPos);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        BlockState above = level.getBlockState(pos.above());

        boolean snowy = above.is(Blocks.SNOW) || above.is(Blocks.SNOW_BLOCK);

        return defaultBlockState()
                .setValue(SNOWY, snowy);
    }
}
