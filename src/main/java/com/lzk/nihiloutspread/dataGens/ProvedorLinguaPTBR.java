package com.lzk.nihiloutspread.dataGens;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.blockGens.FamiliaPedra;
import com.lzk.nihiloutspread.blockGens.TipoMaterial;
import com.lzk.nihiloutspread.blockGens.Variantes;
import com.lzk.nihiloutspread.init.ModBlocks;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ProvedorLinguaPTBR extends LanguageProvider {
    public ProvedorLinguaPTBR(PackOutput output, String locale) {
        super(output, NihilOutspread.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.nihiloutspread", "Nihil Outspread");
        traduzirFamilia(ModBlocks.NILIGHT_BLOCK_FAMILY, "Niluzita", Genero.FEMININO, true);
        traduzirFamilia(ModBlocks.GRAPHITE_BLOCK_FAMILY, "Grafite", Genero.MASCULINO, true);
        traduzirFamilia(ModBlocks.COTTONITE_BLOCK_FAMILY, "Algonita", Genero.FEMININO, true);
        traduzirFamilia(ModBlocks.HONEYCOMB_BLOCK_FAMILY, "Favo de Mel", Genero.MASCULINO, false);
    }

    private void traduzirFamilia(FamiliaPedra familia, String nomeMaterial, Genero genero, boolean temBase) {

        if (temBase)
            addBlock(familia.base, nomeMaterial);
        traduzVariantes(familia.variantesBase, nomeMaterial);

        if (familia.liso != null) {
            String nome = nomeDoMaterial(TipoMaterial.LISO, nomeMaterial, genero);
            addBlock(familia.liso, nome);
            traduzVariantes(familia.variantesLiso, nome);
        }

        if (familia.polido != null) {
            String nome = nomeDoMaterial(TipoMaterial.POLIDO, nomeMaterial, genero);
            addBlock(familia.polido, nome);
            traduzVariantes(familia.variantesPolido, nome);
        }

        if (familia.tijolos != null) {
            String nome = nomeDoMaterial(TipoMaterial.TIJOLOS, nomeMaterial, genero);
            addBlock(familia.tijolos, nome);
            traduzVariantes(familia.variantesTijolos, nome);
        }

        if (familia.tijolosPolido != null) {
            String nome = nomeDoMaterial(TipoMaterial.TIJOLOS_POLIDO, nomeMaterial, genero);
            addBlock(familia.tijolosPolido, nome);
            traduzVariantes(familia.variantesTijolosPolido, nome);
        }

        for (var entry : familia.talhados.entrySet())
            addBlock(entry.getValue(), nomeDoMaterialTalhado(entry.getKey(), nomeMaterial, genero));

        for (var entry : familia.pilares.entrySet())
            addBlock(entry.getValue(), "Pilar de " + nomeDoMaterial(entry.getKey(), nomeMaterial, genero));

        for (var entry : familia.rachados.entrySet())
            addBlock(entry.getValue(), nomeDoMaterialRachado(entry.getKey(), nomeMaterial, genero));

        for (var entry : familia.pressoes.entrySet())
            addBlock(entry.getValue(), "Placa de Pressão de " + nomeDoMaterial(entry.getKey(), nomeMaterial, genero));

        for (var entry : familia.bototes.entrySet())
            addBlock(entry.getValue(), "Botão de " + nomeDoMaterial(entry.getKey(), nomeMaterial, genero));
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

    private String nomeDoMaterial(TipoMaterial tipo, String nomeMaterial, Genero genero) {
        String s = sufixo(genero);
        return switch (tipo) {
            case BASE -> nomeMaterial;
            case LISO -> nomeMaterial + " Lis" + s;
            case POLIDO -> nomeMaterial + " Polid" + s;
            case TIJOLOS -> "Tijolos de " + nomeMaterial;
            case TIJOLOS_POLIDO -> "Tijolos de " + nomeMaterial + " Polid" + s;
            case LADRILHOS -> "Ladrilhos de " + nomeMaterial;
            case PLACAS -> "Placas de " + nomeMaterial;
        };
    }

    private String nomeDoMaterialTalhado(TipoMaterial tipo, String nomeMaterial, Genero genero) {
        String s = sufixo(genero);
        return switch (tipo) {
            case BASE -> nomeMaterial + " Talhad" + s;
            case LISO -> nomeMaterial + " Lis" + s + " Talhad" + s;
            case POLIDO -> nomeMaterial + " Polid" + s + " Talhad" + s;
            case TIJOLOS -> "Tijolos de " + nomeMaterial + " Talhados";
            case TIJOLOS_POLIDO -> "Tijolos de " + nomeMaterial + " Polid" + s + " Talhados";
            case LADRILHOS -> "Ladrilhos de " + nomeMaterial + " Talhados";
            case PLACAS -> "Placas de " + nomeMaterial + " Talhadas";
        };
    }

    private String nomeDoMaterialRachado(TipoMaterial tipo, String nomeMaterial, Genero genero) {
        String s = sufixo(genero);
        return switch (tipo) {
            case BASE -> nomeMaterial + " Rachad" + s;
            case LISO -> nomeMaterial + " Lis" + s + " Rachad" + s;
            case POLIDO -> nomeMaterial + " Polid" + s + " Rachad" + s;
            case TIJOLOS -> "Tijolos de " + nomeMaterial + " Rachados";
            case TIJOLOS_POLIDO -> "Tijolos de " + nomeMaterial + " Polid" + s + " Rachados";
            case LADRILHOS -> "Ladrilhos de " + nomeMaterial + " Rachados";
            case PLACAS -> "Placas de " + nomeMaterial + " Rachadas";
        };
    }
}