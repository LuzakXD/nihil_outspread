package com.lzk.nihiloutspread.dataGens;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.blockGens.FamiliaPedra;
import com.lzk.nihiloutspread.blockGens.TipoMaterialPedra;
import com.lzk.nihiloutspread.blockGens.Variantes;
import com.lzk.nihiloutspread.init.ModBlocks;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ProvedorLinguaENUS extends LanguageProvider {
    public ProvedorLinguaENUS(PackOutput output, String locale) {
        super(output, NihilOutspread.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.nihiloutspread", "Nihil Outspread"); // título da aba
        traduzirFamilia(ModBlocks.NILIGHT_BLOCK_FAMILY, "Nilight", true);
        traduzirFamilia(ModBlocks.GRAPHITE_BLOCK_FAMILY, "Graphite", true);
        traduzirFamilia(ModBlocks.COTTONITE_BLOCK_FAMILY, "Cottonite", true);
        traduzirFamilia(ModBlocks.HONEYCOMB_BLOCK_FAMILY, "Honeycomb", false);
    }

    private void traduzirFamilia(FamiliaPedra familia, String nomeMaterial, boolean temBase) {

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

        if (familia.tijolos != null) {
            addBlock(familia.tijolos, nomeMaterial + " Bricks");
            traduzVariantes(familia.variantesTijolos, nomeMaterial + " Brick");
        }

        if (familia.tijolosPolido != null) {
            addBlock(familia.tijolosPolido, "Polished " + nomeMaterial + " Bricks");
            traduzVariantes(familia.variantesTijolosPolido, "Polished " + nomeMaterial + " Brick");
        }

        // Maps:
        for (var entry : familia.talhados.entrySet())
            addBlock(entry.getValue(), "Chiseled " + nomeDoMaterial(entry.getKey(), nomeMaterial));

        for (var entry : familia.pilares.entrySet())
            addBlock(entry.getValue(), nomeDoMaterialSingular(entry.getKey(), nomeMaterial) + " Pillar");

        for (var entry : familia.rachados.entrySet())
            addBlock(entry.getValue(), "Cracked " + nomeDoMaterial(entry.getKey(), nomeMaterial));

        for (var entry : familia.pressoes.entrySet())
            addBlock(entry.getValue(), nomeDoMaterialSingular(entry.getKey(), nomeMaterial) + " Pressure Plate");

        for (var entry : familia.bototes.entrySet())
            addBlock(entry.getValue(), nomeDoMaterialSingular(entry.getKey(), nomeMaterial) + " Button");
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

    private String nomeDoMaterial(TipoMaterialPedra tipo, String nomeMaterial) {
        return switch (tipo) {
            case BASE -> nomeMaterial;
            case LISO -> "Smooth " + nomeMaterial;
            case POLIDO -> "Polished " + nomeMaterial;
            case TIJOLOS -> nomeMaterial + " Bricks";
            case TIJOLOS_POLIDO -> "Polished " + nomeMaterial + " Bricks";
            case LADRILHOS -> nomeMaterial + " Tiles";
            case PLACAS -> nomeMaterial + " Plates";
        };
    }

    private String nomeDoMaterialSingular(TipoMaterialPedra tipo, String nomeMaterial) {
        return switch (tipo) {
            case BASE -> nomeMaterial;
            case LISO -> "Smooth " + nomeMaterial;
            case POLIDO -> "Polished " + nomeMaterial;
            case TIJOLOS -> nomeMaterial + " Brick";
            case TIJOLOS_POLIDO -> "Polished " + nomeMaterial + " Brick";
            case LADRILHOS -> nomeMaterial + " Tile";
            case PLACAS -> nomeMaterial + " Plate";
        };
    }
}