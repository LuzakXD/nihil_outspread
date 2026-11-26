package com.lzk.nihiloutspread.entity.ai;

import java.util.EnumSet;
import java.util.List;

import com.lzk.nihiloutspread.entity.MurkletEntity;
import com.lzk.nihiloutspread.entity.PixieEntity;
import com.lzk.nihiloutspread.init.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;

public class SeekBushLikeGoal extends Goal {

    private static final int SEARCH_RADIUS = 16;
    private static final int CHEW_TICKS = 60;

    private final PixieEntity mob;
    private BlockPos targetPos;
    private int chewTimer;
    private int cooldown;

    public SeekBushLikeGoal(PixieEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (this.cooldown > 0) {
            this.cooldown--;
            return false;
        }
        if (this.mob.getRandom().nextInt(80) != 0)
            return false;
        this.targetPos = findBushLikeTarget();
        return this.targetPos != null;
    }

    @Override
    public void start() {
        this.chewTimer = 0;
        this.mob.getNavigation().moveTo(
                this.targetPos.getX() + 0.5, this.targetPos.getY() + 1,
                this.targetPos.getZ() + 0.5, 1.0);
    }

    @Override
    public void tick() {
        if (this.targetPos == null)
            return;

        double distSqr = this.mob.distanceToSqr(
                this.targetPos.getX() + 0.5, this.targetPos.getY() + 1,
                this.targetPos.getZ() + 0.5);

        if (distSqr < 4.0) {
            this.mob.getNavigation().stop();
            this.chewTimer++;
            if (this.chewTimer >= CHEW_TICKS) {
                this.cooldown = 200;
                this.stop();
            }
        }
    }

    @Override
    public void stop() {
        this.chewTimer = 0;
        this.targetPos = null;
        this.mob.getNavigation().stop();
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetPos != null && this.cooldown == 0;
    }

    private BlockPos findBushLikeTarget() {
        BlockPos origin = this.mob.blockPosition();

        BlockPos bestBush = null;
        double bestBushDist = Double.MAX_VALUE;
        for (BlockPos pos : BlockPos.betweenClosed(
                origin.offset(-SEARCH_RADIUS, -2, -SEARCH_RADIUS),
                origin.offset(SEARCH_RADIUS, 2, SEARCH_RADIUS))) {
            if (!this.mob.level().getBlockState(pos).is(ModTags.Blocks.NIHIL_BUSHES))
                continue;
            double dist = origin.distSqr(pos);
            if (dist < bestBushDist) {
                bestBushDist = dist;
                bestBush = pos.immutable();
            }
        }

        List<MurkletEntity> hidden = this.mob.level().getEntitiesOfClass(
                MurkletEntity.class,
                this.mob.getBoundingBox().inflate(SEARCH_RADIUS),
                m -> m.getHideState() == MurkletEntity.HIDE_STATE_HIDDEN
                        || m.getHideState() == MurkletEntity.HIDE_STATE_PEEKING);

        MurkletEntity bestMurklet = null;
        double bestMurkletDist = Double.MAX_VALUE;
        for (MurkletEntity m : hidden) {
            double dist = origin.distSqr(m.blockPosition());
            if (dist < bestMurkletDist) {
                bestMurkletDist = dist;
                bestMurklet = m;
            }
        }

        if (bestMurklet != null && bestBush != null) {
            return bestMurkletDist <= bestBushDist * 1.3
                    ? bestMurklet.blockPosition()
                    : bestBush;
        }
        return bestMurklet != null ? bestMurklet.blockPosition() : bestBush;
    }
}
