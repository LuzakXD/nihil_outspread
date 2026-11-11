package com.lzk.nihiloutspread.blocks;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class AbyssalStoneBlock extends Block {

	public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;

	public AbyssalStoneBlock(BlockBehaviour.Properties props) {
		super(props);

		this.registerDefaultState(
				this.stateDefinition.any().setValue(SNOWY, false));

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