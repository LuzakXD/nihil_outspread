package com.lzk.nihiloutspread.blocks;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import com.lzk.nihiloutspread.init.ModBlocks;
import com.lzk.nihiloutspread.init.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class AbyssalMossyStoneBlock extends AbyssalStoneBlock {

	public static final EnumProperty<Direction> VERTICAL_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;

	public AbyssalMossyStoneBlock(BlockBehaviour.Properties props) {
		super(props);

		this.registerDefaultState(
				this.stateDefinition.any()
						.setValue(VERTICAL_DIRECTION, Direction.UP)
						.setValue(SNOWY, false));

	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(VERTICAL_DIRECTION, SNOWY);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction dir = context.getClickedFace() == Direction.DOWN
				? Direction.DOWN
				: Direction.UP;

		BlockPos pos = context.getClickedPos();
		BlockState above = context.getLevel().getBlockState(pos.above());
		boolean snowy = above.is(Blocks.SNOW) || above.is(Blocks.SNOW_BLOCK);

		return defaultBlockState()
				.setValue(VERTICAL_DIRECTION, dir)
				.setValue(SNOWY, snowy);
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
	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

		if (!level.isAreaLoaded(pos, 2)) {
			return;
		}

		Direction facing = state.getValue(VERTICAL_DIRECTION);
		BlockPos exposedPos = facing == Direction.UP ? pos.above() : pos.below();
		BlockState exposedState = level.getBlockState(exposedPos);

		if (exposedState.isSolidRender(level, exposedPos)) {
			level.setBlockAndUpdate(pos, ModBlocks.ABYSSAL_STONE.get().defaultBlockState());
			return;
		}

		for (int i = 0; i < 4; i++) {
			BlockPos target = pos.offset(
					random.nextInt(3) - 1,
					random.nextInt(5) - 4,
					random.nextInt(3) - 1);

			if (level.isOutsideBuildHeight(target)) {
				continue;
			}

			BlockState targetState = level.getBlockState(target);

			if (!targetState.is(ModBlocks.ABYSSAL_STONE)) {
				continue;
			}

			BlockState aboveState = level.getBlockState(target.above());
			boolean snowy = aboveState.is(Blocks.SNOW) || aboveState.is(Blocks.SNOW_BLOCK);

			if (state.getValue(VERTICAL_DIRECTION) == Direction.UP) {
				if (!aboveState.is(BlockTags.REPLACEABLE) || !aboveState.getFluidState().isEmpty()) {
					continue;
				}

				level.setBlockAndUpdate(target, ModBlocks.ABYSSAL_MOSSY_STONE.get().defaultBlockState()
						.setValue(VERTICAL_DIRECTION, Direction.UP)
						.setValue(SNOWY, snowy));
			} else {
				BlockState belowState = level.getBlockState(target.below());

				if (!belowState.is(BlockTags.REPLACEABLE) || !belowState.getFluidState().isEmpty()) {
					continue;
				}

				level.setBlockAndUpdate(target, ModBlocks.ABYSSAL_MOSSY_STONE.get().defaultBlockState()
						.setValue(VERTICAL_DIRECTION, Direction.DOWN)
						.setValue(SNOWY, snowy));
			}
		}
	}
}