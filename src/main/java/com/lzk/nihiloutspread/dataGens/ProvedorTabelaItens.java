package com.lzk.nihiloutspread.dataGens;

import java.util.Collections;

import com.lzk.nihiloutspread.blockGens.*;
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
        dropOther(ModBlocks.CHECKER_GRASS_BLOCK.get(), ModBlocks.NIHIL_DIRT.get());
        dropSelf(ModBlocks.VELVET_SAND.get());
        lootDaFamilia(ModBlocks.VELVESTONE_BLOCK_FAMILY, true);

        lootDaFamilia(ModBlocks.ABYSSAL_MOSS_BLOCK_FAMILY, true);
        dropSelf(ModBlocks.ABYSSAL_FLORAL_MOSS_BLOCK.get());

        dropSelf(ModBlocks.ABYSSAL_MOSS_CARPET.get());
        dropSelf(ModBlocks.ABYSSAL_FLORAL_MOSS_CARPET.get());

        dropWhenSilkTouch(ModBlocks.NIHIL_FERN.get());
        dropWhenSilkTouch(ModBlocks.NIHIL_HERB.get());
        dropWhenSilkTouch(ModBlocks.NIHIL_BUSH.get());
        dropWhenSilkTouch(ModBlocks.NIHIL_SPROUT.get());
        dropWhenSilkTouch(ModBlocks.DREAD_SPROUT.get());

        add(ModBlocks.NIHIL_PETALS.get(), createPetalsDrops(ModBlocks.NIHIL_PETALS.get()));

        lootDaFamilia(ModBlocks.ABYSSAL_STONE_FAMILY, true);
        dropOther(ModBlocks.ABYSSAL_MOSSY_STONE.get(), ModBlocks.ABYSSAL_STONE.get());

        dropSelf(ModBlocks.XOR_GATE.get());
        dropSelf(ModBlocks.OR_GATE.get());
        dropSelf(ModBlocks.AND_GATE.get());

        lootDaFamilia(ModBlocks.NYVERIN_BLOCK_FAMILY, true);
    }

    private void lootDaFamilia(FamiliaPedra familia, boolean temBase) {
        if (temBase) {
            dropSelf(familia.base.get());
        }

        if (familia.tijolos != null) {
            dropSelf(familia.tijolos.get());
        }

        if (familia.ladrilhos != null) {
            dropSelf(familia.ladrilhos.get());
        }

        if (familia.placas != null) {
            dropSelf(familia.placas.get());
        }

        if (familia.polido != null) {
            dropSelf(familia.polido.get());
        }

        lootVariantes(familia);

        if (familia.liso != null) {
            dropSelf(familia.liso.get());
        }

    }

    private void lootDaFamilia(FamiliaMadeira familia, boolean temBase) {
        if (temBase) {
            dropSelf(familia.base.get());
        }

        if (familia.tronco != null) {
            dropSelf(familia.tronco.get());
        }

        if (familia.madeira != null) {
            dropSelf(familia.madeira.get());
        }

        if (familia.folha != null) {
            dropWhenSilkTouch(familia.folha.get());
        }

        lootVariantes(familia);

    }

    private void lootDaFamilia(FamiliaAreia familia, boolean temBase) {

        if (temBase) {
            if (familia.pedregoso != null) {
                dropOther(familia.base.get(), familia.pedregoso.get());
                dropSelf(familia.pedregoso.get());
            } else {
                dropSelf(familia.base.get());
            }
        }

        lootVariantes(familia);

        if (familia.cortado != null) {
            dropSelf(familia.cortado.get());
        }

        if (familia.liso != null) {
            dropSelf(familia.liso.get());
        }
    }

    private void lootVariantes(FamiliaAbstrata familia) {
        for (Variantes v : familia.variantes()) {
            if (v.descascado() != null)
                dropSelf(v.descascado().get());

            if (v.escada() != null)
                dropSelf(v.escada().get());

            if (v.laje() != null)
                add(v.laje().get(), createSlabItemTable(v.laje().get()));

            if (v.muro() != null)
                dropSelf(v.muro().get());

            if (v.botao() != null)
                dropSelf(v.botao().get());

            if (v.pressao() != null)
                dropSelf(v.pressao().get());

            if (v.talhado() != null)
                dropSelf(v.talhado().get());

            if (v.pilar() != null)
                dropSelf(v.pilar().get());

            if (v.rachado() != null)
                dropSelf(v.rachado().get());

            if (v.cerca() != null)
                dropSelf(v.cerca().get());

            if (v.portao() != null)
                dropSelf(v.portao().get());

            if (v.porta() != null)
                dropSelf(v.porta().get());

            if (v.alcapao() != null)
                dropSelf(v.alcapao().get());

        }
    }

}
