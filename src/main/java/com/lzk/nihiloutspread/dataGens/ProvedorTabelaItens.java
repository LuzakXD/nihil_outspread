package com.lzk.nihiloutspread.dataGens;

import java.util.Collections;

import com.lzk.nihiloutspread.blockGens.FamiliaPedra;
import com.lzk.nihiloutspread.init.ModBlocks;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

public class ProvedorTabelaItens extends BlockLootSubProvider {

    public ProvedorTabelaItens(HolderLookup.Provider registries) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .map(Holder::value)
                .toList();
    }

    @Override
    protected void generate() {
        lootDaFamilia(ModBlocks.HONEYCOMB_BLOCK_FAMILY, false);
        lootDaFamilia(ModBlocks.GRAPHITE_BLOCK_FAMILY, true);
        lootDaFamilia(ModBlocks.COTTONITE_BLOCK_FAMILY, true);
        lootDaFamilia(ModBlocks.NILIGHT_BLOCK_FAMILY, true);
        dropSelf(ModBlocks.NIHIL_DIRT.get());
        dropOther(ModBlocks.NIHIL_GRASS_BLOCK.get(), ModBlocks.NIHIL_DIRT.get());
    }

    private void lootDaFamilia(FamiliaPedra familia, boolean temBase) {
        if (temBase) {
            dropSelf(familia.base.get());
        }

        if (familia.variantesBase != null) {
            dropSelf(familia.variantesBase.escada().get());
            add(familia.variantesBase.laje().get(), createSlabItemTable(familia.variantesBase.laje().get()));
            dropSelf(familia.variantesBase.muro().get());
        }

        if (familia.polido != null) {
            dropSelf(familia.polido.get());
            if (familia.variantesPolido != null) {
                dropSelf(familia.variantesPolido.escada().get());
                add(familia.variantesPolido.laje().get(), createSlabItemTable(familia.variantesPolido.laje().get()));
                dropSelf(familia.variantesPolido.muro().get());
            }
        }

        if (familia.tijolos != null) {
            dropSelf(familia.tijolos.get());
            if (familia.variantesTijolos != null) {
                dropSelf(familia.variantesTijolos.escada().get());
                add(familia.variantesTijolos.laje().get(), createSlabItemTable(familia.variantesTijolos.laje().get()));
                dropSelf(familia.variantesTijolos.muro().get());
            }
        }
        if (familia.tijolosPolido != null) {
            dropSelf(familia.tijolosPolido.get());
            if (familia.variantesTijolosPolido != null) {
                dropSelf(familia.variantesTijolosPolido.escada().get());
                add(familia.variantesTijolosPolido.laje().get(),
                        createSlabItemTable(familia.variantesTijolosPolido.laje().get()));
                dropSelf(familia.variantesTijolosPolido.muro().get());
            }
        }

        for (var bloco : familia.rachados.values()) {
            dropSelf(bloco.get());
        }
        if (familia.liso != null) {
            dropSelf(familia.liso.get());
            if (familia.variantesLiso != null) {
                if (familia.variantesLiso.escada() != null)
                    dropSelf(familia.variantesLiso.escada().get());
                if (familia.variantesLiso.laje() != null)
                    add(familia.variantesLiso.laje().get(),
                            createSlabItemTable(familia.variantesLiso.laje().get()));
                if (familia.variantesLiso.muro() != null)
                    dropSelf(familia.variantesLiso.muro().get());
            }
        }

        for (var bloco : familia.bototes.values()) {
            dropSelf(bloco.get());
        }

        for (var bloco : familia.pressoes.values()) {
            dropSelf(bloco.get());
        }

        for (var bloco : familia.talhados.values()) {
            dropSelf(bloco.get());
        }

        for (var pilar : familia.pilares.values()) {
            dropSelf(pilar.get());
        }
    }

}
