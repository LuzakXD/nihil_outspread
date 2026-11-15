package com.lzk.nihiloutspread.blocks;

import com.mojang.serialization.MapCodec;

public class XORGateBlock extends GateBlock {

    public static final MapCodec<XORGateBlock> CODEC = simpleCodec(XORGateBlock::new);

    @Override
    public MapCodec<XORGateBlock> codec() {
        return CODEC;
    }

    public XORGateBlock(Properties props) {
        super(props);
    }

    @Override
    protected boolean baseLogic(boolean a, boolean b) {
        return (a ^ b);
    }

}
