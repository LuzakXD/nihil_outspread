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
        add("itemGroup.nihil", "Nihil");
        add("itemGroup.abyss", "Abismo");
        add("itemGroup.hive", "Colmeia");

        add("block.nihil_outspread.nihil_dirt", "Terra Nihil");
        add("block.nihil_outspread.nihil_grass_block", "Bloco de Grama Nihil");
        add("block.nihil_outspread.checker_grass_block", "Bloco de Grama Xadrez");

        add("block.nihil_outspread.nihil_fern", "Samambaia Nihil");
        add("block.nihil_outspread.nihil_short_grass", "Grama Nihil Baixa");
        add("block.nihil_outspread.nihil_small_herb", "Pequena Erva Nihil");

        add("block.nihil_outspread.velvet_sand", "Areia de Veludo");

        add("block.nihil_outspread.abyssal_moss_block", "Bloco de Musgo Abissal");
        add("block.nihil_outspread.abyssal_floral_moss_block", "Bloco de Musgo Abissal Florido");

        add("block.nihil_outspread.abyssal_moss_carpet", "Tapete de Musgo Abissal");
        add("block.nihil_outspread.abyssal_floral_moss_carpet", "Tapete de Musgo Abissal Florido");

        add("block.nihil_outspread.abyssal_mossy_stone", "Pedra Abissal Musgosa");

        traduzirFamiliaPedra(ModBlocks.NILIGHT_BLOCK_FAMILY, "Niluzita", Genero.FEMININO, true);
        traduzirFamiliaPedra(ModBlocks.GRAPHITE_BLOCK_FAMILY, "Grafite", Genero.MASCULINO, true);
        traduzirFamiliaPedra(ModBlocks.COTTONITE_BLOCK_FAMILY, "Algonita", Genero.FEMININO, true);
        traduzirFamiliaPedra(ModBlocks.HONEYCOMB_BLOCK_FAMILY, "Favo de Mel", Genero.MASCULINO, false);
        traduzirFamiliaPedraAreia(ModBlocks.VELVESTONE_BLOCK_FAMILY, "Velveta", Genero.FEMININO, true);

        traduzirFamiliaPedra(ModBlocks.ABYSSAL_STONE_FAMILY, "Pedra Abissal", Genero.FEMININO, true);
        traduzirFamiliaPedra(ModBlocks.ABYSSAL_MOSS_BLOCK_FAMILY, "Musgo Abissal", Genero.NEUTRO, false);
    }

    private void traduzirFamiliaPedra(FamiliaPedra familia, String nomeMaterial, Genero genero, boolean temBase) {

        if (temBase)
            addBlock(familia.base, nomeMaterial);
        traduzVariantes(familia.variantesBase, nomeMaterial);

        if (familia.liso != null) {
            String nome = nomeDoMaterial(TipoMaterial.LISO, nomeMaterial, genero, familia.sufixoPolido);
            addBlock(familia.liso, nome);
            traduzVariantes(familia.variantesLiso, nome);
        }

        if (familia.tijolos != null) {
            String nome = nomeDoMaterial(TipoMaterial.TIJOLOS, nomeMaterial, genero, familia.sufixoPolido);
            addBlock(familia.tijolos, nome);
            traduzVariantes(familia.variantesTijolos, nome);
        }

        if (familia.ladrilhos != null) {
            String nome = nomeDoMaterial(TipoMaterial.LADRILHOS, nomeMaterial, genero, familia.sufixoPolido);
            addBlock(familia.ladrilhos, nome);
            traduzVariantes(familia.variantesLadrilhos, nome);
        }

        if (familia.placas != null) {
            String nome = nomeDoMaterial(TipoMaterial.PLACAS, nomeMaterial, genero, familia.sufixoPolido);
            addBlock(familia.placas, nome);
            traduzVariantes(familia.variantesPlacas, nome);
        }

        if (familia.polido != null) {
            String nome = nomeDoMaterial(TipoMaterial.POLIDO, nomeMaterial, genero, familia.sufixoPolido);
            addBlock(familia.polido, nome);
            traduzVariantes(familia.variantesPolido, nome);
        }

        for (var entry : familia.talhados.entrySet())
            addBlock(entry.getValue(), nomeDoMaterialTalhado(entry.getKey(), nomeMaterial, genero,
                    familia.sufixoPolido));

        for (var entry : familia.pilares.entrySet())
            addBlock(entry.getValue(), "Pilar de " + nomeDoMaterial(entry.getKey(), nomeMaterial, genero,
                    familia.sufixoPolido));

        for (var entry : familia.rachados.entrySet())
            addBlock(entry.getValue(), nomeDoMaterialRachado(entry.getKey(), nomeMaterial, genero,
                    familia.sufixoPolido));

        for (var entry : familia.pressoes.entrySet())
            addBlock(entry.getValue(), "Placa de Pressão de "
                    + nomeDoMaterial(entry.getKey(), nomeMaterial, genero, familia.sufixoPolido));

        for (var entry : familia.bototes.entrySet())
            addBlock(entry.getValue(),
                    "Botão de " + nomeDoMaterial(entry.getKey(), nomeMaterial, genero, familia.sufixoPolido));
    }

    private void traduzirFamiliaPedraAreia(FamiliaAreia familia, String nomeMaterial, Genero genero, boolean temBase) {

        if (temBase)
            addBlock(familia.base, nomeMaterial);
        traduzVariantes(familia.variantesBase, nomeMaterial);

        if (familia.liso != null) {
            String nome = nomeDoMaterial(TipoMaterial.LISO, nomeMaterial, genero, false);
            addBlock(familia.liso, nome);
            traduzVariantes(familia.variantesLiso, nome);
        }

        if (familia.pedregoso != null) {
            String nome = nomeDoMaterial(TipoMaterial.PEDREGOSO, nomeMaterial, genero, false);
            addBlock(familia.pedregoso, nome);
            traduzVariantes(familia.variantesPedregoso, nome);
        }

        if (familia.cortado != null) {
            String nome = nomeDoMaterial(TipoMaterial.CORTADO, nomeMaterial, genero, false);
            addBlock(familia.cortado, nome);
            traduzVariantes(familia.variantesCortado, nome);
        }

        for (var entry : familia.talhados.entrySet())
            addBlock(entry.getValue(),
                    nomeDoMaterialTalhado(entry.getKey(), nomeMaterial, genero, false));

        for (var entry : familia.pilares.entrySet())
            addBlock(entry.getValue(),
                    "Pilar de " + nomeDoMaterial(entry.getKey(), nomeMaterial, genero, false));

        for (var entry : familia.pressoes.entrySet())
            addBlock(entry.getValue(),
                    "Placa de Pressão de "
                            + nomeDoMaterial(entry.getKey(), nomeMaterial, genero, false));

        for (var entry : familia.bototes.entrySet())
            addBlock(entry.getValue(),
                    "Botão de " + nomeDoMaterial(entry.getKey(), nomeMaterial, genero, false));
    }

    private void traduzVariantes(Variantes variantes, String nomeBase) {
        if (variantes == null)
            return;
        if (variantes.escada() != null)
            addBlock(variantes.escada(), "Escadas de " + nomeBase);
        if (variantes.laje() != null)
            addBlock(variantes.laje(), "Laje de " + nomeBase);
        if (variantes.muro() != null)
            addBlock(variantes.muro(), "Muro de " + nomeBase);
    }

    private String sufixo(Genero genero) {
        return switch (genero) {
            case FEMININO -> "a";
            case MASCULINO -> "o";
            case NEUTRO -> "e";
        };
    }

    private String nomeDoMaterial(TipoMaterial tipo, String nomeMaterial, Genero genero, Boolean sufixoPolido) {
        String s = sufixo(genero);
        if (sufixoPolido) {
            return switch (tipo) {
                case BASE -> nomeMaterial;
                case PEDREGOSO -> "Pedregulho de " + nomeMaterial;
                case LISO -> nomeMaterial + " Lis" + s;
                case CORTADO -> nomeMaterial + " Lapidad" + s;
                case POLIDO -> nomeMaterial + " Polid" + s;
                case TIJOLOS -> "Tijolos de " + nomeMaterial + " Polid" + s;
                case LADRILHOS -> "Ladrilhos de " + nomeMaterial + " Polid" + s;
                case PLACAS -> "Placas de " + nomeMaterial + " Polid" + s;
            };
        } else {
            return switch (tipo) {
                case BASE -> nomeMaterial;
                case PEDREGOSO -> "Pedregulho de " + nomeMaterial;
                case LISO -> nomeMaterial + " Lis" + s;
                case CORTADO -> nomeMaterial + " Lapidad" + s;
                case POLIDO -> nomeMaterial + " Polid" + s;
                case TIJOLOS -> "Tijolos de " + nomeMaterial;
                case LADRILHOS -> "Ladrilhos de " + nomeMaterial;
                case PLACAS -> "Placas de " + nomeMaterial;
            };
        }

    }

    private String nomeDoMaterialTalhado(TipoMaterial tipo, String nomeMaterial, Genero genero, Boolean sufixoPolido) {
        String s = sufixo(genero);
        if (sufixoPolido) {
            return switch (tipo) {
                case BASE -> nomeMaterial + " Talhad" + s;
                case PEDREGOSO -> "Pedregulho de " + nomeMaterial + " Talhad" + s;
                case LISO -> nomeMaterial + " Lis" + s + " Talhad" + s;
                case CORTADO -> nomeMaterial + " Lapidad" + s + " Talhad" + s;
                case POLIDO -> nomeMaterial + " Polid" + s + " Talhad" + s;
                case TIJOLOS -> "Tijolos de " + nomeMaterial + " Polid" + s + " Talhados";
                case LADRILHOS -> "Ladrilhos de " + nomeMaterial + " Polid" + s + " Talhados";
                case PLACAS -> "Placas de " + nomeMaterial + " Polid" + s + " Talhadas";
            };
        } else {
            return switch (tipo) {
                case BASE -> nomeMaterial + " Talhad" + s;
                case PEDREGOSO -> "Pedregulho de " + nomeMaterial + " Talhad" + s;
                case LISO -> nomeMaterial + " Lis" + s + " Talhad" + s;
                case CORTADO -> nomeMaterial + " Lapidad" + s + " Talhad" + s;
                case POLIDO -> nomeMaterial + " Polid" + s + " Talhad" + s;
                case TIJOLOS -> "Tijolos de " + nomeMaterial + " Talhados";
                case LADRILHOS -> "Ladrilhos de " + nomeMaterial + " Talhados";
                case PLACAS -> "Placas de " + nomeMaterial + " Talhadas";
            };
        }

    }

    private String nomeDoMaterialRachado(TipoMaterial tipo, String nomeMaterial, Genero genero, Boolean sufixoPolido) {
        String s = sufixo(genero);
        if (sufixoPolido) {
            return switch (tipo) {
                case BASE -> nomeMaterial + " Rachad" + s;
                case PEDREGOSO -> "Pedregulho de " + nomeMaterial + " Rachad" + s;
                case LISO -> nomeMaterial + " Lis" + s + " Rachad" + s;
                case CORTADO -> nomeMaterial + " Lapidad" + s + " Rachad" + s;
                case POLIDO -> nomeMaterial + " Polid" + s + " Rachad" + s;
                case TIJOLOS -> "Tijolos de " + nomeMaterial + " Polid" + s + " Rachados";
                case LADRILHOS -> "Ladrilhos de " + nomeMaterial + " Polid" + s + " Rachados";
                case PLACAS -> "Placas de " + nomeMaterial + " Polid" + s + " Rachadas";
            };
        } else {
            return switch (tipo) {
                case BASE -> nomeMaterial + " Rachad" + s;
                case PEDREGOSO -> "Pedregulho de " + nomeMaterial + " Rachad" + s;
                case LISO -> nomeMaterial + " Lis" + s + " Rachad" + s;
                case CORTADO -> nomeMaterial + " Lapidad" + s + " Rachad" + s;
                case POLIDO -> nomeMaterial + " Polid" + s + " Rachad" + s;
                case TIJOLOS -> "Tijolos de " + nomeMaterial + " Rachados";
                case LADRILHOS -> "Ladrilhos de " + nomeMaterial + " Rachados";
                case PLACAS -> "Placas de " + nomeMaterial + " Rachadas";
            };
        }

    }
}