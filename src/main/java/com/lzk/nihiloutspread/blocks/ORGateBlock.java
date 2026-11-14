package com.lzk.nihiloutspread.blocks;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.state.BlockState;

public class ORGateBlock extends GateBlock {

    public static final MapCodec<ORGateBlock> CODEC = simpleCodec(ORGateBlock::new);

    @Override
    public MapCodec<ORGateBlock> codec() {
        return CODEC;
    }

    public ORGateBlock(Properties props) {
        super(props);
    }

    @Override
    protected boolean baseLogic(boolean a, boolean b) {
        return (a || b);
    }

}
