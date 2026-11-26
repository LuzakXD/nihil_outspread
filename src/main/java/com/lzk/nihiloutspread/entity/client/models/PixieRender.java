package com.lzk.nihiloutspread.entity.client.models;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.entity.PixieEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PixieRender extends MobRenderer<PixieEntity, PixieModel<PixieEntity>> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NihilOutspread.MODID,
            "textures/entity/nihil/pixie.png");

    public PixieRender(EntityRendererProvider.Context context) {
        super(context, new PixieModel<>(context.bakeLayer(PixieModel.LAYER_LOCATION)), 0.1f);
    }

    @Override
    public ResourceLocation getTextureLocation(PixieEntity entity) {
        return TEXTURE;
    }

    @Override
    protected float getShadowRadius(PixieEntity entity) {
        return super.getShadowRadius(entity);
    }
}