package com.lzk.nihiloutspread.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lzk.nihiloutspread.entity.ai.BitePixieGoal;
import com.lzk.nihiloutspread.entity.ai.HideInBlockGoal;

import net.minecraft.core.GlobalPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;

public class MurkletEntity extends PathfinderMob {

    public static final int HIDE_STATE_NONE = 0;
    public static final int HIDE_STATE_DIGGING = 1;
    public static final int HIDE_STATE_HIDDEN = 2;
    public static final int HIDE_STATE_PEEKING = 3;
    public static final int HIDE_STATE_BITING = 4;
    public static final int HIDE_STATE_UNDIGGING = 5;

    public final AnimationState digState = new AnimationState();
    public final AnimationState digIdleState = new AnimationState();
    public final AnimationState undigState = new AnimationState();
    public final AnimationState biteState = new AnimationState();

    @Override
    public void tick() {

        if (!this.level().isClientSide()) {
            if (this.hideCooldown > 0) {
                this.hideCooldown--;
            }
        }

        super.tick();
        if (this.level().isClientSide()) {
            int state = this.getHideState();

            if (state == HIDE_STATE_DIGGING) {
                this.digState.startIfStopped(this.tickCount);
            } else {
                this.digState.stop();
            }

            if (state == HIDE_STATE_HIDDEN) {
                this.digIdleState.startIfStopped(this.tickCount);
            } else {
                this.digIdleState.stop();
            }

            if (state == HIDE_STATE_UNDIGGING) {
                this.undigState.startIfStopped(this.tickCount);
            } else {
                this.undigState.stop();
            }

            if (state == HIDE_STATE_BITING) {
                this.biteState.startIfStopped(this.tickCount);
            } else {
                this.biteState.stop();
            }
        }
    }

    private int digTimer;
    private int hideCooldown;
    private int randomTimer;
    private int searchTimer;
    private int biteTimer;
    private long lastTimeEaten;

    private static final EntityDataAccessor<Integer> DATA_HIDE_STATE = SynchedEntityData.defineId(
            MurkletEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Optional<GlobalPos>> DATA_HIDE_TARGET = SynchedEntityData.defineId(
            MurkletEntity.class, EntityDataSerializers.OPTIONAL_GLOBAL_POS);

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_HIDE_STATE, HIDE_STATE_NONE);
        builder.define(DATA_HIDE_TARGET, Optional.empty());
    }

    public int getHideState() {
        return this.entityData.get(DATA_HIDE_STATE);
    }

    public void setHideState(int state) {
        this.entityData.set(DATA_HIDE_STATE, state);
    }

    public int getDigTimer() {
        return this.digTimer;
    }

    public void setDigTimer(int timer) {
        this.digTimer = timer;
    }

    public int getHideCooldown() {
        return this.hideCooldown;
    }

    public void setHideCooldown(int cooldown) {
        this.hideCooldown = cooldown;
    }

    public GlobalPos getHideTarget() {
        return this.entityData.get(DATA_HIDE_TARGET).orElse(null);
    }

    public void setHideTarget(GlobalPos target) {
        this.entityData.set(DATA_HIDE_TARGET, Optional.ofNullable(target));
    }

    public int getRandomTimer() {
        return this.randomTimer;
    }

    public void setRandomTimer(int timer) {
        this.randomTimer = timer;
    }

    public int getSearchTimer() {
        return this.searchTimer;
    }

    public void setSearchTimer(int timer) {
        this.searchTimer = timer;
    }

    public int getBiteTimer() {
        return this.biteTimer;
    }

    public void setBiteTimer(int time) {
        this.biteTimer = time;
    }

    public long getLastTimeEaten() {
        return this.lastTimeEaten;
    }

    public void setLastTimeEaten(long time) {
        this.lastTimeEaten = time;
    }

    public MurkletEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.SCALE, 1.25D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BitePixieGoal(this));
        this.goalSelector.addGoal(2, new HideInBlockGoal(this));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

    }

    @Override
    public boolean isPushable() {
        return this.getHideState() == HIDE_STATE_NONE && super.isPushable();
    }

    @Override
    public boolean canBeCollidedWith() {
        return this.getHideState() == HIDE_STATE_NONE && super.canBeCollidedWith();
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.getHideState() == HIDE_STATE_HIDDEN && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            amount *= 0.25F;
        }
        return super.hurt(source, amount);
    }

    @Override
    public void die(DamageSource damageSource) {
        this.setHideState(HIDE_STATE_NONE);
        super.die(damageSource);
    }

    @Override
    public void aiStep() {
        if (this.getHideState() != HIDE_STATE_NONE) {
            var dm = this.getDeltaMovement();
            this.setDeltaMovement(0, Math.min(0, dm.y), 0);
            this.getNavigation().stop();
        }
        super.aiStep();
    }

    // thank you sniffer
    private static final int MAX_EXPLORED_POSITIONS = 10;
    private final List<GlobalPos> exploredPositions = new ArrayList<>();

    public List<GlobalPos> getExploredPositions() {
        return this.exploredPositions;
    }

    public void addExploredPosition(GlobalPos pos) {
        if (this.exploredPositions.size() >= MAX_EXPLORED_POSITIONS) {
            this.exploredPositions.remove(0);
        }
        this.exploredPositions.add(pos);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("HideState", this.getHideState());
        tag.putInt("DigTimer", this.getDigTimer());
        tag.putInt("HideCooldown", this.getHideCooldown());
        tag.putInt("RandomTimer", this.getRandomTimer());
        tag.putInt("SearchTimer", this.getSearchTimer());
        tag.putLong("LastTimeEaten", this.getLastTimeEaten());
        GlobalPos target = this.getHideTarget();
        if (target != null) {
            GlobalPos.CODEC.encodeStart(NbtOps.INSTANCE, target)
                    .result().ifPresent(t -> tag.put("HideTarget", t));
        }
        if (!this.exploredPositions.isEmpty()) {
            ListTag list = new ListTag();
            for (GlobalPos pos : this.exploredPositions) {
                GlobalPos.CODEC.encodeStart(NbtOps.INSTANCE, pos)
                        .result().ifPresent(list::add);
            }
            tag.put("ExploredPositions", list);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setHideState(tag.getInt("HideState"));
        this.setDigTimer(tag.getInt("DigTimer"));
        this.setHideCooldown(tag.getInt("HideCooldown"));
        this.setRandomTimer(tag.getInt("RandomTimer"));
        this.setSearchTimer(tag.getInt("SearchTimer"));
        this.setLastTimeEaten(tag.contains("LastTimeEaten", Tag.TAG_LONG)
                ? tag.getLong("LastTimeEaten")
                : this.level().getGameTime());
        if (tag.contains("HideTarget")) {
            GlobalPos.CODEC.parse(NbtOps.INSTANCE, tag.get(
                    "HideTarget"))
                    .result().ifPresent(this::setHideTarget);
        }
        this.exploredPositions.clear();
        if (tag.contains("ExploredPositions", Tag.TAG_LIST)) {
            ListTag list = tag.getList("ExploredPositions", Tag.TAG_COMPOUND);
            for (int i = 0; i < list.size(); i++) {
                GlobalPos.CODEC.parse(NbtOps.INSTANCE, list.get(i))
                        .result().ifPresent(this.exploredPositions::add);
            }
        }
    }
}
