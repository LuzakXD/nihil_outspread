package com.lzk.nihiloutspread.entity.client.models;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.entity.MurkletEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.LivingEntity;

public class MurkletModel<T extends LivingEntity> extends HierarchicalModel<T> {
        public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
                        ResourceLocation.fromNamespaceAndPath(NihilOutspread.MODID, "murklet"), "main");
        private final ModelPart root;
        private final ModelPart torso_pivot;
        private final ModelPart torso;
        private final ModelPart head;
        private final ModelPart ears;
        private final ModelPart right_ear;
        private final ModelPart left_ear;
        private final ModelPart body;
        private final ModelPart right_leg;
        private final ModelPart left_leg;

        public MurkletModel(ModelPart root) {
                this.root = root.getChild("root");
                this.torso_pivot = this.root.getChild("torso_pivot");
                this.torso = this.torso_pivot.getChild("torso");
                this.head = this.torso.getChild("head");
                this.ears = this.head.getChild("ears");
                this.right_ear = this.ears.getChild("right_ear");
                this.left_ear = this.ears.getChild("left_ear");
                this.body = this.torso.getChild("body");
                this.right_leg = this.root.getChild("right_leg");
                this.left_leg = this.root.getChild("left_leg");
        }

        public static LayerDefinition createBodyLayer() {
                MeshDefinition meshdefinition = new MeshDefinition();
                PartDefinition partdefinition = meshdefinition.getRoot();

                PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(),
                                PartPose.offset(0.0F, 20.0F, 0.0F));

                PartDefinition torso_pivot = root.addOrReplaceChild("torso_pivot", CubeListBuilder.create(),
                                PartPose.offset(0.0F, 0.0F, 0.0F));

                PartDefinition torso = torso_pivot.addOrReplaceChild("torso", CubeListBuilder.create(),
                                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

                PartDefinition head = torso.addOrReplaceChild("head",
                                CubeListBuilder.create().texOffs(0, 0)
                                                .addBox(-5.75F, -2.75F, -5.75F, 6.0F, 3.0F, 6.0F,
                                                                new CubeDeformation(-0.25F))
                                                .texOffs(24, 0).addBox(-5.75F, -0.75F, -5.75F, 6.0F, 2.0F, 6.0F,
                                                                new CubeDeformation(-0.35F)),
                                PartPose.offset(2.75F, -2.8F, 2.75F));

                PartDefinition ears = head.addOrReplaceChild("ears", CubeListBuilder.create(),
                                PartPose.offsetAndRotation(-3.25F, -0.25F, -3.25F, 0.0F, 0.7854F, 0.0F));

                @SuppressWarnings("unused")
                PartDefinition right_ear = ears.addOrReplaceChild("right_ear",
                                CubeListBuilder.create().texOffs(0, 26).addBox(-1.0F, -8.0F, -0.5F, 3.0F, 6.0F, 0.0F,
                                                new CubeDeformation(0.025F)),
                                PartPose.offsetAndRotation(-2.5F, 0.0F, 1.0F, 0.0F, 0.0F, -0.0873F));

                @SuppressWarnings("unused")
                PartDefinition left_ear = ears.addOrReplaceChild("left_ear",
                                CubeListBuilder.create().texOffs(6, 26).addBox(-1.0F, -8.0F, -0.5F, 3.0F, 6.0F, 0.0F,
                                                new CubeDeformation(0.025F)),
                                PartPose.offsetAndRotation(1.5F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0873F));

                @SuppressWarnings("unused")
                PartDefinition body = torso.addOrReplaceChild("body",
                                CubeListBuilder.create().texOffs(0, 9)
                                                .addBox(-3.0F, -1.8F, -6.0F, 6.0F, 4.0F, 6.0F,
                                                                new CubeDeformation(0.0F))
                                                .texOffs(40, 9)
                                                .addBox(-3.0F, -1.8F, -6.0F, 6.0F, 4.0F, 6.0F,
                                                                new CubeDeformation(0.1F))
                                                .texOffs(24, 8).addBox(-3.0F, -2.5F, -6.0F, 6.0F, 2.0F, 6.0F,
                                                                new CubeDeformation(-0.35F)),
                                PartPose.offset(0.0F, -2.0F, 3.0F));

                @SuppressWarnings("unused")
                PartDefinition right_leg = root.addOrReplaceChild("right_leg",
                                CubeListBuilder.create().texOffs(0, 19)
                                                .addBox(-1.0F, -0.9F, -1.0F, 2.0F, 5.0F, 2.0F,
                                                                new CubeDeformation(-0.1F))
                                                .texOffs(16, 19).addBox(-1.0F, -0.9F, -1.0F, 2.0F, 5.0F, 2.0F,
                                                                new CubeDeformation(0.0F)),
                                PartPose.offset(-2.0F, 0.0F, 0.0F));

                @SuppressWarnings("unused")
                PartDefinition left_leg = root.addOrReplaceChild("left_leg",
                                CubeListBuilder.create().texOffs(8, 19)
                                                .addBox(-1.0F, -0.9F, -1.0F, 2.0F, 5.0F, 2.0F,
                                                                new CubeDeformation(-0.1F))
                                                .texOffs(24, 19).addBox(-1.0F, -0.9F, -1.0F, 2.0F, 5.0F, 2.0F,
                                                                new CubeDeformation(-0.1F)),
                                PartPose.offset(2.0F, 0.0F, 0.0F));

                return LayerDefinition.create(meshdefinition, 64, 64);
        }

        private final AnimationState digState = new AnimationState();
        private final AnimationState digIdleState = new AnimationState();
        private final AnimationState undigState = new AnimationState();

        @Override
        public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
                        float headPitch) {
                this.root.getAllParts().forEach(ModelPart::resetPose);

                if (entity instanceof MurkletEntity rv) {
                        int state = rv.getHideState();

                        if (state != MurkletEntity.HIDE_STATE_DIGGING)
                                digState.stop();
                        if (state != MurkletEntity.HIDE_STATE_HIDDEN)
                                digIdleState.stop();
                        if (state != MurkletEntity.HIDE_STATE_UNDIGGING)
                                undigState.stop();

                        switch (state) {
                                case MurkletEntity.HIDE_STATE_DIGGING -> {
                                        digState.startIfStopped(entity.tickCount);
                                        this.animate(digState, MurkletAnimations.dig, ageInTicks, 1.0f);
                                }
                                case MurkletEntity.HIDE_STATE_HIDDEN -> {
                                        digIdleState.startIfStopped(entity.tickCount);
                                        this.animate(digIdleState, MurkletAnimations.dig_idle, ageInTicks, 1.0f);
                                }
                                case MurkletEntity.HIDE_STATE_UNDIGGING -> {
                                        undigState.startIfStopped(entity.tickCount);
                                        this.animate(undigState, MurkletAnimations.undig, ageInTicks, 1.0f);
                                }
                                default -> {
                                        this.animateWalk(MurkletAnimations.walk, limbSwing, limbSwingAmount, 2.5f,
                                                        2.5f);
                                }
                        }
                }
        }

        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay,
                        int color) {
                root.render(poseStack, buffer, packedLight, packedOverlay, color);
        }

        @Override
        public ModelPart root() {
                return this.root;
        }
}
