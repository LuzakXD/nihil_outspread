package com.lzk.nihiloutspread.dataGens;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.blockGens.*;
import com.lzk.nihiloutspread.init.ModBlocks;
import com.lzk.nihiloutspread.init.ModTags;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ProvedorTagsBloco extends BlockTagsProvider {

    public ProvedorTagsBloco(PackOutput output, CompletableFuture<Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, NihilOutspread.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        tagVariantes(ModBlocks.HONEYCOMB_FAMILY);
        tagVariantes(ModBlocks.GRAPHITE_FAMILY);
        tagVariantes(ModBlocks.SODALITE_FAMILY);
        tagVariantes(ModBlocks.COTTONITE_FAMILY);
        tagVariantes(ModBlocks.NILIGHT_FAMILY);
        tagVariantes(ModBlocks.VELVESTONE_FAMILY);

        tagVariantes(ModBlocks.ABYSSAL_STONE_FAMILY);

        tagVariantes(ModBlocks.NYVERIN_FAMILY);
        tagVariantes(ModBlocks.SPIRAL_FAMILY);

        tag(ModTags.Blocks.CORRUPTIBLE_TO_NIHIL_GRASS).add(
                ModBlocks.NIHIL_DIRT.get(),
                Blocks.GRASS_BLOCK,
                Blocks.PODZOL,
                Blocks.MYCELIUM);

        tag(ModTags.Blocks.CORRUPTIBLE_TO_NIHIL_DIRT).add(
                Blocks.DIRT,
                Blocks.COARSE_DIRT,
                Blocks.ROOTED_DIRT);

        tag(ModTags.Blocks.NIHIL_BUSHES_MAY_PLACE_ON).addTag(ModTags.Blocks.DIRTY_BLOCKS);

        tag(ModTags.Blocks.NIHIL_BUSHES).add(
                ModBlocks.NIHIL_BUSH.get(),
                ModBlocks.NIHIL_FERN.get(),
                ModBlocks.NIHIL_HERB.get(),
                ModBlocks.NIHIL_SPROUT.get(),
                ModBlocks.DREAD_SPROUT.get(),
                ModBlocks.NIHIL_PETALS.get());

        tag(ModTags.Blocks.BASE_STONE_NIHIL).add(
                ModBlocks.NILIGHT.get(),
                ModBlocks.GRAPHITE.get(),
                ModBlocks.SODALITE.get(),
                ModBlocks.COTTONITE.get(),
                ModBlocks.ABYSSAL_STONE.get(),
                ModBlocks.ABYSSAL_MOSSY_STONE.get());

        tag(ModTags.Blocks.DIRTY_BLOCKS).add(
                ModBlocks.NIHIL_DIRT.get(),
                ModBlocks.NIHIL_GRASS_BLOCK.get(),
                ModBlocks.CHECKER_GRASS_BLOCK.get());

        tag(ModTags.Blocks.MOSSY_BLOCKS).add(
                ModBlocks.ABYSSAL_MOSS_BLOCK.get(),
                ModBlocks.ABYSSAL_MOSS_CARPET.get(),
                ModBlocks.ABYSSAL_FLORAL_MOSS_BLOCK.get(),
                ModBlocks.ABYSSAL_FLORAL_MOSS_CARPET.get());

        tag(BlockTags.REPLACEABLE).addTag(ModTags.Blocks.NIHIL_BUSHES);
        tag(BlockTags.ENCHANTMENT_POWER_TRANSMITTER).addTag(ModTags.Blocks.NIHIL_BUSHES);
        tag(BlockTags.SWORD_EFFICIENT).addTag(ModTags.Blocks.NIHIL_BUSHES);

    }

    private static TagKey<Block> botaoTag = BlockTags.STONE_BUTTONS;
    private static TagKey<Block> pressaoTag = BlockTags.STONE_PRESSURE_PLATES;

    private void tagVariantes(FamiliaAbstrata familia) {

        boolean madeira = familia.tipoFamilia == TipoFamilia.MADEIRA;

        TagKey<Block> botaoTag = madeira ? BlockTags.WOODEN_BUTTONS : BlockTags.STONE_BUTTONS;
        TagKey<Block> pressaoTag = madeira ? BlockTags.WOODEN_PRESSURE_PLATES : BlockTags.STONE_PRESSURE_PLATES;

        if (madeira)
            tagMadeira((FamiliaMadeira) familia);

        for (Variantes v : familia.variantes()) {
            if (v.muro() != null) {
                tag(BlockTags.WALLS).add(v.muro().get());
            }

            if (v.cerca() != null) {
                tag(BlockTags.FENCES).add(v.cerca().get());
                tag(BlockTags.WOODEN_FENCES).add(v.cerca().get());
            }

            if (v.portao() != null) {
                tag(BlockTags.FENCE_GATES).add(v.portao().get());
            }

            if (v.botao() != null) {
                tag(BlockTags.BUTTONS).add(v.botao().get());
                tag(botaoTag).add(v.botao().get());
            }

            if (v.pressao() != null) {
                tag(BlockTags.PRESSURE_PLATES).add(v.pressao().get());
                tag(pressaoTag).add(v.pressao().get());
            }

            if (v.descascado() != null) {
                tag(BlockTags.LOGS).add(v.descascado().get());
                tag(BlockTags.LOGS_THAT_BURN).add(v.descascado().get());
                tag(BlockTags.MINEABLE_WITH_AXE).add(v.descascado().get());
            }
        }
    }

    private void tagMadeira(FamiliaMadeira familia) {
        if (familia.tronco != null) {
            tag(BlockTags.LOGS).add(familia.tronco.get());
            tag(BlockTags.LOGS_THAT_BURN).add(familia.tronco.get());
            tag(BlockTags.MINEABLE_WITH_AXE).add(familia.tronco.get());
        }

        if (familia.madeira != null) {
            tag(BlockTags.LOGS).add(familia.madeira.get());
            tag(BlockTags.LOGS_THAT_BURN).add(familia.madeira.get());
            tag(BlockTags.MINEABLE_WITH_AXE).add(familia.madeira.get());
        }

        if (familia.folha != null) {
            tag(BlockTags.LEAVES).add(familia.folha.get());
            tag(BlockTags.SWORD_EFFICIENT).add(familia.folha.get());
            tag(BlockTags.MINEABLE_WITH_HOE).add(familia.folha.get());
        }
    }
}
