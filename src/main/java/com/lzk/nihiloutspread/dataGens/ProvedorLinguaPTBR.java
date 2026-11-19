package com.lzk.nihiloutspread.dataGens;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.blockGens.*;
import com.lzk.nihiloutspread.init.ModBlocks;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ProvedorLinguaPTBR extends LanguageProvider {
    public ProvedorLinguaPTBR(PackOutput output, String locale) {
        super(output, NihilOutspread.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.nihil", "Blocos do Nihil");
        add("itemGroup.nihil_naturals", "Nihil naturais");
        add("itemGroup.abyss", "Blocos do Abismo");
        add("itemGroup.hive", "Bloco da Colmeia");
        add("itemGroup.glacier", "Blocos da Geleira");

        addBlock(ModBlocks.NIHIL_DIRT, "Terra Nihil");
        addBlock(ModBlocks.NIHIL_GRASS_BLOCK, "Bloco de Grama Nihil");
        addBlock(ModBlocks.CHECKER_GRASS_BLOCK, "Bloco de Grama Xadrez");

        addBlock(ModBlocks.NIHIL_FERN, "Samambaia Nihil");
        addBlock(ModBlocks.NIHIL_BUSH, "Arbusto Nihil");
        addBlock(ModBlocks.NIHIL_HERB, "Erva Nihil");

        addBlock(ModBlocks.NIHIL_PETALS, "Pétalas Nihil");
        addBlock(ModBlocks.NIHIL_SPROUT, "Broto Nihil");
        addBlock(ModBlocks.DREAD_SPROUT, "Broto Terrível");

        addBlock(ModBlocks.VELVET_SAND, "Areia de Veludo");

        addBlock(ModBlocks.ABYSSAL_MOSS_BLOCK, "Bloco de Musgo Abissal");
        addBlock(ModBlocks.ABYSSAL_FLORAL_MOSS_BLOCK, "Bloco de Musgo Abissal Florido");

        addBlock(ModBlocks.ABYSSAL_MOSS_CARPET, "Tapete de Musgo Abissal");
        addBlock(ModBlocks.ABYSSAL_FLORAL_MOSS_CARPET, "Tapete de Musgo Abissal Florido");

        addBlock(ModBlocks.ABYSSAL_MOSSY_STONE, "Pedra Abissal Musgosa");

        traduzirFamilia(ModBlocks.NILIGHT_BLOCK_FAMILY, "Niluzita", Genero.FEMININO, true);
        traduzirFamilia(ModBlocks.GRAPHITE_BLOCK_FAMILY, "Grafite", Genero.MASCULINO, true);
        traduzirFamilia(ModBlocks.COTTONITE_BLOCK_FAMILY, "Algonita", Genero.FEMININO, true);
        traduzirFamilia(ModBlocks.HONEYCOMB_BLOCK_FAMILY, "Favo de Mel", Genero.MASCULINO, false);
        traduzirFamilia(ModBlocks.VELVESTONE_BLOCK_FAMILY, "Velveta", Genero.FEMININO, true);

        traduzirFamilia(ModBlocks.ABYSSAL_STONE_FAMILY, "Pedra Abissal", Genero.FEMININO, true);
        traduzirFamilia(ModBlocks.ABYSSAL_MOSS_BLOCK_FAMILY, "Musgo Abissal", Genero.NEUTRO, false);

        traduzirFamilia(ModBlocks.NYVERIN_BLOCK_FAMILY, "Niverina", Genero.FEMININO, true);

        add(ModBlocks.XOR_GATE.get(), "Porta de Redstone XOR");
        add(ModBlocks.OR_GATE.get(), "Porta de Redstone OR");
        add(ModBlocks.AND_GATE.get(), "Porta de Redstone AND");
        add("subtitles.nihil_outspread.honey_gate_click", "Porta de redstone de mel acionada");
    }

    private void traduzirFamilia(FamiliaPedra familia, String nomeMaterial, Genero genero, boolean temBase) {

        if (temBase)
            addBlock(familia.base, nomeMaterial);

        if (familia.liso != null)
            addBlock(familia.liso, nomeDoMaterial(TipoMaterial.LISO, nomeMaterial, genero, familia.sufixoPolido));

        if (familia.tijolos != null)
            addBlock(familia.tijolos, nomeDoMaterial(TipoMaterial.TIJOLOS, nomeMaterial, genero, familia.sufixoPolido));

        if (familia.ladrilhos != null)
            addBlock(familia.ladrilhos,
                    nomeDoMaterial(TipoMaterial.LADRILHOS, nomeMaterial, genero, familia.sufixoPolido));

        if (familia.placas != null)
            addBlock(familia.placas, nomeDoMaterial(TipoMaterial.PLACAS, nomeMaterial, genero, familia.sufixoPolido));

        if (familia.polido != null)
            addBlock(familia.polido, nomeDoMaterial(TipoMaterial.POLIDO, nomeMaterial, genero, familia.sufixoPolido));

        traduzirVariantes(familia, nomeMaterial, genero, familia.sufixoPolido);
    }

    private void traduzirFamilia(FamiliaAreia familia, String nomeMaterial, Genero genero, boolean temBase) {

        if (temBase)
            addBlock(familia.base, nomeMaterial);

        if (familia.liso != null)
            addBlock(familia.liso, nomeDoMaterial(TipoMaterial.LISO, nomeMaterial, genero, false));

        if (familia.pedregoso != null)
            addBlock(familia.pedregoso, nomeDoMaterial(TipoMaterial.PEDREGOSO, nomeMaterial, genero, false));

        if (familia.cortado != null)
            addBlock(familia.cortado, nomeDoMaterial(TipoMaterial.CORTADO, nomeMaterial, genero, false));

        traduzirVariantes(familia, nomeMaterial, genero, false);
    }

    private void traduzirFamilia(FamiliaMadeira familia, String nomeMaterial, Genero genero, boolean temBase) {

        if (temBase)
            addBlock(familia.base, "Tábuas de " + nomeMaterial);

        if (familia.tronco != null)
            addBlock(familia.tronco, "Tronco de " + nomeMaterial);

        if (familia.madeira != null)
            addBlock(familia.madeira, "Madeira de " + nomeMaterial);

        if (familia.folha != null)
            addBlock(familia.folha, "Folhas de " + nomeMaterial);

        traduzirVariantes(familia, nomeMaterial, genero, false);
    }

    private void traduzirVariantes(FamiliaAbstrata familia, String nomeBase, Genero genero, boolean sufixoPolido) {
        for (Variantes v : familia.variantes()) {
            if (v.descascado() != null)
                addBlock(v.descascado(), nomeDoMaterialDescascado(v.tipo(), nomeBase, genero, sufixoPolido));

            if (v.escada() != null)
                addBlock(v.escada(),
                        "Escadas de " + nomeDoMaterial(v.tipo(), nomeBase, genero, sufixoPolido));

            if (v.laje() != null)
                addBlock(v.laje(),
                        "Laje de " + nomeDoMaterial(v.tipo(), nomeBase, genero, sufixoPolido));

            if (v.muro() != null)
                addBlock(v.muro(),
                        "Muro de " + nomeDoMaterial(v.tipo(), nomeBase, genero, sufixoPolido));

            if (v.cerca() != null)
                addBlock(v.cerca(),
                        "Cerca de " + nomeDoMaterial(v.tipo(), nomeBase, genero, sufixoPolido));

            if (v.portao() != null)
                addBlock(v.portao(),
                        "Portão de " + nomeDoMaterial(v.tipo(), nomeBase, genero, sufixoPolido));

            if (v.porta() != null)
                addBlock(v.porta(),
                        "Porta de " + nomeDoMaterial(v.tipo(), nomeBase, genero, sufixoPolido));
            if (v.alcapao() != null)
                addBlock(v.alcapao(),
                        "Alçapão de " + nomeDoMaterial(v.tipo(), nomeBase, genero, sufixoPolido));

            if (v.talhado() != null)
                addBlock(v.talhado(),
                        nomeDoMaterialaTalhado(v.tipo(), nomeBase, genero, sufixoPolido));

            if (v.pilar() != null)
                addBlock(v.pilar(),
                        "Pilar de " + nomeDoMaterial(v.tipo(), nomeBase, genero, sufixoPolido));

            if (v.rachado() != null)
                addBlock(v.rachado(),
                        nomeDoMaterialaRachado(v.tipo(), nomeBase, genero, sufixoPolido));

            if (v.pressao() != null)
                addBlock(v.pressao(),
                        "Placa de Pressão de " + nomeDoMaterial(v.tipo(), nomeBase, genero, sufixoPolido));

            if (v.botao() != null)
                addBlock(v.botao(),
                        "Botão de " + nomeDoMaterial(v.tipo(), nomeBase, genero, sufixoPolido));

        }
    }

    private String pronome(Genero genero) {
        return switch (genero) {
            case FEMININO -> "a";
            case MASCULINO -> "o";
            case NEUTRO -> "e";
        };
    }

    private String nomeDoMaterial(TipoMaterial tipo, String nomeMaterial, Genero genero, Boolean sufixoPolido) {
        String p = pronome(genero); // how woke huh???
        String s = sufixoPolido ? " Polid" + p : "";

        return switch (tipo) {
            case BASE -> nomeMaterial;
            case PEDREGOSO -> "Pedregulho de " + nomeMaterial;
            case LISO -> nomeMaterial + " Lis" + p;
            case CORTADO -> nomeMaterial + " Lapidad" + p;
            case POLIDO -> nomeMaterial + " Polid" + p;
            case TIJOLOS -> "Tijolos de " + nomeMaterial + s;
            case LADRILHOS -> "Ladrilhos de " + nomeMaterial + s;
            case PLACAS -> "Placas de " + nomeMaterial + s;
            case MOSAICO -> "Mosaico de " + nomeMaterial + s;
            case TRONCO -> "Tronco de " + nomeMaterial;
            case MADEIRA -> "Madeira de " + nomeMaterial;
            case FOLHA -> "Folhas de " + nomeMaterial;
        };
    }

    private String nomeDoMaterialaTalhado(TipoMaterial tipo, String nomeMaterial, Genero genero,
            Boolean sufixoPolido) {
        String p = pronome(genero);
        String s = sufixoPolido ? " Polid" + p : "";

        return switch (tipo) {
            case BASE -> nomeMaterial + " Talhad" + p;
            case PEDREGOSO -> "Pedregulho de " + nomeMaterial + " Talhado";
            case LISO -> nomeMaterial + " Lis" + p;
            case CORTADO -> nomeMaterial + " Lapidad" + p;
            case POLIDO -> nomeMaterial + " Polid" + p + " Talhad" + p;
            case TIJOLOS -> "Tijolos de " + nomeMaterial + s + " Talhados";
            case LADRILHOS -> "Ladrilhos de " + nomeMaterial + s + " Talhados";
            case PLACAS -> "Placas de " + nomeMaterial + s + " Talhadas";
            case MOSAICO -> "Mosaico de " + nomeMaterial + s + " Talhado";
            case TRONCO -> "Tronco de " + nomeMaterial;
            case MADEIRA -> "Madeira de " + nomeMaterial;
            case FOLHA -> "Folhas de " + nomeMaterial;
        };
    }

    private String nomeDoMaterialaRachado(TipoMaterial tipo, String nomeMaterial, Genero genero,
            Boolean sufixoPolido) {
        String p = pronome(genero);
        String s = sufixoPolido ? " Polid" + p : "";

        return switch (tipo) {
            case BASE -> nomeMaterial + " Rachad" + p;
            case PEDREGOSO -> "Pedregulho de " + nomeMaterial + " Rachado";
            case LISO -> nomeMaterial + " Lis" + p;
            case CORTADO -> nomeMaterial + " Lapidad" + p;
            case POLIDO -> nomeMaterial + " Polid" + p + " Rachad" + p;
            case TIJOLOS -> "Tijolos de " + nomeMaterial + s + " Rachados";
            case LADRILHOS -> "Ladrilhos de " + nomeMaterial + s + " Rachados";
            case PLACAS -> "Placas de " + nomeMaterial + s + " Rachadas";
            case MOSAICO -> "Mosaico de " + nomeMaterial;
            case TRONCO -> "Tronco de " + nomeMaterial;
            case MADEIRA -> "Madeira de " + nomeMaterial;
            case FOLHA -> "Folhas de " + nomeMaterial;
        };
    }

    private String nomeDoMaterialDescascado(TipoMaterial tipo, String nomeMaterial, Genero genero,
            Boolean sufixoPolido) {
        return switch (tipo) {
            case BASE -> null;
            case PEDREGOSO -> null;
            case LISO -> null;
            case CORTADO -> null;
            case POLIDO -> null;
            case TIJOLOS -> null;
            case LADRILHOS -> null;
            case PLACAS -> null;
            case MOSAICO -> null;
            case TRONCO -> "Tronco de " + nomeMaterial + " Descascado";
            case MADEIRA -> "Madeira de " + nomeMaterial + " Descascada";
            case FOLHA -> null;
        };
    }
}