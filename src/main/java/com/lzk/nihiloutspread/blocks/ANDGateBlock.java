package com.lzk.nihiloutspread.blocks;

import com.mojang.serialization.MapCodec;

public class ANDGateBlock extends GateBlock {

    public static final MapCodec<ANDGateBlock> CODEC = simpleCodec(ANDGateBlock::new);

    @Override
    public MapCodec<ANDGateBlock> codec() {
        return CODEC;
    }

    public ANDGateBlock(Properties props) {
        super(props);
    }

    @Override
    protected boolean baseLogic(boolean a, boolean b) {
        return (a && b);
    }

}
