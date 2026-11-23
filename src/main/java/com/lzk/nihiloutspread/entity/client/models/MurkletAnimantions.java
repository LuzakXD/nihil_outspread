package com.lzk.nihiloutspread.entity.client.models;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class MurkletAnimantions {
        public static final AnimationDefinition walk = AnimationDefinition.Builder.withLength(1.0F).looping()
                        .addAnimation("root", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_ear", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_ear", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .build();

        public static final AnimationDefinition walk_alt = AnimationDefinition.Builder.withLength(1.0F).looping()
                        .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 15.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("root", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_ear", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, -3.4547F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_ear", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 3.4547F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .build();

        public static final AnimationDefinition dig = AnimationDefinition.Builder.withLength(1.0F)
                        .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("root", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, -4.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -3.5F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, -4.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_ear", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_ear", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .build();

        public static final AnimationDefinition undig = AnimationDefinition.Builder.withLength(1.25F)
                        .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.875F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 15.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.875F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("root", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -3.5F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, -4.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.625F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.875F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.25F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.875F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.875F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, -4.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.625F, KeyframeAnimations.posVec(0.0F, -4.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.875F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_ear", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_ear", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .build();

        public static final AnimationDefinition dig_peek = AnimationDefinition.Builder.withLength(2.0F).looping()
                        .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 15.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 15.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("root", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -7.5F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_ear", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_ear", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .build();

        public static final AnimationDefinition dig_idle = AnimationDefinition.Builder.withLength(2.0F).looping()
                        .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("root", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -7.5F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("torso", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_ear", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("right_ear", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_ear", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .addAnimation("left_ear", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR),
                                        new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F),
                                                        AnimationChannel.Interpolations.LINEAR)))
                        .build();
}