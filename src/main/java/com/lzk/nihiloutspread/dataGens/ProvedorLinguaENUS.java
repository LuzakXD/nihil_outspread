package com.lzk.nihiloutspread.dataGens;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.blockGens.*;
import com.lzk.nihiloutspread.init.ModBlocks;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ProvedorLinguaENUS extends LanguageProvider {
    public ProvedorLinguaENUS(PackOutput output, String locale) {
        super(output, NihilOutspread.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.nihil", "Nihil");
        add("itemGroup.abyss", "Abyss");
        add("itemGroup.hive", "Hive");

        add("block.nihil_outspread.nihil_dirt", "Nihil Dirt");
        add("block.nihil_outspread.nihil_grass_block", "Nihil Grass Block");
        add("block.nihil_outspread.checker_grass_block", "Checker Grass Block");

        add("block.nihil_outspread.nihil_fern", "Nihil Fern");
        add("block.nihil_outspread.nihil_short_grass", "Nihil Short Grass");
        add("block.nihil_outspread.nihil_small_herb", "Nihil Small Herb");

        add("block.nihil_outspread.velvet_sand", "Velvet Sand");

        add("block.nihil_outsprea.abyssal_moss_block", "Abyssal Moss Block");
        add("block.nihil_outsprea.abyssal_floral_moss_block", "Abyssal Floral Moss Block");

        add("block.nihil_outsprea.abyssal_moss_carpet", "Abyssal Moss Carpet");
        add("block.nihil_outsprea.abyssal_floral_moss_carpet", "Abyssal Floral Moss Carpet");

        traduzirFamiliaPedra(ModBlocks.NILIGHT_BLOCK_FAMILY, "Nilight", true);
        traduzirFamiliaPedra(ModBlocks.GRAPHITE_BLOCK_FAMILY, "Graphite", true);
        traduzirFamiliaPedra(ModBlocks.COTTONITE_BLOCK_FAMILY, "Cottonite", true);
        traduzirFamiliaPedra(ModBlocks.HONEYCOMB_BLOCK_FAMILY, "Honeycomb", false);
        traduzirFamiliaAreia(ModBlocks.VELVESTONE_BLOCK_FAMILY, "Velvestone", true);

        traduzirFamiliaPedra(ModBlocks.ABYSSAL_STONE_FAMILY, "Abyssal Stone", true);
    }

    private void traduzirFamiliaPedra(FamiliaPedra familia, String nomeMaterial, boolean temBase) {

        if (temBase)
            addBlock(familia.base, nomeMaterial);
        traduzVariantes(familia.variantesBase, nomeMaterial);

        if (familia.liso != null) {
            addBlock(familia.liso, "Smooth " + nomeMaterial);
            traduzVariantes(familia.variantesLiso, "Smooth " + nomeMaterial);
        }

        if (familia.polido != null) {
            addBlock(familia.polido, "Polished " + nomeMaterial);
            traduzVariantes(familia.variantesPolido, "Polished " + nomeMaterial);
        }

        if (familia.sufixoPolido) {
            if (familia.tijolos != null) {
                addBlock(familia.tijolos, "Polished " + nomeMaterial + " Bricks");
                traduzVariantes(familia.variantesTijolos, "Polished " + nomeMaterial + " Brick");
            }

            if (familia.ladrilhos != null) {
                addBlock(familia.ladrilhos, "Polished " + nomeMaterial + " Tiles");
                traduzVariantes(familia.variantesLadrilhos, "Polished " + nomeMaterial + " Tile");
            }

            if (familia.placas != null) {
                addBlock(familia.placas, "Polished " + nomeMaterial + " Plates");
                traduzVariantes(familia.variantesPlacas, "Polished " + nomeMaterial + " Plate");
            }
        } else {
            if (familia.tijolos != null) {
                addBlock(familia.tijolos, nomeMaterial + " Bricks");
                traduzVariantes(familia.variantesTijolos, nomeMaterial + " Brick");
            }

            if (familia.ladrilhos != null) {
                addBlock(familia.ladrilhos, nomeMaterial + " Tiles");
                traduzVariantes(familia.variantesLadrilhos, nomeMaterial + " Tile");
            }

            if (familia.placas != null) {
                addBlock(familia.placas, nomeMaterial + " Plates");
                traduzVariantes(familia.variantesPlacas, nomeMaterial + " Plate");
            }
        }

        for (var entry : familia.talhados.entrySet())
            addBlock(entry.getValue(),
                    "Chiseled " + nomeDoMaterial(entry.getKey(), nomeMaterial, familia.sufixoPolido));

        for (var entry : familia.pilares.entrySet())
            addBlock(entry.getValue(), nomeDoMaterialSingular(entry.getKey(), nomeMaterial,
                    familia.sufixoPolido) + " Pillar");

        for (var entry : familia.rachados.entrySet())
            addBlock(entry.getValue(), "Cracked " + nomeDoMaterial(entry.getKey(), nomeMaterial, familia.sufixoPolido));

        for (var entry : familia.pressoes.entrySet())
            addBlock(entry.getValue(), nomeDoMaterialSingular(entry.getKey(), nomeMaterial,
                    familia.sufixoPolido) + " Pressure Plate");

        for (var entry : familia.bototes.entrySet())
            addBlock(entry.getValue(), nomeDoMaterialSingular(entry.getKey(), nomeMaterial,
                    familia.sufixoPolido) + " Button");
    }

    private void traduzirFamiliaAreia(FamiliaAreia familia, String nomeMaterial, boolean temBase) {

        if (temBase)
            addBlock(familia.base, nomeMaterial);
        traduzVariantes(familia.variantesBase, nomeMaterial);

        if (familia.liso != null) {
            addBlock(familia.liso, "Smooth " + nomeMaterial);
            traduzVariantes(familia.variantesLiso, "Smooth " + nomeMaterial);
        }

        if (familia.pedregoso != null) {
            addBlock(familia.pedregoso, "Cobbled " + nomeMaterial);
            traduzVariantes(familia.variantesPedregoso, "Cobbled " + nomeMaterial);
        }

        if (familia.cortado != null) {
            addBlock(familia.cortado, "Cut " + nomeMaterial);
            traduzVariantes(familia.variantesCortado, "Cut " + nomeMaterial);
        }

        for (var entry : familia.talhados.entrySet())
            addBlock(entry.getValue(),
                    "Chiseled " + nomeDoMaterial(entry.getKey(), nomeMaterial, false));

        for (var entry : familia.pilares.entrySet())
            addBlock(entry.getValue(), nomeDoMaterialSingular(entry.getKey(), nomeMaterial,
                    false) + " Pillar");

        for (var entry : familia.rachados.entrySet())
            addBlock(entry.getValue(), "Cracked " + nomeDoMaterial(entry.getKey(), nomeMaterial, false));

        for (var entry : familia.pressoes.entrySet())
            addBlock(entry.getValue(), nomeDoMaterialSingular(entry.getKey(), nomeMaterial, false) + " Pressure Plate");

        for (var entry : familia.bototes.entrySet())
            addBlock(entry.getValue(), nomeDoMaterialSingular(entry.getKey(), nomeMaterial,
                    false) + " Button");
    }

    private void traduzVariantes(Variantes variantes, String nomeBase) {
        if (variantes == null)
            return;
        if (variantes.escada() != null)
            addBlock(variantes.escada(), nomeBase + " Stairs");
        if (variantes.laje() != null)
            addBlock(variantes.laje(), nomeBase + " Slab");
        if (variantes.muro() != null)
            addBlock(variantes.muro(), nomeBase + " Wall");
    }

    private String nomeDoMaterial(TipoMaterial tipo, String nomeMaterial, Boolean sufixoPolido) {
        if (sufixoPolido) {
            return switch (tipo) {
                case BASE -> nomeMaterial;
                case PEDREGOSO -> "Cobbled " + nomeMaterial;
                case LISO -> "Smooth " + nomeMaterial;
                case CORTADO -> "Cut " + nomeMaterial;
                case POLIDO -> "Polished " + nomeMaterial;
                case TIJOLOS -> "Polished " + nomeMaterial + " Bricks";
                case LADRILHOS -> "Polished " + nomeMaterial + " Tiles";
                case PLACAS -> "Polished " + nomeMaterial + " Plates";
            };
        } else {
            return switch (tipo) {
                case BASE -> nomeMaterial;
                case PEDREGOSO -> "Cobbled " + nomeMaterial;
                case LISO -> "Smooth " + nomeMaterial;
                case CORTADO -> "Cut " + nomeMaterial;
                case POLIDO -> "Polished " + nomeMaterial;
                case TIJOLOS -> nomeMaterial + " Bricks";
                case LADRILHOS -> nomeMaterial + " Tiles";
                case PLACAS -> nomeMaterial + " Plates";
            };
        }

    }

    private String nomeDoMaterialSingular(TipoMaterial tipo, String nomeMaterial, Boolean sufixoPolido) {
        if (sufixoPolido) {
            return switch (tipo) {
                case BASE -> nomeMaterial;
                case PEDREGOSO -> "Cobbled " + nomeMaterial;
                case LISO -> "Smooth " + nomeMaterial;
                case CORTADO -> "Cut " + nomeMaterial;
                case POLIDO -> "Polished " + nomeMaterial;
                case TIJOLOS -> "Polished " + nomeMaterial + " Brick";
                case LADRILHOS -> "Polished " + nomeMaterial + " Tile";
                case PLACAS -> "Polished " + nomeMaterial + " Plate";
            };
        } else {
            return switch (tipo) {
                case BASE -> nomeMaterial;
                case PEDREGOSO -> "Cobbled " + nomeMaterial;
                case LISO -> "Smooth " + nomeMaterial;
                case CORTADO -> "Cut " + nomeMaterial;
                case POLIDO -> "Polished " + nomeMaterial;
                case TIJOLOS -> nomeMaterial + " Brick";
                case LADRILHOS -> nomeMaterial + " Tile";
                case PLACAS -> nomeMaterial + " Plate";
            };
        }

    }
}