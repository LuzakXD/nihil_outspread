package com.lzk.nihiloutspread.entity.ai;

import com.lzk.nihiloutspread.entity.MurkletEntity;
import com.lzk.nihiloutspread.entity.PixieEntity;
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

    private static final int DIG_TICKS = 24;
    private static final int HIDE_TICKS = 200;
    private static final int UNDIG_TICKS = 30;
    private static final int SEARCH_RADIUS = 16;

    private double moveSpeed = 1.0D;

    private boolean isPanicking() {
        return this.mob.getLastHurtByMob() != null || this.mob.isOnFire() || this.mob.isFreezing();
    }

    private enum HideMode {
        RANDOM, PANIC, HUNT
    }

    private HideMode currentMode = HideMode.RANDOM;

    private enum Phase {
        SEARCHING, MOVING, DIGGING, HIDING, UNDIGGING
    }

    private final MurkletEntity mob;
    private BlockPos targetPos;
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
                    : this.mob.blockPosition().below();
            this.phase = switch (state) {
                case MurkletEntity.HIDE_STATE_DIGGING -> Phase.DIGGING;
                case MurkletEntity.HIDE_STATE_HIDDEN -> Phase.HIDING;
                case MurkletEntity.HIDE_STATE_UNDIGGING -> Phase.UNDIGGING;
                case MurkletEntity.HIDE_STATE_PEEKING -> Phase.HIDING;
                case MurkletEntity.HIDE_STATE_BITING -> Phase.HIDING;
                default -> Phase.SEARCHING;
            };
            return true;
        }

        if (this.mob.getHideCooldown() > 0)
            return false;
        if (this.mob.getTarget() != null)
            return false;

        boolean panicking = isPanicking();
        long hungerThreshold = 20L * 60L * 7L;
        boolean hungry = !panicking
                && this.mob.level().getGameTime() - this.mob.getLastTimeEaten() > hungerThreshold;

        if (panicking) {
            this.currentMode = HideMode.PANIC;
            this.targetPos = findHidingSpot(true);
        } else if (hungry) {
            this.targetPos = findBushNearPixie();
            if (this.targetPos != null) {
                this.currentMode = HideMode.HUNT;
            } else {
                if (this.mob.getRandom().nextInt(250) != 0)
                    return false;
                this.currentMode = HideMode.RANDOM;
                this.targetPos = findHidingSpot(false);
            }
        } else {
            if (this.mob.getRandom().nextInt(250) != 0)
                return false;
            this.currentMode = HideMode.RANDOM;
            this.targetPos = findHidingSpot(false);
        }
        if (this.targetPos == null)
            return false;
        this.moveSpeed = panicking ? 1.5D : 1.0D;
        this.mob.setHideTarget(GlobalPos.of(this.mob.level().dimension(), this.targetPos));
        return true;
    }

    private BlockPos findBushNearPixie() {
        BlockPos origin = this.mob.blockPosition();
        List<PixieEntity> pixies = this.mob.level().getEntitiesOfClass(
                PixieEntity.class,
                this.mob.getBoundingBox().inflate(24.0),
                PixieEntity::isAlive);

        BlockPos best = null;
        double bestScore = Double.MAX_VALUE;

        for (BlockPos pos : BlockPos.betweenClosed(
                origin.offset(-SEARCH_RADIUS, -2, -SEARCH_RADIUS),
                origin.offset(SEARCH_RADIUS, 2, SEARCH_RADIUS))) {
            if (!isValidHidingBlock(pos))
                continue;
            if (occupiedByOther(pos))
                continue;
            if (alreadyVisited(pos))
                continue;

            // Distância até a pixie mais próxima deste bush
            double minPixieDist = pixies.stream()
                    .mapToDouble(p -> p.blockPosition().distSqr(pos))
                    .min().orElse(Double.MAX_VALUE);

            // Score combina "perto do Murklet" + "perto da Pixie"
            double score = origin.distSqr(pos) + minPixieDist;
            if (score < bestScore) {
                bestScore = score;
                best = pos.immutable();
            }
        }
        return best;
    }

    @Override
    public void start() {
        int state = this.mob.getHideState();
        if (state != MurkletEntity.HIDE_STATE_NONE) {

            return;
        }
        this.mob.setRandomTimer(this.mob.getRandom().nextInt(200));
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
                    this.mob.getX() + 0.25D,
                    this.mob.getY(),
                    this.mob.getZ() + 0.25D,
                    4,
                    0.1D,
                    0.05D,
                    0.1D,
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

                this.mob.setSearchTimer(this.mob.getSearchTimer() + 1);

                if (this.mob.getSearchTimer() >= 100) {
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

                if (this.mob.getDigTimer() >= 8 && this.mob.getDigTimer() <= 12)
                    if (this.mob.getDigTimer() % 2 == 0) {
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

                boolean safeToExit;
                if (this.currentMode == HideMode.PANIC) {
                    safeToExit = !isPanicking();
                } else if (this.currentMode == HideMode.HUNT) {
                    safeToExit = this.mob.getDigTimer() >= 1200;
                } else {
                    safeToExit = this.mob.getDigTimer() >= (HIDE_TICKS + this.mob.getRandomTimer());
                }

                if (safeToExit) {
                    this.mob.setHideState(MurkletEntity.HIDE_STATE_UNDIGGING);
                    this.phase = Phase.UNDIGGING;
                    this.mob.setDigTimer(0);
                }
            }
            case UNDIGGING -> {
                this.mob.setDigTimer(this.mob.getDigTimer() + 1);

                if (this.mob.getDigTimer() <= 12)
                    if (this.mob.getDigTimer() % 2 == 0) {
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
        boolean naturalEnd = this.mob.getHideState() == MurkletEntity.HIDE_STATE_NONE
                || this.phase != Phase.HIDING;
        if (naturalEnd) {
            this.mob.setHideState(MurkletEntity.HIDE_STATE_NONE);
            this.mob.setHideTarget(null);
            this.currentMode = HideMode.RANDOM;
        }
        this.phase = Phase.SEARCHING;
        this.mob.setSearchTimer(0);
        this.mob.setDigTimer(0);
        this.mob.setRandomTimer(0);
        this.targetPos = null;
        this.mob.getNavigation().stop();
    }

    private BlockPos findHidingSpot(boolean ignoreExplored) {
        BlockPos origin = this.mob.blockPosition();
        BlockPos best = null;
        double bestDist = Double.MAX_VALUE;

        for (BlockPos pos : BlockPos.betweenClosed(
                origin.offset(-SEARCH_RADIUS, -2, -SEARCH_RADIUS),
                origin.offset(SEARCH_RADIUS, 2, SEARCH_RADIUS))) {

            BlockState state = this.mob.level().getBlockState(pos);
            if (!state.is(ModTags.Blocks.NIHIL_BUSHES_MAY_PLACE_ON))
                continue;

            if (occupiedByOther(pos))
                continue;

            BlockPos abovePos = pos.above();
            BlockState stateAbove = this.mob.level().getBlockState(abovePos);
            if (!stateAbove.getCollisionShape(this.mob.level(), abovePos).isEmpty()
                    || !stateAbove.getFluidState().isEmpty())
                continue;

            if (!ignoreExplored && alreadyVisited(pos))
                continue;

            double dist = origin.distSqr(pos);
            if (dist < bestDist) {
                bestDist = dist;
                best = pos.immutable();
            }
        }
        return best;
    }

    private boolean occupiedByOther(BlockPos pos) {
        List<MurkletEntity> nearbyMurklets = this.mob.level().getEntitiesOfClass(
                MurkletEntity.class,
                this.mob.getBoundingBox().inflate(SEARCH_RADIUS + 2.0D),
                other -> other != this.mob && other.getHideTarget() != null);
        return nearbyMurklets.stream()
                .anyMatch(other -> other.getHideTarget().dimension() == this.mob.level().dimension()
                        && other.getHideTarget().pos().equals(pos));
    }

    private boolean alreadyVisited(BlockPos pos) {
        return this.mob.getExploredPositions().stream()
                .anyMatch(p -> p.dimension() == this.mob.level().dimension()
                        && p.pos().equals(pos));
    }
}