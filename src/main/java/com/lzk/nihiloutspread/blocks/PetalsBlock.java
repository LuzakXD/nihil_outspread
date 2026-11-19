package com.lzk.nihiloutspread.blocks;

import com.lzk.nihiloutspread.init.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PetalsBlock extends PinkPetalsBlock {

    public PetalsBlock(Properties props) {
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
