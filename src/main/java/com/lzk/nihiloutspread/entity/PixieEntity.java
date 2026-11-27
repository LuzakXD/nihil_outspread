package com.lzk.nihiloutspread.entity;

import com.lzk.nihiloutspread.entity.ai.SeekBushLikeGoal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class PixieEntity extends PathfinderMob {

    // TODO: add NBT data for stuff on "SeekBushLikeGoal"
    // (they arent NBT for now cuz im testing stuff, and im too lazy to add NBT rn
    // (its hard to work on that mod while im at my job))
    public PixieEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.FLYING_SPEED, 0.1F)
                .add(Attributes.MOVEMENT_SPEED, 0.1F)
                .add(Attributes.FOLLOW_RANGE, 48.0)
                .add(Attributes.SCALE, 0.75D);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation navigation = new FlyingPathNavigation(this, level);
        navigation.setCanOpenDoors(false);
        navigation.setCanFloat(true);
        navigation.setCanPassDoors(true);
        return navigation;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D) {
            @Override // im too lazy to create a new class rn
            protected boolean findRandomPosition() {
                Vec3 view = this.mob.getViewVector(0.0F);
                Vec3 pos = HoverRandomPos.getPos(this.mob, 8, 7, view.x, view.z, ((float) Math.PI / 2F), 3, 1);
                if (pos == null) {
                    pos = AirAndWaterRandomPos.getPos(this.mob, 8, 4, -2, view.x, view.z, ((float) Math.PI / 2F));
                }
                if (pos != null) {
                    this.posX = pos.x;
                    this.posY = pos.y;
                    this.posZ = pos.z;
                    return true;
                }
                return false;
            }
        });
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(
                this, MurkletEntity.class, 12.0F, 1.0, 1.2,
                entity -> entity instanceof MurkletEntity m
                        && m.getHideState() == MurkletEntity.HIDE_STATE_NONE));

        this.goalSelector.addGoal(3, new SeekBushLikeGoal(this));

        WaterAvoidingRandomFlyingGoal flyingGoal = new WaterAvoidingRandomFlyingGoal(this, 1.0D);
        flyingGoal.setInterval(15);
        this.goalSelector.addGoal(4, flyingGoal);
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    // thank you allay
    @Override
    public void travel(Vec3 travelVector) {
        if (this.isControlledByLocalInstance()) {
            if (this.isInWater()) {
                this.moveRelative(0.02F, travelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.8F));
            } else if (this.isInLava()) {
                this.moveRelative(0.02F, travelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5));
            } else {
                this.moveRelative(this.getSpeed(), travelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.91F));
            }
        }
        this.calculateEntityAnimation(false);
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }
}
