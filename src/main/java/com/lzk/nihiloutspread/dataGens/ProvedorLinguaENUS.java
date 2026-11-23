package com.lzk.nihiloutspread.dataGens;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.blockGens.*;
import com.lzk.nihiloutspread.init.ModBlocks;
import com.lzk.nihiloutspread.init.ModEntities;
import com.lzk.nihiloutspread.init.ModItems;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ProvedorLinguaENUS extends LanguageProvider {
    public ProvedorLinguaENUS(PackOutput output, String locale) {
        super(output, NihilOutspread.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.nihil_outspread.nihil", "Nihil blocks");
        add("itemGroup.nihil_outspread.nihil_naturals", "Nihil naturals");
        add("itemGroup.nihil_outspread.abyss", "Abyss blocks");
        add("itemGroup.nihil_outspread.hive", "Hive blocks");
        add("itemGroup.nihil_outspread.glacier", "Glacier blocks");

        addBlock(ModBlocks.NIHIL_DIRT, "Nihil Dirt");
        addBlock(ModBlocks.NIHIL_GRASS_BLOCK, "Nihil Grass Block");
        addBlock(ModBlocks.CHECKER_GRASS_BLOCK, "Checker Grass Block");

        addBlock(ModBlocks.NIHIL_FERN, "Nihil Fern");
        addBlock(ModBlocks.NIHIL_BUSH, "Nihil Bush");
        addBlock(ModBlocks.NIHIL_HERB, "Nihil Herb");

        addBlock(ModBlocks.NIHIL_PETALS, "Nihil Petals");
        addBlock(ModBlocks.NIHIL_SPROUT, "Nihil Sprout");
        addBlock(ModBlocks.DREAD_SPROUT, "Dread Sprout");

        addBlock(ModBlocks.VELVET_SAND, "Velvet Sand");

        addBlock(ModBlocks.ABYSSAL_MOSS_BLOCK, "Abyssal Moss Block");
        addBlock(ModBlocks.ABYSSAL_FLORAL_MOSS_BLOCK, "Abyssal Floral Moss Block");

        addBlock(ModBlocks.ABYSSAL_MOSS_CARPET, "Abyssal Moss Carpet");
        addBlock(ModBlocks.ABYSSAL_FLORAL_MOSS_CARPET, "Abyssal Floral Moss Carpet");

        addBlock(ModBlocks.ABYSSAL_MOSSY_STONE, "Abyssal Mossy Stone");

        traduzirFamilia(ModBlocks.NILIGHT_FAMILY, "Nilight", true);
        traduzirFamilia(ModBlocks.GRAPHITE_FAMILY, "Graphite", true);
        traduzirFamilia(ModBlocks.SODALITE_FAMILY, "Sodalite", true);
        traduzirFamilia(ModBlocks.COTTONITE_FAMILY, "Cottonite", true);
        traduzirFamilia(ModBlocks.HONEYCOMB_FAMILY, "Honeycomb", false);
        traduzirFamilia(ModBlocks.VELVESTONE_FAMILY, "Velvestone", true);

        traduzirFamilia(ModBlocks.ABYSSAL_STONE_FAMILY, "Abyssal Stone", true);
        traduzirFamilia(ModBlocks.ABYSSAL_MOSS_FAMILY, "Abyssal Moss", false);

        traduzirFamilia(ModBlocks.NYVERIN_FAMILY, "Nyverin", true);

        traduzirFamilia(ModBlocks.SPIRAL_FAMILY, "Spiral", true);

        addBlock(ModBlocks.XOR_GATE, "XOR Redstone Gate");
        addBlock(ModBlocks.OR_GATE, "OR Redstone Gate");
        addBlock(ModBlocks.AND_GATE, "AND Redstone Gate");
        add("subtitles.nihil_outspread.honey_gate_click", "Honey redstone gate click");

        addEntityType(ModEntities.MURKLET, "Murklet");
        addItem(ModItems.MURKLET_SPAWN_EGG, "Murklet Spawn Egg");
    }

    private void traduzirFamilia(FamiliaPedra familia, String nomeMaterial, boolean temBase) {

        if (temBase)
            addBlock(familia.base, nomeMaterial);

        if (familia.liso != null)
            addBlock(familia.liso, nomeDoMaterialPlural(TipoMaterial.LISO, nomeMaterial, familia.sufixoPolido));

        if (familia.polido != null)
            addBlock(familia.polido, nomeDoMaterialPlural(TipoMaterial.POLIDO, nomeMaterial,
                    familia.sufixoPolido));

        if (familia.tijolos != null) {
            addBlock(familia.tijolos, nomeDoMaterialPlural(TipoMaterial.TIJOLOS, nomeMaterial,
                    familia.sufixoPolido));
        }

        if (familia.ladrilhos != null)
            addBlock(familia.ladrilhos, nomeDoMaterialPlural(TipoMaterial.LADRILHOS, nomeMaterial,
                    familia.sufixoPolido));

        if (familia.placas != null)
            addBlock(familia.placas, nomeDoMaterialPlural(TipoMaterial.PLACAS, nomeMaterial,
                    familia.sufixoPolido));

        traduzirVariantes(familia, nomeMaterial, familia.sufixoPolido);

    }

    private void traduzirFamilia(FamiliaAreia familia, String nomeMaterial, boolean temBase) {

        if (temBase)
            addBlock(familia.base, nomeMaterial);

        if (familia.liso != null)
            addBlock(familia.liso, nomeDoMaterialPlural(TipoMaterial.LISO, nomeMaterial, false));

        if (familia.pedregoso != null)
            addBlock(familia.pedregoso, nomeDoMaterialPlural(TipoMaterial.PEDREGOSO, nomeMaterial, false));

        if (familia.cortado != null)
            addBlock(familia.cortado, nomeDoMaterialPlural(TipoMaterial.CORTADO, nomeMaterial, false));

        traduzirVariantes(familia, nomeMaterial, false);
    }

    private void traduzirFamilia(FamiliaMadeira familia, String nomeMaterial, boolean temBase) {

        if (temBase)
            addBlock(familia.base, nomeMaterial + " Planks");

        if (familia.tronco != null)
            addBlock(familia.tronco, nomeDoMaterialPlural(TipoMaterial.TRONCO, nomeMaterial, false));

        if (familia.madeira != null)
            addBlock(familia.madeira, nomeDoMaterialPlural(TipoMaterial.MADEIRA, nomeMaterial, false));

        if (familia.folha != null)
            addBlock(familia.folha, nomeDoMaterialPlural(TipoMaterial.FOLHA, nomeMaterial, false));

        traduzirVariantes(familia, nomeMaterial, false);

    }

    private void traduzirVariantes(FamiliaAbstrata familia, String nomeBase, boolean sufixoPolido) {

        for (Variantes v : familia.variantes()) {
            if (v.descascado() != null)
                addBlock(v.descascado(), "Stripped " + nomeDoMaterialSingular(v.tipo(), nomeBase, sufixoPolido));

            if (v.escada() != null)
                addBlock(v.escada(), nomeDoMaterialSingular(v.tipo(), nomeBase, sufixoPolido) + " Stairs");

            if (v.laje() != null)
                addBlock(v.laje(), nomeDoMaterialSingular(v.tipo(), nomeBase, sufixoPolido) + " Slab");

            if (v.muro() != null)
                addBlock(v.muro(), nomeDoMaterialSingular(v.tipo(), nomeBase, sufixoPolido) + " Wall");

            if (v.cerca() != null)
                addBlock(v.cerca(), nomeDoMaterialSingular(v.tipo(), nomeBase, sufixoPolido) + " Fence");

            if (v.portao() != null)
                addBlock(v.portao(), nomeDoMaterialSingular(v.tipo(), nomeBase, sufixoPolido) + " Fence Gate");

            if (v.porta() != null)
                addBlock(v.porta(), nomeDoMaterialSingular(v.tipo(), nomeBase, sufixoPolido) + " Door");
            if (v.alcapao() != null)
                addBlock(v.alcapao(), nomeDoMaterialSingular(v.tipo(), nomeBase, sufixoPolido) + " Trapdoor");

            if (v.talhado() != null)
                addBlock(v.talhado(), "Chiseled " + nomeDoMaterialPlural(v.tipo(), nomeBase, sufixoPolido));

            if (v.pilar() != null)
                addBlock(v.pilar(), nomeDoMaterialSingular(v.tipo(), nomeBase, sufixoPolido) + " Pillar");

            if (v.rachado() != null)
                addBlock(v.rachado(), "Cracked " + nomeDoMaterialPlural(v.tipo(), nomeBase, sufixoPolido));

            if (v.pressao() != null)
                addBlock(v.pressao(), nomeDoMaterialSingular(v.tipo(), nomeBase, sufixoPolido) + " Pressure Plate");

            if (v.botao() != null)
                addBlock(v.botao(), nomeDoMaterialSingular(v.tipo(), nomeBase, sufixoPolido) + " Button");

        }
    }

    private String nomeDoMaterialPlural(TipoMaterial tipo, String nomeMaterial, Boolean sufixoPolido) {

        String s = sufixoPolido ? "Polished " : "";

        return switch (tipo) {
            case BASE -> nomeMaterial;
            case PEDREGOSO -> "Cobbled " + nomeMaterial;
            case LISO -> "Smooth " + nomeMaterial;
            case CORTADO -> "Cut " + nomeMaterial;
            case POLIDO -> "Polished " + nomeMaterial;
            case TIJOLOS -> s + nomeMaterial + " Bricks";
            case LADRILHOS -> s + nomeMaterial + " Tiles";
            case PLACAS -> s + nomeMaterial + " Plates";
            case MOSAICO -> s + nomeMaterial + " Mosaic";
            case TRONCO -> nomeMaterial + " Log";
            case MADEIRA -> nomeMaterial + " Wood";
            case FOLHA -> nomeMaterial + " Leaves";
        };

    }

    private String nomeDoMaterialSingular(TipoMaterial tipo, String nomeMaterial, Boolean sufixoPolido) {

        String s = sufixoPolido ? "Polished " : "";

        return switch (tipo) {
            case BASE -> nomeMaterial;
            case PEDREGOSO -> "Cobbled " + nomeMaterial;
            case LISO -> "Smooth " + nomeMaterial;
            case CORTADO -> "Cut " + nomeMaterial;
            case POLIDO -> "Polished " + nomeMaterial;
            case TIJOLOS -> s + nomeMaterial + " Brick";
            case LADRILHOS -> s + nomeMaterial + " Tile";
            case PLACAS -> s + nomeMaterial + " Plate";
            case MOSAICO -> s + nomeMaterial + " Mosaic";
            case TRONCO -> nomeMaterial + " Log";
            case MADEIRA -> nomeMaterial + " Wood";
            case FOLHA -> nomeMaterial + " Leaves";
        };

    }
}