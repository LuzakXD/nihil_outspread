package com.lzk.nihiloutspread.init;

import com.lzk.nihiloutspread.NihilOutspread;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

public class ModCreativeTabs {

        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, NihilOutspread.MODID);

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS
                        .register("example_tab", () -> CreativeModeTab.builder()
                                        .title(Component.translatable("itemGroup.nihil_outspread"))
                                        .icon(() -> ModItems.EXAMPLE_ITEM.get().getDefaultInstance())
                                        .displayItems((parameters, output) -> {

                                                output.accept(ModBlocks.NIHIL_GRASS_BLOCK.get());
                                                output.accept(ModBlocks.NIHIL_DIRT.get());

                                                for (Item item : ModBlocks.NILIGHT_BLOCK_FAMILY.devolverItens(true)) {
                                                        output.accept(item);
                                                }

                                                for (Item item : ModBlocks.GRAPHITE_BLOCK_FAMILY.devolverItens(true)) {
                                                        output.accept(item);
                                                }

                                                output.accept(ModBlocks.VELVET_SAND.get());

                                                for (Item item : ModBlocks.VELVESTONE_BLOCK_FAMILY
                                                                .devolverItens(true)) {
                                                        output.accept(item);
                                                }

                                                for (Item item : ModBlocks.COTTONITE_BLOCK_FAMILY.devolverItens(true)) {
                                                        output.accept(item);
                                                }

                                                for (Item item : ModBlocks.HONEYCOMB_BLOCK_FAMILY.devolverItens(true)) {
                                                        output.accept(item);
                                                }

                                        }).build());

}
