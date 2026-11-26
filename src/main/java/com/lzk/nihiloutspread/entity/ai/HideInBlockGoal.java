package com.lzk.nihiloutspread.entity.ai;

import com.lzk.nihiloutspread.entity.MurkletEntity;
import com.lzk.nihiloutspread.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;
import java.util.List;

public class HideInBlockGoal extends Goal {

    private static final int DIG_TICKS = 20;
    private static final int HIDE_TICKS = 200;
    private static final int UNDIG_TICKS = 25;
    private static final int SEARCH_RADIUS = 16;

    private double moveSpeed = 1.0D;

    private boolean hidFromPanic = false;

    private boolean isPanicking() {
        return this.mob.getLastHurtByMob() != null || this.mob.isOnFire() || this.mob.isFreezing();
    }

    private enum Phase {
        SEARCHING, MOVING, DIGGING, HIDING, UNDIGGING
    }

    private final MurkletEntity mob;
    private BlockPos targetPos;
    private int randomTimer;
    private int searchTimer;
    private Phase phase = Phase.SEARCHING;

    public HideInBlockGoal(MurkletEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.mob.getHideCooldown() > 0) {
            this.mob.setHideCooldown(this.mob.getHideCooldown() - 1);
            return false;
        }
        if (this.mob.getTarget() != null)
            return false;

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

        boolean panicking = isPanicking();
        this.hidFromPanic = panicking;
        if (!panicking && this.mob.getRandom().nextInt(250) != 0)
            return false;

        this.targetPos = findHidingSpot(panicking);
        if (this.targetPos != null) {
            this.moveSpeed = panicking ? 1.5D : 1.0D;
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
                this.moveSpeed);

    }

    private void spawnDiggingParticles() {
        if (this.targetPos == null || !(this.mob.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        BlockState blockState = serverLevel.getBlockState(this.targetPos);
        if (blockState.getRenderShape() != RenderShape.INVISIBLE) {
            serverLevel.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, blockState),
                    this.targetPos.getX() + 0.5D,
                    this.targetPos.getY() + 1.05D,
                    this.targetPos.getZ() + 0.5D,
                    4,
                    0.25D,
                    0.05D,
                    0.25D,
                    0.15D);
        }
    }

    private boolean isValidHidingBlock(BlockPos pos) {
        if (pos == null)
            return false;

        BlockState state = this.mob.level().getBlockState(pos);
        if (!state.is(ModTags.Blocks.NIHIL_BUSHES_MAY_PLACE_ON)) {
            return false;
        }

        BlockPos abovePos = pos.above();
        BlockState stateAbove = this.mob.level().getBlockState(abovePos);
        return stateAbove.getCollisionShape(this.mob.level(), abovePos).isEmpty()
                && stateAbove.getFluidState().isEmpty();
    }

    @Override
    public void tick() {

        if (!isValidHidingBlock(this.targetPos)) {
            if (this.phase != Phase.MOVING) {
                this.mob.setHideCooldown(60);
            }
            this.mob.setHideState(MurkletEntity.HIDE_STATE_NONE);
            this.stop();
            return;
        }

        switch (phase) {
            case MOVING -> {
                double cx = targetPos.getX() + 0.5;
                double cy = targetPos.getY() + 1.0;
                double cz = targetPos.getZ() + 0.5;
                double distSqr = this.mob.distanceToSqr(cx, cy, cz);

                searchTimer++;

                if (searchTimer >= 100) {
                    this.mob.addExploredPosition(GlobalPos.of(this.mob.level().dimension(), targetPos));
                    this.stop();
                    return;
                }

                if (distSqr > 4.0) {
                    if (this.mob.getNavigation().isDone()) {
                        this.mob.getNavigation().moveTo(cx, cy, cz, this.moveSpeed);
                    }
                } else if (distSqr > 0.16) {
                    this.mob.getNavigation().stop();
                    this.mob.getMoveControl().setWantedPosition(cx, cy, cz, this.moveSpeed);
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

                if (this.mob.getDigTimer() % 4 == 0) {
                    spawnDiggingParticles();
                }

                if (this.mob.getDigTimer() >= DIG_TICKS) {
                    this.mob.setHideState(MurkletEntity.HIDE_STATE_HIDDEN);
                    this.phase = Phase.HIDING;
                    this.mob.setDigTimer(0);
                }
            }
            case HIDING -> {
                if (this.mob.hurtTime > 0) {
                    this.mob.setHideCooldown(100);
                    this.mob.setHideState(MurkletEntity.HIDE_STATE_UNDIGGING);
                    this.phase = Phase.UNDIGGING;
                    this.mob.setDigTimer(0);
                    return;
                }

                this.mob.setDigTimer(this.mob.getDigTimer() + 1);

                boolean safeToExit = this.hidFromPanic
                        ? !isPanicking()
                        : this.mob.getDigTimer() >= (HIDE_TICKS + randomTimer);

                if (safeToExit) {
                    this.mob.setHideState(MurkletEntity.HIDE_STATE_UNDIGGING);
                    this.phase = Phase.UNDIGGING;
                    this.mob.setDigTimer(0);
                }
            }
            case UNDIGGING -> {
                this.mob.setDigTimer(this.mob.getDigTimer() + 1);

                if (this.mob.getDigTimer() % 4 == 0) {
                    spawnDiggingParticles();
                }

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
        this.searchTimer = 0;
        this.mob.setDigTimer(0);
        this.targetPos = null;
        this.hidFromPanic = false;
        this.mob.setHideTarget(null);
        this.mob.getNavigation().stop();
    }

    private BlockPos findHidingSpot(boolean ignoreExplored) {
        BlockPos origin = this.mob.blockPosition();
        BlockPos best = null;
        double bestDist = Double.MAX_VALUE;

        List<MurkletEntity> nearbyMurklets = this.mob.level().getEntitiesOfClass(
                MurkletEntity.class,
                this.mob.getBoundingBox().inflate(SEARCH_RADIUS + 2.0D),
                other -> other != this.mob && other.getHideTarget() != null);

        for (BlockPos pos : BlockPos.betweenClosed(
                origin.offset(-SEARCH_RADIUS, -2, -SEARCH_RADIUS),
                origin.offset(SEARCH_RADIUS, 2, SEARCH_RADIUS))) {

            BlockState state = this.mob.level().getBlockState(pos);
            if (!state.is(ModTags.Blocks.NIHIL_BUSHES_MAY_PLACE_ON))
                continue;

            boolean occupiedByOther = nearbyMurklets.stream()
                    .anyMatch(other -> other.getHideTarget().dimension() == this.mob.level().dimension()
                            && other.getHideTarget().pos().equals(pos));
            if (occupiedByOther) {
                continue;
            }

            BlockPos abovePos = pos.above();
            BlockState stateAbove = this.mob.level().getBlockState(abovePos);
            if (!stateAbove.getCollisionShape(this.mob.level(), abovePos).isEmpty()
                    || !stateAbove.getFluidState().isEmpty())
                continue;

            if (!ignoreExplored) {
                boolean alreadyVisited = this.mob.getExploredPositions().stream()
                        .anyMatch(p -> p.dimension() == this.mob.level().dimension()
                                && p.pos().equals(pos));
                if (alreadyVisited)
                    continue;
            }

            double dist = origin.distSqr(pos);
            if (dist < bestDist) {
                bestDist = dist;
                best = pos.immutable();
            }
        }
        return best;
    }
}