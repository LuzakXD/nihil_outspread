package com.lzk.nihiloutspread.blocks;

import com.lzk.nihiloutspread.init.ModTags;

import net.minecraft.core.BlockPos;
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

}
