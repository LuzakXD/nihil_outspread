package com.lzk.nihiloutspread.entity.ai;

import java.util.EnumSet;
import java.util.List;

import com.lzk.nihiloutspread.entity.MurkletEntity;
import com.lzk.nihiloutspread.entity.PixieEntity;
import com.lzk.nihiloutspread.init.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;

public class SeekBushLikeGoal extends Goal {

    private static final int SEARCH_RADIUS = 32;
    private static final int CHEW_TICKS = 60;

    private final PixieEntity mob;
    private BlockPos targetPos;
    private MurkletEntity targetMurklet;
    private int chewTimer;
    private int travelTimer;
    private int cooldown;

    public SeekBushLikeGoal(PixieEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.cooldown > 0) {
            this.cooldown--;
            return false;
        }
        if (hasVisibleMurkletNearby()) {
            return false;
        }
        if (this.mob.getRandom().nextInt(60) != 0) {
            return false;
        }
        return findBushLikeTarget();
    }

    @Override
    public void start() {
        this.chewTimer = 0;
        this.travelTimer = 0;
        moveToTarget();
    }

    private void moveToTarget() {
        this.mob.getNavigation().moveTo(
                this.targetPos.getX() + 0.5,
                this.targetPos.getY() + 0.8,
                this.targetPos.getZ() + 0.5,
                1.0);
    }

    private boolean hasVisibleMurkletNearby() {
        return !this.mob.level().getEntitiesOfClass(
                MurkletEntity.class,
                this.mob.getBoundingBox().inflate(10.0D),
                m -> m.getHideState() == MurkletEntity.HIDE_STATE_NONE
                        || m.getHideState() == MurkletEntity.HIDE_STATE_BITING)
                .isEmpty();
    }

    @Override
    public void tick() {
        if (this.targetPos == null) {
            return;
        }

        if ((this.targetMurklet != null && (!this.targetMurklet.isAlive()
                || (this.targetMurklet.getHideState() != MurkletEntity.HIDE_STATE_HIDDEN
                        && this.targetMurklet.getHideState() != MurkletEntity.HIDE_STATE_PEEKING)))
                || hasVisibleMurkletNearby()) {
            this.cooldown = 60;
            this.stop();
            return;
        }

        double tx = this.targetPos.getX() + 0.5;
        double ty = this.targetPos.getY() + 0.8;
        double tz = this.targetPos.getZ() + 0.5;

        this.mob.getLookControl().setLookAt(tx, ty, tz);

        double distSqr = this.mob.distanceToSqr(tx, ty, tz);

        if (distSqr < 1.0) {
            this.mob.getNavigation().stop();
            this.chewTimer++;
            if (this.chewTimer >= CHEW_TICKS) {
                this.cooldown = 200;
                this.stop();
            }
        } else {
            this.travelTimer++;
            if (this.travelTimer > 120) {
                this.cooldown = 60;
                this.stop();
                return;
            }
            if (this.mob.getNavigation().isDone()) {
                moveToTarget();
            }
        }
    }

    @Override
    public void stop() {
        this.chewTimer = 0;
        this.travelTimer = 0;
        this.targetPos = null;
        this.targetMurklet = null;
        this.mob.getNavigation().stop();
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetPos != null && this.cooldown == 0;
    }

    private boolean findBushLikeTarget() {
        BlockPos origin = this.mob.blockPosition();
        this.targetPos = null;
        this.targetMurklet = null;

        BlockPos bestBush = null;
        double bestBushDist = Double.MAX_VALUE;

        for (BlockPos pos : BlockPos.betweenClosed(
                origin.offset(-SEARCH_RADIUS, -10, -SEARCH_RADIUS),
                origin.offset(SEARCH_RADIUS, 6, SEARCH_RADIUS))) {
            if (!this.mob.level().getBlockState(pos).is(ModTags.Blocks.NIHIL_BUSHES)) {
                continue;
            }
            double dist = origin.distSqr(pos);
            if (dist < bestBushDist) {
                bestBushDist = dist;
                bestBush = pos.immutable();
            }
        }

        List<MurkletEntity> hidden = this.mob.level().getEntitiesOfClass(
                MurkletEntity.class,
                this.mob.getBoundingBox().inflate(SEARCH_RADIUS, 10.0D, SEARCH_RADIUS),
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
            if (bestMurkletDist <= bestBushDist * 1.3) {
                this.targetMurklet = bestMurklet;
                this.targetPos = bestMurklet.blockPosition();
            } else {
                this.targetPos = bestBush;
            }
            return true;
        }

        if (bestMurklet != null) {
            this.targetMurklet = bestMurklet;
            this.targetPos = bestMurklet.blockPosition();
            return true;
        }

        if (bestBush != null) {
            this.targetPos = bestBush;
            return true;
        }

        return false;
    }
}
