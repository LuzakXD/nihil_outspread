package com.lzk.nihiloutspread.blocks;

import com.lzk.nihiloutspread.init.ModTags;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NihilSroutsBlock extends BushBlock {

    public static final MapCodec<NihilSroutsBlock> CODEC = simpleCodec(NihilSroutsBlock::new);
    protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0);

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public MapCodec<NihilSroutsBlock> codec() {
        return CODEC;
    }

    public NihilSroutsBlock(Properties props) {
        super(props);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(ModTags.Blocks.NIHIL_BUSHES_MAY_PLACE_ON);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos,
            Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos,
            Direction direction) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos,
            Direction direction) {
        return 100;
    }
}
