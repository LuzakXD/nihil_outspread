package com.lzk.nihiloutspread.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ANDGateBlock extends GateBlock {

    public ANDGateBlock(Properties props) {
        super(props);
    }

    @Override
    protected Boolean logic(BlockState state, boolean a, boolean b) {
        return (a && b) ^ state.getValue(INVERTED);
    }

    @Override
    protected boolean shouldTurnOn(Level level, BlockPos pos, BlockState state) {
        Direction back = state.getValue(FACING);
        int l = level.getSignal(pos.relative(back.getClockWise()), back.getClockWise());
        int r = level.getSignal(pos.relative(back.getCounterClockWise()), back.getCounterClockWise());
        return ((l > 0) && (r > 0)) ^ state.getValue(INVERTED);
    }

}
