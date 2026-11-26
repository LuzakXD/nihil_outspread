package com.lzk.nihiloutspread;

import com.lzk.nihiloutspread.entity.MurkletEntity;
import com.lzk.nihiloutspread.entity.PixieEntity;
import com.lzk.nihiloutspread.init.ModEntities;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = NihilOutspread.MODID, bus = EventBusSubscriber.Bus.MOD)
public class NihilOutspreadBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MURKLET.get(), MurkletEntity.createAttributes().build());
        event.put(ModEntities.PIXIE.get(), PixieEntity.createAttributes().build());
    }
}
