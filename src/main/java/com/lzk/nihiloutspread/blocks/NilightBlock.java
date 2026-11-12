package com.lzk.nihiloutspread.blocks;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
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

import com.lzk.nihiloutspread.init.ModTags;

import net.minecraft.core.BlockPos;

public class NilightBlock extends Block {

    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;
    public static final BooleanProperty MOSSY = BooleanProperty.create("mossy");
    public static final BooleanProperty DIRTY = BooleanProperty.create("dirty");

    public NilightBlock(BlockBehaviour.Properties props) {
        super(props.instrument(NoteBlockInstrument.BASEDRUM));

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(SNOWY, false)
                        .setValue(MOSSY, false)
                        .setValue(DIRTY, false));
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 15;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SNOWY, MOSSY, DIRTY);
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

            boolean mossy = neighborState.is(ModTags.Blocks.MOSSY_BLOCKS);

            boolean dirty = neighborState.is(ModTags.Blocks.DIRTY_BLOCKS);

            return state.setValue(SNOWY, snowy).setValue(MOSSY, mossy).setValue(DIRTY, dirty);
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
        boolean mossy = above.is(ModTags.Blocks.MOSSY_BLOCKS);
        boolean dirty = above.is(ModTags.Blocks.DIRTY_BLOCKS);

        return defaultBlockState()
                .setValue(SNOWY, snowy)
                .setValue(MOSSY, mossy)
                .setValue(DIRTY, dirty);
    }
}
