package com.lzk.nihiloutspread.dataGens;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.blockGens.FamiliaPedra;
import com.lzk.nihiloutspread.init.ModBlocks;
import com.lzk.nihiloutspread.init.ModTags;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ProvedorTagsBloco extends BlockTagsProvider {

    public ProvedorTagsBloco(PackOutput output, CompletableFuture<Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, NihilOutspread.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        tagMuros(ModBlocks.HONEYCOMB_BLOCK_FAMILY);
        tagMuros(ModBlocks.GRAPHITE_BLOCK_FAMILY);
        tagMuros(ModBlocks.COTTONITE_BLOCK_FAMILY);
        tagMuros(ModBlocks.NILIGHT_BLOCK_FAMILY);
        tagBotaoPressao(ModBlocks.NILIGHT_BLOCK_FAMILY, true);
        tag(ModTags.Blocks.DIRTY_BLOCKS).add(
                ModBlocks.NIHIL_DIRT.get(),
                ModBlocks.NIHIL_GRASS_BLOCK.get());

    }

    private void tagMuros(FamiliaPedra familia) {
        if (familia.variantesBase != null && familia.variantesBase.muro() != null) {
            tag(BlockTags.WALLS).add(familia.variantesBase.muro().get());
        }
        if (familia.variantesPolido != null && familia.variantesPolido.muro() != null) {
            tag(BlockTags.WALLS).add(familia.variantesPolido.muro().get());
        }
        if (familia.variantesTijolos != null && familia.variantesTijolos.muro() != null) {
            tag(BlockTags.WALLS).add(familia.variantesTijolos.muro().get());
        }
        if (familia.variantesTijolosPolido != null && familia.variantesTijolosPolido.muro() != null) {
            tag(BlockTags.WALLS).add(familia.variantesTijolosPolido.muro().get());
        }

        if (familia.variantesLiso != null && familia.variantesLiso.muro() != null) {
            tag(BlockTags.WALLS).add(familia.variantesLiso.muro().get());
        }
    }

    private void tagBotaoPressao(FamiliaPedra familia, boolean tipoPedra) {

        if (tipoPedra) {
            for (var bloco : familia.pressoes.values()) {
                tag(BlockTags.PRESSURE_PLATES).add(bloco.get());
                tag(BlockTags.STONE_PRESSURE_PLATES).add(bloco.get());
            }

            for (var bloco : familia.bototes.values()) {
                tag(BlockTags.BUTTONS).add(bloco.get());
                tag(BlockTags.STONE_BUTTONS).add(bloco.get());
            }
        } else {
            for (var bloco : familia.pressoes.values()) {
                tag(BlockTags.PRESSURE_PLATES).add(bloco.get());
                tag(BlockTags.WOODEN_PRESSURE_PLATES).add(bloco.get());
            }

            for (var bloco : familia.bototes.values()) {
                tag(BlockTags.BUTTONS).add(bloco.get());
                tag(BlockTags.WOODEN_BUTTONS).add(bloco.get());
            }
        }

    }
}
