package com.lzk.nihiloutspread.entity.ai;

import com.lzk.nihiloutspread.entity.MurkletEntity;
import com.lzk.nihiloutspread.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class HideInBlockGoal extends Goal {

    private static final int DIG_TICKS = 20;
    private static final int HIDE_TICKS = 200;
    private static final int UNDIG_TICKS = 25;
    private static final int SEARCH_RADIUS = 16;

    private enum Phase {
        SEARCHING, MOVING, DIGGING, HIDING, UNDIGGING
    }

    private final MurkletEntity mob;
    private BlockPos targetPos;
    private int randomTimer;
    private Phase phase = Phase.SEARCHING;

    public HideInBlockGoal(MurkletEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        int state = this.mob.getHideState();
        if (state != MurkletEntity.HIDE_STATE_NONE) {
            this.targetPos = this.mob.getHideTarget() != null
                    ? this.mob.getHideTarget().pos()
                    : this.mob.blockPosition();
            this.phase = switch (state) {
                case MurkletEntity.HIDE_STATE_DIGGING -> Phase.DIGGING;
                case MurkletEntity.HIDE_STATE_HIDDEN -> Phase.HIDING;
                case MurkletEntity.HIDE_STATE_UNDIGGING -> Phase.UNDIGGING;
                default -> Phase.SEARCHING;
            };
            return true;
        }
        if (this.mob.getRandom().nextInt(250) != 0)
            return false;
        this.targetPos = findHidingSpot();
        if (this.targetPos != null) {
            this.mob.setHideTarget(GlobalPos.of(this.mob.level().dimension(), this.targetPos));
        }
        return this.targetPos != null;
    }

    @Override
    public void start() {
        int state = this.mob.getHideState();
        if (state != MurkletEntity.HIDE_STATE_NONE) {
            this.mob.setDigTimer(0);
            return;
        }
        this.randomTimer = mob.getRandom().nextInt(200);
        this.phase = Phase.MOVING;
        this.mob.setDigTimer(0);
        this.mob.getNavigation().moveTo(
                targetPos.getX() + 0.5,
                targetPos.getY() + 1,
                targetPos.getZ() + 0.5,
                1.0D);

    }

    @Override
    public void tick() {
        switch (phase) {
            case MOVING -> {
                double cx = targetPos.getX() + 0.5;
                double cy = targetPos.getY() + 1.0;
                double cz = targetPos.getZ() + 0.5;
                double distSqr = this.mob.distanceToSqr(cx, cy, cz);

                if (distSqr > 4.0) {
                    if (this.mob.getNavigation().isDone()) {
                        this.mob.getNavigation().moveTo(cx, cy, cz, 1.0D);
                    }
                } else if (distSqr > 0.01) {
                    this.mob.getNavigation().stop();
                    this.mob.getMoveControl().setWantedPosition(cx, cy, cz, 1.0D);
                } else {
                    if (distSqr > 1.0E-6) {
                        this.mob.moveTo(cx, cy, cz, this.mob.getYRot(), this.mob.getXRot());
                    }
                    this.mob.getNavigation().stop();
                    this.mob.getMoveControl().setWantedPosition(cx, cy, cz, 0.0D);
                    this.mob.setDeltaMovement(0, 0, 0);
                    this.mob.setHideState(MurkletEntity.HIDE_STATE_DIGGING);
                    this.mob.addExploredPosition(GlobalPos.of(this.mob.level().dimension(), targetPos));
                    this.phase = Phase.DIGGING;
                    this.mob.setDigTimer(0);
                }
            }
            case DIGGING -> {
                this.mob.setDigTimer(this.mob.getDigTimer() + 1);
                if (this.mob.getDigTimer() >= DIG_TICKS) {
                    this.mob.setHideState(MurkletEntity.HIDE_STATE_HIDDEN);
                    this.phase = Phase.HIDING;
                    this.mob.setDigTimer(0);
                }
            }
            case HIDING -> {
                this.mob.setDigTimer(this.mob.getDigTimer() + 1);
                if (this.mob.getDigTimer() >= (HIDE_TICKS + randomTimer)) {
                    this.mob.setHideState(MurkletEntity.HIDE_STATE_UNDIGGING);
                    this.phase = Phase.UNDIGGING;
                    this.mob.setDigTimer(0);
                }
            }
            case UNDIGGING -> {
                this.mob.setDigTimer(this.mob.getDigTimer() + 1);
                if (this.mob.getDigTimer() >= UNDIG_TICKS) {
                    this.mob.setHideState(MurkletEntity.HIDE_STATE_NONE);
                    this.stop();
                }
            }
            default -> {
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.phase != Phase.SEARCHING;
    }

    @Override
    public void stop() {
        if (this.mob.getHideState() != MurkletEntity.HIDE_STATE_NONE && this.phase != Phase.HIDING) {
            this.mob.setHideState(MurkletEntity.HIDE_STATE_NONE);
        }
        this.phase = Phase.SEARCHING;
        this.randomTimer = 0;
        this.mob.setDigTimer(0);
        this.targetPos = null;
        this.mob.setHideTarget(null);
        this.mob.getNavigation().stop();
    }

    private BlockPos findHidingSpot() {
        BlockPos origin = this.mob.blockPosition();
        BlockPos best = null;
        double bestDist = Double.MAX_VALUE;

        for (BlockPos pos : BlockPos.betweenClosed(
                origin.offset(-SEARCH_RADIUS, -2, -SEARCH_RADIUS),
                origin.offset(SEARCH_RADIUS, 2, SEARCH_RADIUS))) {
            if (!this.mob.level().getBlockState(pos).is(ModTags.Blocks.NIHIL_BUSHES_MAY_PLACE_ON))
                continue;
            if (!this.mob.level().isEmptyBlock(pos.above()))
                continue;

            boolean alreadyVisited = this.mob.getExploredPositions().stream()
                    .anyMatch(p -> p.dimension() == this.mob.level().dimension()
                            && p.pos().equals(pos));
            if (alreadyVisited)
                continue;

            double dist = origin.distSqr(pos);
            if (dist < bestDist) {
                bestDist = dist;
                best = pos.immutable();
            }
        }
        return best;
    }
}