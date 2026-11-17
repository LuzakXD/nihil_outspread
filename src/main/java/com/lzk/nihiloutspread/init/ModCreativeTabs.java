package com.lzk.nihiloutspread.init;

import com.lzk.nihiloutspread.NihilOutspread;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {

        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, NihilOutspread.MODID);

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NIHIL = CREATIVE_MODE_TABS
                        .register("nihil", () -> CreativeModeTab.builder()
                                        .title(Component.translatable("itemGroup.nihil"))
                                        .icon(() -> new ItemStack(ModBlocks.NIHIL_GRASS_BLOCK.get()))
                                        .displayItems((parameters, output) -> {

                                                output.accept(ModBlocks.NIHIL_GRASS_BLOCK.get());
                                                output.accept(ModBlocks.NIHIL_DIRT.get());

                                                output.accept(ModBlocks.NIHIL_BUSH.get());
                                                output.accept(ModBlocks.NIHIL_FERN.get());
                                                output.accept(ModBlocks.NIHIL_HERB.get());
                                                output.accept(ModBlocks.NIHIL_PETALS.get());

                                                output.accept(ModBlocks.NIHIL_SPROUT.get());

                                                output.accept(ModBlocks.DREAD_SPROUT.get());

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

                                        }).build());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NIHIL_NATURALS = CREATIVE_MODE_TABS
                        .register("nihil_naturals", () -> CreativeModeTab.builder()
                                        .title(Component.translatable("itemGroup.nihil_naturals"))
                                        .icon(() -> new ItemStack(ModBlocks.NIHIL_GRASS_BLOCK.get()))
                                        .displayItems((parameters, output) -> {

                                                output.accept(ModBlocks.NIHIL_GRASS_BLOCK.get());
                                                output.accept(ModBlocks.CHECKER_GRASS_BLOCK.get());

                                                output.accept(ModBlocks.NIHIL_DIRT.get());

                                                output.accept(ModBlocks.NIHIL_BUSH.get());
                                                output.accept(ModBlocks.NIHIL_FERN.get());
                                                output.accept(ModBlocks.NIHIL_HERB.get());
                                                output.accept(ModBlocks.NIHIL_PETALS.get());

                                                output.accept(ModBlocks.NIHIL_SPROUT.get());
                                                output.accept(ModBlocks.DREAD_SPROUT.get());

                                                output.accept(ModBlocks.NILIGHT.get());

                                                output.accept(ModBlocks.GRAPHITE.get());

                                                output.accept(ModBlocks.VELVET_SAND.get());

                                                output.accept(ModBlocks.VELVESTONE.get());

                                                output.accept(ModBlocks.COTTONITE.get());

                                        }).build());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ABYSS = CREATIVE_MODE_TABS
                        .register("abyss", () -> CreativeModeTab.builder()
                                        .title(Component.translatable("itemGroup.abyss"))
                                        .icon(() -> new ItemStack(ModBlocks.ABYSSAL_MOSS_BLOCK.get()))
                                        .displayItems((parameters, output) -> {

                                                for (Item item : ModBlocks.ABYSSAL_MOSS_BLOCK_FAMILY
                                                                .devolverItens(true)) {
                                                        output.accept(item);
                                                }
                                                output.accept(ModBlocks.ABYSSAL_FLORAL_MOSS_BLOCK.get());

                                                output.accept(ModBlocks.ABYSSAL_MOSS_CARPET.get());
                                                output.accept(ModBlocks.ABYSSAL_FLORAL_MOSS_CARPET.get());

                                                output.accept(ModBlocks.ABYSSAL_MOSSY_STONE.get());

                                                for (Item item : ModBlocks.ABYSSAL_STONE_FAMILY.devolverItens(true)) {
                                                        output.accept(item);
                                                }

                                        }).build());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> HIVE = CREATIVE_MODE_TABS
                        .register("hive", () -> CreativeModeTab.builder()
                                        .title(Component.translatable("itemGroup.hive"))
                                        .icon(() -> new ItemStack(ModBlocks.HONEYCOMB_BLOCK_FAMILY.base.get()))
                                        .displayItems((parameters, output) -> {

                                                for (Item item : ModBlocks.HONEYCOMB_BLOCK_FAMILY.devolverItens(true)) {
                                                        output.accept(item);
                                                }
                                                output.accept(ModBlocks.OR_GATE.get());
                                                output.accept(ModBlocks.AND_GATE.get());
                                                output.accept(ModBlocks.XOR_GATE.get());

                                        }).build());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GLACIER = CREATIVE_MODE_TABS
                        .register("glacier", () -> CreativeModeTab.builder()
                                        .title(Component.translatable("itemGroup.glacier"))
                                        .icon(() -> new ItemStack(Blocks.SNOW))
                                        .displayItems((parameters, output) -> {

                                                for (Item item : ModBlocks.NYVERIN_BLOCK_FAMILY.devolverItens(true)) {
                                                        output.accept(item);
                                                }

                                        }).build());

}
