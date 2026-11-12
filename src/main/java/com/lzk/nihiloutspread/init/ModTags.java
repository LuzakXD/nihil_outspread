package com.lzk.nihiloutspread.init;

import com.lzk.nihiloutspread.NihilOutspread;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> DIRTY_BLOCKS = TagKey.create(
                Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(NihilOutspread.MODID, "dirty_blocks"));

        public static final TagKey<Block> MOSSY_BLOCKS = TagKey.create(
                Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(NihilOutspread.MODID, "mossy_blocks"));

        public static final TagKey<Block> CORRUPTIBLE_TO_NIHIL_GRASS = TagKey.create(
                Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(NihilOutspread.MODID, "corruptible_to_nihil_grass"));

        public static final TagKey<Block> CORRUPTIBLE_TO_NIHIL_DIRT = TagKey.create(
                Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(NihilOutspread.MODID, "corruptible_to_nihil_dirt"));
    }
}