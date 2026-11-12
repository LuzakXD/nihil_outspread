package com.lzk.nihiloutspread.blocks;

import net.minecraft.world.level.block.state.BlockState;

import com.lzk.nihiloutspread.init.ModBlocks;
import com.lzk.nihiloutspread.init.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
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

            BlockState aboveState = level.getBlockState(target.above());

            if (!aboveState.is(BlockTags.REPLACEABLE) || !aboveState.getFluidState().isEmpty()) {
                continue;
            }

            level.setBlockAndUpdate(target, ModBlocks.CHECKER_GRASS_BLOCK.get().defaultBlockState());

        }
    }
}
