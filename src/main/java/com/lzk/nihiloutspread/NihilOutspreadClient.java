package com.lzk.nihiloutspread;

import com.lzk.nihiloutspread.entity.client.models.MurkletModel;
import com.lzk.nihiloutspread.entity.client.models.MurkletRender;
import com.lzk.nihiloutspread.entity.client.models.PixieModel;
import com.lzk.nihiloutspread.entity.client.models.PixieRender;
import com.lzk.nihiloutspread.init.ModEntities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = NihilOutspread.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = NihilOutspread.MODID, value = Dist.CLIENT)
public class NihilOutspreadClient {
    public NihilOutspreadClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MurkletModel.LAYER_LOCATION, MurkletModel::createBodyLayer);
        event.registerLayerDefinition(PixieModel.LAYER_LOCATION, PixieModel::createBodyLayer);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {

        EntityRenderers.register(ModEntities.MURKLET.get(), MurkletRender::new);
        EntityRenderers.register(ModEntities.PIXIE.get(), PixieRender::new);

        NihilOutspread.LOGGER.info("HELLO FROM CLIENT SETUP");
        NihilOutspread.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
