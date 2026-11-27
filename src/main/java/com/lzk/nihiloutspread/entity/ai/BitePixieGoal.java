package com.lzk.nihiloutspread.entity.ai;

import java.util.EnumSet;
import java.util.List;

import com.lzk.nihiloutspread.entity.MurkletEntity;
import com.lzk.nihiloutspread.entity.PixieEntity;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.goal.Goal;

public class BitePixieGoal extends Goal {

    private static final double CLOSE_RADIUS = 0.45D;
    private static final double CANCEL_RADIUS = 1.2D;
    private static final int BITE_TICKS = 15;
    private static final int BITE_HIT_TICK = 8;
    private static final int COOLDOWN_TICKS = 300;

    private final MurkletEntity mob;
    private PixieEntity target;
    private int timer;
    private int biteCooldown;
    private boolean hasBitten;

    public BitePixieGoal(MurkletEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.biteCooldown > 0) {
            this.biteCooldown--;
            return false;
        }

        int state = this.mob.getHideState();
        if (state != MurkletEntity.HIDE_STATE_HIDDEN
                && state != MurkletEntity.HIDE_STATE_PEEKING) {
            return false;
        }

        this.target = findPixieWithin(CLOSE_RADIUS);
        return this.target != null;
    }

    @Override
    public void start() {
        this.timer = 0;
        this.mob.setWaitTimer(this.mob.getRandom().nextIntBetweenInclusive(20, 40));
        this.hasBitten = false;
        this.mob.setBiteTimer(0);
        this.mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        boolean isBiting = this.mob.getHideState() == MurkletEntity.HIDE_STATE_BITING;

        if (!isBiting) {
            PixieEntity closePixie = findPixieWithin(CLOSE_RADIUS);
            if (closePixie != null) {
                this.target = closePixie;
                this.timer++;
            }

            if (this.timer >= this.mob.getWaitTimer()) {
                this.mob.setHideState(MurkletEntity.HIDE_STATE_BITING);
            }
            return;
        }

        this.timer++;
        int biteProgress = this.timer - this.mob.getWaitTimer();
        this.mob.setBiteTimer(biteProgress);

        if (this.target != null && this.target.isAlive()) {
            this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
        }

        if (!this.hasBitten && biteProgress >= BITE_HIT_TICK) {
            this.hasBitten = true;
            if (this.target != null && this.target.isAlive()
                    && this.mob.distanceToSqr(this.target) <= 2.25D) {
                this.mob.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.0F);
                this.target.discard();
                this.mob.setLastTimeEaten(this.mob.level().getGameTime());
            }
        }

        if (biteProgress >= BITE_TICKS) {
            this.biteCooldown = COOLDOWN_TICKS;
            this.mob.setHideState(MurkletEntity.HIDE_STATE_HIDDEN);
            this.mob.setBiteTimer(0);
            this.stop();
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (this.mob.hurtTime > 0) {
            return false;
        }

        if (this.mob.getHideState() == MurkletEntity.HIDE_STATE_BITING) {
            return (this.timer - this.mob.getWaitTimer()) < BITE_TICKS;
        }

        return findPixieWithin(CANCEL_RADIUS) != null;
    }

    @Override
    public void stop() {
        if (this.mob.getHideState() == MurkletEntity.HIDE_STATE_BITING) {
            this.mob.setHideState(MurkletEntity.HIDE_STATE_HIDDEN);
        }
        this.target = null;
        this.timer = 0;
        this.hasBitten = false;
    }

    private PixieEntity findPixieWithin(double radius) {
        List<PixieEntity> pixies = this.mob.level().getEntitiesOfClass(
                PixieEntity.class,
                this.mob.getBoundingBox().inflate(radius),
                PixieEntity::isAlive);
        return pixies.isEmpty() ? null : pixies.get(0);
    }
}
