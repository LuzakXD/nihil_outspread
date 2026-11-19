package com.lzk.nihiloutspread.init;

import com.lzk.nihiloutspread.NihilOutspread;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {

        public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister
                        .create(BuiltInRegistries.SOUND_EVENT, NihilOutspread.MODID);

        public static final DeferredHolder<SoundEvent, SoundEvent> HONEY_GATE_CLICK = registrar("honey_gate_click",
                        "block.honey_gate_click");

        private static DeferredHolder<SoundEvent, SoundEvent> registrar(String nome, String evento) {
                DeferredHolder<SoundEvent, SoundEvent> som = SOUND_EVENTS.register(
                                nome,
                                () -> SoundEvent.createVariableRangeEvent(
                                                ResourceLocation.fromNamespaceAndPath(NihilOutspread.MODID,
                                                                evento)));

                return som;
        }

}
