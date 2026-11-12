package com.lzk.nihiloutspread.dataGens;

import java.util.Collections;

import com.lzk.nihiloutspread.blockGens.FamiliaAreia;
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
        lootDaFamiliaPedra(ModBlocks.HONEYCOMB_BLOCK_FAMILY, false);
        lootDaFamiliaPedra(ModBlocks.GRAPHITE_BLOCK_FAMILY, true);
        lootDaFamiliaPedra(ModBlocks.COTTONITE_BLOCK_FAMILY, true);
        lootDaFamiliaPedra(ModBlocks.NILIGHT_BLOCK_FAMILY, true);
        dropSelf(ModBlocks.NIHIL_DIRT.get());
        dropOther(ModBlocks.NIHIL_GRASS_BLOCK.get(), ModBlocks.NIHIL_DIRT.get());
        dropOther(ModBlocks.CHECKER_GRASS_BLOCK.get(), ModBlocks.NIHIL_DIRT.get());
        dropSelf(ModBlocks.VELVET_SAND.get());
        lootDaFamiliaAreia(ModBlocks.VELVESTONE_BLOCK_FAMILY, true);

        dropSelf(ModBlocks.ABYSSAL_MOSS_BLOCK.get());
        dropSelf(ModBlocks.ABYSSAL_FLORAL_MOSS_BLOCK.get());

        dropSelf(ModBlocks.ABYSSAL_MOSS_CARPET.get());
        dropSelf(ModBlocks.ABYSSAL_FLORAL_MOSS_CARPET.get());

        dropWhenSilkTouch(ModBlocks.NIHIL_FERN.get());
        dropWhenSilkTouch(ModBlocks.NIHIL_SMALL_HERB.get());
        dropWhenSilkTouch(ModBlocks.NIHIL_SHORT_GRASS.get());

        lootDaFamiliaPedra(ModBlocks.ABYSSAL_STONE_FAMILY, true);
    }

    private void lootDaFamiliaPedra(FamiliaPedra familia, boolean temBase) {
        if (temBase) {
            dropSelf(familia.base.get());
        }

        if (familia.variantesBase != null) {
            if (familia.variantesBase.escada() != null) {
                dropSelf(familia.variantesBase.escada().get());
            }
            if (familia.variantesBase.laje() != null) {
                add(familia.variantesBase.laje().get(), createSlabItemTable(familia.variantesBase.laje().get()));
            }
            if (familia.variantesBase.muro() != null) {
                dropSelf(familia.variantesBase.muro().get());
            }
        }

        if (familia.tijolos != null) {
            dropSelf(familia.tijolos.get());
            if (familia.variantesTijolos != null) {
                if (familia.variantesTijolos.escada() != null) {
                    dropSelf(familia.variantesTijolos.escada().get());
                }
                if (familia.variantesTijolos.laje() != null) {
                    add(familia.variantesTijolos.laje().get(),
                            createSlabItemTable(familia.variantesTijolos.laje().get()));
                }
                if (familia.variantesTijolos.muro() != null) {
                    dropSelf(familia.variantesTijolos.muro().get());
                }
            }
        }

        if (familia.ladrilhos != null) {
            dropSelf(familia.ladrilhos.get());
            if (familia.variantesLadrilhos != null) {
                if (familia.variantesLadrilhos.escada() != null) {
                    dropSelf(familia.variantesLadrilhos.escada().get());
                }
                if (familia.variantesLadrilhos.laje() != null) {
                    add(familia.variantesLadrilhos.laje().get(),
                            createSlabItemTable(familia.variantesLadrilhos.laje().get()));
                }
                if (familia.variantesLadrilhos.muro() != null) {
                    dropSelf(familia.variantesLadrilhos.muro().get());
                }
            }
        }

        if (familia.placas != null) {
            dropSelf(familia.placas.get());
            if (familia.variantesPlacas != null) {
                if (familia.variantesPlacas.escada() != null) {
                    dropSelf(familia.variantesPlacas.escada().get());
                }
                if (familia.variantesPlacas.laje() != null) {
                    add(familia.variantesPlacas.laje().get(),
                            createSlabItemTable(familia.variantesPlacas.laje().get()));
                }
                if (familia.variantesPlacas.muro() != null) {
                    dropSelf(familia.variantesPlacas.muro().get());
                }
            }
        }

        if (familia.polido != null) {
            dropSelf(familia.polido.get());
            if (familia.variantesPolido != null) {
                if (familia.variantesPolido.escada() != null) {
                    dropSelf(familia.variantesPolido.escada().get());
                }
                if (familia.variantesPolido.laje() != null) {
                    add(familia.variantesPolido.laje().get(),
                            createSlabItemTable(familia.variantesPolido.laje().get()));
                }
                if (familia.variantesPolido.muro() != null) {
                    dropSelf(familia.variantesPolido.muro().get());
                }
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

    private void lootDaFamiliaAreia(FamiliaAreia familia, boolean temBase) {

        if (temBase) {
            if (familia.pedregoso != null) {
                dropOther(familia.base.get(), familia.pedregoso.get());
                dropSelf(familia.pedregoso.get());
            } else {
                dropSelf(familia.base.get());
            }
        }

        if (familia.variantesBase != null) {
            if (familia.variantesBase.escada() != null) {
                dropSelf(familia.variantesBase.escada().get());
            }
            if (familia.variantesBase.laje() != null) {
                add(familia.variantesBase.laje().get(), createSlabItemTable(familia.variantesBase.laje().get()));
            }
            if (familia.variantesBase.muro() != null) {
                dropSelf(familia.variantesBase.muro().get());
            }
        }

        if (familia.pedregoso != null) {
            if (familia.variantesPedregoso != null) {
                if (familia.variantesPedregoso.escada() != null) {
                    dropSelf(familia.variantesPedregoso.escada().get());
                }
                if (familia.variantesPedregoso.laje() != null) {
                    add(familia.variantesPedregoso.laje().get(),
                            createSlabItemTable(familia.variantesPedregoso.laje().get()));
                }
                if (familia.variantesPedregoso.muro() != null) {
                    dropSelf(familia.variantesPedregoso.muro().get());
                }
            }
        }

        if (familia.cortado != null) {
            dropSelf(familia.cortado.get());
            if (familia.variantesCortado != null) {
                if (familia.variantesCortado.escada() != null) {
                    dropSelf(familia.variantesCortado.escada().get());
                }
                if (familia.variantesCortado.laje() != null) {
                    add(familia.variantesCortado.laje().get(),
                            createSlabItemTable(familia.variantesCortado.laje().get()));
                }
                if (familia.variantesCortado.muro() != null) {
                    dropSelf(familia.variantesCortado.muro().get());
                }
            }
        }

        if (familia.liso != null) {
            dropSelf(familia.liso.get());
            if (familia.variantesLiso != null) {
                if (familia.variantesLiso.escada() != null) {
                    dropSelf(familia.variantesLiso.escada().get());
                }
                if (familia.variantesLiso.laje() != null) {
                    add(familia.variantesLiso.laje().get(), createSlabItemTable(familia.variantesLiso.laje().get()));
                }
                if (familia.variantesLiso.muro() != null) {
                    dropSelf(familia.variantesLiso.muro().get());
                }
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
