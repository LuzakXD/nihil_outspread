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
        add("itemGroup.nihil_naturals", "Nihil naturais");
        add("itemGroup.abyss", "Abismo");
        add("itemGroup.hive", "Colmeia");

        addBlock(ModBlocks.NIHIL_DIRT, "Terra Nihil");
        addBlock(ModBlocks.NIHIL_GRASS_BLOCK, "Bloco de Grama Nihil");
        addBlock(ModBlocks.CHECKER_GRASS_BLOCK, "Bloco de Grama Xadrez");

        addBlock(ModBlocks.NIHIL_FERN, "Samambaia Nihil");
        addBlock(ModBlocks.NIHIL_BUSH, "Arbusto Nihil");
        addBlock(ModBlocks.NIHIL_HERB, "Erva Nihil");

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

        for (var entry : familia.botoes.entrySet())
            addBlock(entry.getValue(),
                    "Botão de " + nomeDoMaterial(entry.getKey(), nomeMaterial, genero, familia.sufixoPolido));
    }

    private void traduzirFamilia(FamiliaAreia familia, String nomeMaterial, Genero genero, boolean temBase) {

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

        for (var entry : familia.botoes.entrySet())
            addBlock(entry.getValue(),
                    "Botão de " + nomeDoMaterial(entry.getKey(), nomeMaterial, genero, false));
    }

    private void traduzirFamilia(FamiliaMadeira familia, String nomeMaterial, Genero genero, boolean temBase) {

        if (temBase)
            addBlock(familia.base, "Tábuas de " + nomeMaterial);
        traduzVariantes(familia.variantesBase, nomeMaterial);

        if (familia.tronco != null) {
            addBlock(familia.tronco, "Tronco de " + nomeMaterial);
        }
        if (familia.madeira != null) {
            addBlock(familia.madeira, "Madeira de " + nomeMaterial);
        }
        if (familia.folha != null) {
            addBlock(familia.folha, "Folhas de " + nomeMaterial);
        }

        for (var entry : familia.descascados.entrySet())
            addBlock(entry.getValue(),
                    nomeDoMaterialDescascados(entry.getKey(), nomeMaterial + " Descascado", genero));

        for (var entry : familia.pressoes.entrySet())
            addBlock(entry.getValue(), "Placa de Pressão de "
                    + nomeDoMaterial(entry.getKey(), nomeMaterial, genero, false));

        for (var entry : familia.botoes.entrySet())
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

        if (variantes.cerca() != null)
            addBlock(variantes.cerca(), "Cerca de " + nomeBase);
        if (variantes.portao() != null)
            addBlock(variantes.portao(), "Portão de " + nomeBase);

        if (variantes.porta() != null)
            addBlock(variantes.porta(), "Porta de " + nomeBase);
        if (variantes.alcapao() != null)
            addBlock(variantes.alcapao(), "Alçapão de " + nomeBase);
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
                case MOSAICO -> "Mosaico de " + nomeMaterial + " Polid" + s;
                case TRONCO -> null;
                case MADEIRA -> null;
                case FOLHA -> null;
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
                case MOSAICO -> "Mosaico de " + nomeMaterial;
                case TRONCO -> "Tronco de " + nomeMaterial;
                case MADEIRA -> "Madeira de " + nomeMaterial;
                case FOLHA -> "Folhas de " + nomeMaterial;
            };
        }
    }

    private String nomeDoMaterialDescascados(TipoMaterial tipo, String nomeMaterial, Genero genero) {
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
            case TRONCO -> "Tronco de " + nomeMaterial;
            case MADEIRA -> "Madeira de " + nomeMaterial;
            case FOLHA -> null;
        };
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
                case MOSAICO -> "Mosaico de " + nomeMaterial + " Polid" + s + " Talhados";
                case TRONCO -> null;
                case MADEIRA -> null;
                case FOLHA -> null;
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
                case MOSAICO -> "Mosaico de " + nomeMaterial + " Talhados";
                case TRONCO -> null;
                case MADEIRA -> null;
                case FOLHA -> null;
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
                case MOSAICO -> "Mosaico de " + nomeMaterial + " Polid" + s + " Rachados";
                case TRONCO -> null;
                case MADEIRA -> null;
                case FOLHA -> null;
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
                case MOSAICO -> "Mosaico de " + nomeMaterial + " Rachados";
                case TRONCO -> "Tronco de " + nomeMaterial + " Rachados";
                case MADEIRA -> "Madeira de " + nomeMaterial + " Rachados";
                case FOLHA -> null;
            };
        }

    }
}