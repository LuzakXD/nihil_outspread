package com.lzk.nihiloutspread.blocks;

import net.minecraft.world.level.block.state.BlockState;

import com.lzk.nihiloutspread.init.ModBlocks;
import com.lzk.nihiloutspread.init.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CheckerGrassBlock extends NihilGrassBlock {

    public CheckerGrassBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        if (!level.isAreaLoaded(pos, 2)) {
            return;
        }

        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);

        if (aboveState.isSolidRender(level, abovePos)) {
            level.setBlockAndUpdate(pos, ModBlocks.NIHIL_DIRT.get().defaultBlockState());
            return;
        }

        for (int i = 0; i < 4; i++) {
            BlockPos target = pos.offset(
                    random.nextInt(3) - 1,
                    random.nextInt(5) - 3,
                    random.nextInt(3) - 1);

            if (level.isOutsideBuildHeight(target)) {
                continue;
            }

            BlockState targetState = level.getBlockState(target);

            if (!targetState.is(ModBlocks.NIHIL_DIRT)) {
                continue;
            }

            BlockPos targetAbove = target.above();
            BlockState targetAboveState = level.getBlockState(targetAbove);

            if (targetAboveState.isSolidRender(level, targetAbove)) {
                continue;
            }

            if (!targetAboveState.is(BlockTags.REPLACEABLE) || !targetAboveState.getFluidState().isEmpty()) {
                continue;
            }

            boolean snowy = targetAboveState.is(Blocks.SNOW) || targetAboveState.is(Blocks.SNOW_BLOCK);

            level.setBlockAndUpdate(target, ModBlocks.CHECKER_GRASS_BLOCK.get().defaultBlockState()
                    .setValue(SNOWY, snowy));
        }
    }
}
