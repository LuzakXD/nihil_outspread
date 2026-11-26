package com.lzk.nihiloutspread.entity.ai;

import java.util.EnumSet;
import java.util.List;

import com.lzk.nihiloutspread.entity.MurkletEntity;
import com.lzk.nihiloutspread.entity.PixieEntity;

import net.minecraft.world.entity.ai.goal.Goal;

public class BitePixieGoal extends Goal {

    private static final double BITE_RADIUS = 1.0;
    private static final int BITE_TICKS = 15;
    private static final int BITE_HIT_TICK = 8;
    private static final int COOLDOWN_TICKS = 300;

    private final MurkletEntity mob;
    private PixieEntity target;
    private int timer;
    private boolean hasBitten;

    public BitePixieGoal(MurkletEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        int state = this.mob.getHideState();
        if (state != MurkletEntity.HIDE_STATE_HIDDEN
                && state != MurkletEntity.HIDE_STATE_PEEKING)
            return false;
        if (this.mob.getHideCooldown() > 0)
            return false;

        this.target = findPixieInRange();
        return this.target != null;
    }

    @Override
    public void start() {
        this.timer = 0;
        this.hasBitten = false;
        this.mob.setHideState(MurkletEntity.HIDE_STATE_BITING);
        this.mob.setBiteTimer(0);
        this.mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        this.timer++;
        this.mob.setBiteTimer(this.timer);

        if (!this.hasBitten && this.timer >= BITE_HIT_TICK) {
            this.hasBitten = true;
            if (this.target != null && this.target.isAlive()) {
                this.target.discard();
                this.mob.setLastTimeEaten(this.mob.level().getGameTime());
            }
        }

        if (this.timer >= BITE_TICKS) {
            this.mob.setHideState(MurkletEntity.HIDE_STATE_HIDDEN);
            this.mob.setHideCooldown(COOLDOWN_TICKS);
            this.mob.setBiteTimer(0);
            this.stop();
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.mob.getHideState() == MurkletEntity.HIDE_STATE_BITING
                && this.timer < BITE_TICKS;
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

    private PixieEntity findPixieInRange() {
        List<PixieEntity> pixies = this.mob.level().getEntitiesOfClass(
                PixieEntity.class,
                this.mob.getBoundingBox().inflate(BITE_RADIUS),
                PixieEntity::isAlive);
        return pixies.isEmpty() ? null : pixies.get(0);
    }
}