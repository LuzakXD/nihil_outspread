package com.lzk.nihiloutspread.blocks;

import net.neoforged.neoforge.common.util.TriState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class AbyssalMossBlock extends Block {

    public AbyssalMossBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    public TriState canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing,
            BlockState plantState) {
        return TriState.TRUE;
    }
}
