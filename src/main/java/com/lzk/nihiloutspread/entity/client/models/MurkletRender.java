package com.lzk.nihiloutspread.entity.client.models;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.entity.MurkletEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MurkletRender extends MobRenderer<MurkletEntity, MurkletModel<MurkletEntity>> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NihilOutspread.MODID,
            "textures/entity/nihil/murklet.png");

    public MurkletRender(EntityRendererProvider.Context context) {
        super(context, new MurkletModel<>(context.bakeLayer(MurkletModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(MurkletEntity entity) {
        return TEXTURE;
    }

    @Override
    protected float getShadowRadius(MurkletEntity entity) {
        if (entity.getHideState() == MurkletEntity.HIDE_STATE_HIDDEN
                || entity.getHideState() == MurkletEntity.HIDE_STATE_HIDDEN_PEEK) {
            return 0.0F;
        }
        return super.getShadowRadius(entity);
    }
}