package com.lzk.nihiloutspread.dataGens;

import java.util.LinkedHashMap;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.blockGens.FamiliaAbstrata;
import com.lzk.nihiloutspread.blockGens.FamiliaAreia;
import com.lzk.nihiloutspread.blockGens.FamiliaPedra;
import com.lzk.nihiloutspread.blockGens.Variantes;
import com.lzk.nihiloutspread.blocks.*;
import com.lzk.nihiloutspread.init.ModBlocks;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import java.util.Map;

public class ProvedorEstadoBloco extends BlockStateProvider {

    public ProvedorEstadoBloco(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, NihilOutspread.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        gerarModelosDaFamiliaPedra(ModBlocks.HONEYCOMB_BLOCK_FAMILY, false, "hive");
        gerarModelosDaFamiliaPedra(ModBlocks.GRAPHITE_BLOCK_FAMILY, true, "nihil");
        gerarModelosDaFamiliaPedra(ModBlocks.COTTONITE_BLOCK_FAMILY, true, "nihil");
        blocoComCamadas(ModBlocks.NILIGHT, "nilight", "nihil", false, NilightBlock.SNOWY, NilightBlock.MOSSY,
                NilightBlock.DIRTY);
        gerarModelosDaFamiliaPedra(ModBlocks.NILIGHT_BLOCK_FAMILY, false, "nihil");
        blocoComCamadasTopBottom(ModBlocks.NIHIL_GRASS_BLOCK, "nihil_grass_block", "nihil", true,
                NihilGrassBlock.SNOWY);
        blocoSimples(ModBlocks.NIHIL_DIRT, "dirty", "extras");
        blocoSimples(ModBlocks.VELVET_SAND, "velvet_sand", "nihil");
        gerarModelosDaFamiliaAreia(ModBlocks.VELVESTONE_BLOCK_FAMILY, true, false, "nihil");

    }

    private void blocoSimples(DeferredBlock<? extends Block> bloco, String Textura, String categoria) {
        if (Textura != null) {
            simpleBlock(bloco.get(),
                    models().cubeAll(bloco.getId().getPath(), textura(Textura, categoria)));
            modeloItemPadrao(bloco);
        } else {
            simpleBlock(bloco.get(),
                    models().cubeAll(bloco.getId().getPath(), textura(bloco.getId().getPath(), categoria)));
            modeloItemPadrao(bloco);
        }

    }

    private ResourceLocation textura(String nome, String categoria) {
        if (categoria != null) {
            return modLoc("block/" + categoria + "/" + nome);
        } else {
            return modLoc("block/" + nome);
        }
    }

    private void modeloItemPadrao(DeferredBlock<? extends Block> bloco) {
        itemModels().withExistingParent(bloco.getId().getPath(),
                modLoc("block/" + bloco.getId().getPath()));
    }

    private void modeloItemMuro(DeferredBlock<WallBlock> muro, String nomeTextura, String categoria) {
        itemModels().withExistingParent(muro.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", textura(nomeTextura, categoria));
    }

    private void gerarModelosVariantes(
            DeferredBlock<? extends Block> raiz,
            String nomeTextura,
            Variantes variantes,
            boolean lajeTexturaUnica,
            String categoria) {

        if (variantes == null)
            return;

        if (variantes.escada() != null) {
            stairsBlock(variantes.escada().get(), textura(nomeTextura, categoria));
            modeloItemPadrao(variantes.escada());
        }

        if (variantes.laje() != null) {
            if (lajeTexturaUnica) {
                ModelFile doubleslabCustom = models().cubeBottomTop(
                        variantes.laje().getId().getPath() + "_double",
                        textura(nomeTextura + "_slab_side", categoria),
                        textura(nomeTextura, categoria),
                        textura(nomeTextura, categoria));
                slabBlock(variantes.laje().get(),
                        doubleslabCustom.getLocation(),
                        textura(nomeTextura + "_slab_side", categoria),
                        textura(nomeTextura, categoria),
                        textura(nomeTextura, categoria));
            } else {
                slabBlock(variantes.laje().get(), modLoc("block/" + raiz.getId().getPath()),
                        textura(nomeTextura, categoria));
            }
            modeloItemPadrao(variantes.laje());
        }

        if (variantes.muro() != null) {
            wallBlock(variantes.muro().get(), textura(nomeTextura, categoria));
            modeloItemMuro(variantes.muro(), nomeTextura, categoria);
        }
    }

    private void gerarModelosDaFamiliaPedra(FamiliaPedra familia, boolean temBase,
            String categoria) {

        // BASE
        if (temBase) {
            simpleBlock(familia.base.get(),
                    models().cubeAll(familia.base.getId().getPath(), textura(familia.nomeCompleto, categoria)));
            modeloItemPadrao(familia.base);
        }

        gerarModelosVariantes(familia.base, familia.nomeCompleto, familia.variantesBase, false, categoria);

        // POLIDO
        if (familia.polido != null) {
            String nomePolido = familia.polido.getId().getPath();
            simpleBlock(familia.polido.get(),
                    models().cubeAll(nomePolido, textura(nomePolido, categoria)));
            modeloItemPadrao(familia.polido);

            gerarModelosVariantes(familia.polido, familia.polido.getId().getPath(), familia.variantesPolido,
                    true, categoria);
        }

        // TIJOLOS
        if (familia.tijolos != null) {
            String nomeTijolos = familia.tijolos.getId().getPath();
            simpleBlock(familia.tijolos.get(),
                    models().cubeAll(nomeTijolos, textura(nomeTijolos, categoria)));
            modeloItemPadrao(familia.tijolos);

            gerarModelosVariantes(familia.tijolos, familia.tijolos.getId().getPath(), familia.variantesTijolos,
                    false, categoria);
        }

        // TIJOLOS POLIDOS
        if (familia.tijolosPolido != null) {
            String nomeTijolosPolido = familia.tijolosPolido.getId().getPath();
            simpleBlock(familia.tijolosPolido.get(),
                    models().cubeAll(nomeTijolosPolido, textura(nomeTijolosPolido, categoria)));
            modeloItemPadrao(familia.tijolosPolido);

            gerarModelosVariantes(familia.tijolosPolido, familia.tijolosPolido.getId().getPath(),
                    familia.variantesTijolosPolido, false, categoria);

        }

        // LADRILHOS
        if (familia.ladrilhos != null) {
            String nomeLadrilhos = familia.ladrilhos.getId().getPath();
            simpleBlock(familia.ladrilhos.get(),
                    models().cubeAll(nomeLadrilhos, textura(nomeLadrilhos, categoria)));
            modeloItemPadrao(familia.ladrilhos);

            gerarModelosVariantes(familia.ladrilhos, familia.ladrilhos.getId().getPath(), familia.variantesLadrilhos,
                    false, categoria);
        }

        // LADRILHOS POLIDOS
        if (familia.ladrilhosPolido != null) {
            String nomeLadrilhosPolido = familia.ladrilhosPolido.getId().getPath();
            simpleBlock(familia.ladrilhosPolido.get(),
                    models().cubeAll(nomeLadrilhosPolido, textura(nomeLadrilhosPolido, categoria)));
            modeloItemPadrao(familia.ladrilhosPolido);

            gerarModelosVariantes(familia.ladrilhosPolido, familia.ladrilhosPolido.getId().getPath(),
                    familia.variantesLadrilhosPolido, false, categoria);

        }

        // PLACAS
        if (familia.placas != null) {
            String nomePlacas = familia.placas.getId().getPath();
            simpleBlock(familia.placas.get(),
                    models().cubeAll(nomePlacas, textura(nomePlacas, categoria)));
            modeloItemPadrao(familia.placas);

            gerarModelosVariantes(familia.placas, familia.placas.getId().getPath(), familia.variantesPlacas,
                    false, categoria);
        }

        // PLACAS POLIDAS
        if (familia.placasPolidas != null) {
            String nomePlacasPolidas = familia.placasPolidas.getId().getPath();
            simpleBlock(familia.placasPolidas.get(),
                    models().cubeAll(nomePlacasPolidas, textura(nomePlacasPolidas, categoria)));
            modeloItemPadrao(familia.placasPolidas);

            gerarModelosVariantes(familia.placasPolidas, familia.placasPolidas.getId().getPath(),
                    familia.variantesPlacasPolidas, false, categoria);

        }

        // LISO
        if (familia.liso != null) {
            String nomeLiso = familia.liso.getId().getPath();
            simpleBlock(familia.liso.get(),
                    models().cubeAll(nomeLiso, textura(nomeLiso, categoria)));
            modeloItemPadrao(familia.liso);

            gerarModelosVariantes(familia.liso, familia.liso.getId().getPath(), familia.variantesLiso,
                    false, categoria);

        }

        // RACHADOS
        for (var entry : familia.rachados.entrySet()) {
            String nomeRachado = entry.getValue().getId().getPath();
            simpleBlock(entry.getValue().get(),
                    models().cubeAll(nomeRachado, textura(nomeRachado, categoria)));
            modeloItemPadrao(entry.getValue());
        }

        // PLACA DE PRESSAO
        for (var entry : familia.pressoes.entrySet()) {
            DeferredBlock<Block> material = familia.materialDe(entry.getKey());
            if (material != null) {
                String nomeMaterial = material.getId().getPath();
                pressurePlateBlock(entry.getValue().get(), textura(nomeMaterial, categoria));
                modeloItemPadrao(entry.getValue());
            }
        }

        // BOTAO
        for (var entry : familia.bototes.entrySet()) {
            DeferredBlock<Block> material = familia.materialDe(entry.getKey());
            if (material != null) {
                String nomeMaterial = material.getId().getPath();
                buttonBlock(entry.getValue().get(), textura(nomeMaterial, categoria));
                itemModels()
                        .withExistingParent(entry.getValue().getId().getPath(), mcLoc("block/button_inventory"))
                        .texture("texture", textura(nomeMaterial, categoria));
            }
        }

        // TALHADOS
        for (var entry : familia.talhados.entrySet()) {
            String nomeTalhado = entry.getValue().getId().getPath();
            simpleBlock(entry.getValue().get(),
                    models().cubeAll(nomeTalhado, textura(nomeTalhado, categoria)));
            modeloItemPadrao(entry.getValue());
        }

        // PILARES
        for (var entry : familia.pilares.entrySet()) {
            String nomePilar = entry.getValue().getId().getPath();
            axisBlock(entry.getValue().get(),
                    textura(nomePilar + "_side", categoria),
                    textura(nomePilar + "_top", categoria));
            modeloItemPadrao(entry.getValue());
        }
    }

    private void gerarModelosDaFamiliaAreia(FamiliaAreia familia, boolean temBase, boolean lajeCortadoTexturaUnica,
            String categoria) {

        // BASE
        if (temBase) {
            simpleBlock(familia.base.get(),
                    models().cubeBottomTop(familia.base.getId().getPath(),
                            textura(familia.nomeCompleto, categoria),
                            textura(familia.nomeCompleto + "_bottom", categoria),
                            textura(familia.nomeCompleto + "_top", categoria)));
            modeloItemPadrao(familia.base);
        }

        gerarModelosVariantes(familia.base, familia.nomeCompleto, familia.variantesBase, false, categoria);

        // CORTADO
        if (familia.cortado != null) {
            String nomeCortado = familia.cortado.getId().getPath();
            simpleBlock(familia.cortado.get(),
                    models().cubeBottomTop(nomeCortado,
                            textura(nomeCortado, categoria),
                            textura(familia.nomeCompleto + "_top", categoria),
                            textura(familia.nomeCompleto + "_top", categoria)));
            modeloItemPadrao(familia.cortado);

            gerarModelosVariantes(familia.cortado, familia.cortado.getId().getPath(), familia.variantesCortado,
                    true, categoria);
        }

        // LISO
        if (familia.liso != null) {
            String nomeLiso = familia.liso.getId().getPath();
            String nomeTextura = familia.base.getId().getPath() + "_top";
            simpleBlock(familia.liso.get(),
                    models().cubeAll(nomeLiso, textura(nomeTextura, categoria)));
            modeloItemPadrao(familia.liso);

            gerarModelosVariantes(familia.liso, familia.liso.getId().getPath(), familia.variantesLiso,
                    false, categoria);
        }

        // PLACA DE PRESSAO
        for (var entry : familia.pressoes.entrySet()) {
            DeferredBlock<Block> material = familia.materialDe(entry.getKey());
            if (material != null) {
                String nomeMaterial = material.getId().getPath();
                pressurePlateBlock(entry.getValue().get(), textura(nomeMaterial, categoria));
                modeloItemPadrao(entry.getValue());
            }
        }

        // BOTAO
        for (var entry : familia.bototes.entrySet()) {
            DeferredBlock<Block> material = familia.materialDe(entry.getKey());
            if (material != null) {
                String nomeMaterial = material.getId().getPath();
                buttonBlock(entry.getValue().get(), textura(nomeMaterial, categoria));
                itemModels()
                        .withExistingParent(entry.getValue().getId().getPath(), mcLoc("block/button_inventory"))
                        .texture("texture", textura(nomeMaterial, categoria));
            }
        }

        // RACHADOS
        for (var entry : familia.rachados.entrySet()) {
            String nomeRachado = entry.getValue().getId().getPath();
            simpleBlock(entry.getValue().get(),
                    models().cubeAll(nomeRachado, textura(nomeRachado, categoria)));
            modeloItemPadrao(entry.getValue());
        }

        // TALHADOS
        for (var entry : familia.talhados.entrySet()) {
            String nomeTalhado = entry.getValue().getId().getPath();
            simpleBlock(entry.getValue().get(),
                    models().cubeAll(nomeTalhado, textura(nomeTalhado, categoria)));
            modeloItemPadrao(entry.getValue());
        }

        // PILARES
        for (var entry : familia.pilares.entrySet()) {
            String nomePilar = entry.getValue().getId().getPath();
            axisBlock(entry.getValue().get(),
                    textura(nomePilar + "_side", categoria),
                    textura(nomePilar + "_top", categoria));
            modeloItemPadrao(entry.getValue());
        }
    }

    private void blocoComCamadas(
            DeferredBlock<? extends Block> bloco, String material, String categoria,
            boolean temVariacao, BooleanProperty... camadas) {

        if (camadas.length == 0) {
            throw new IllegalArgumentException(
                    "Block Need at least one layer: " + bloco.getId());
        }

        String nomeBloco = bloco.getId().getPath();

        ModelFile base = models().cubeAll(nomeBloco, textura(material, categoria));
        modeloItemPadrao(bloco);

        Map<BooleanProperty, ModelFile> modelos = new LinkedHashMap<>();
        for (BooleanProperty camada : camadas) {
            String nomeCamada = camada.getName();

            modelos.put(camada, models().cubeBottomTop(
                    material + "_" + nomeCamada,
                    textura(material + "_side_" + nomeCamada, categoria),
                    textura(material, categoria),
                    textura(nomeCamada, "extras")));
        }

        getVariantBuilder(bloco.get()).forAllStates(state -> {
            ModelFile escolhido = base;
            for (var entry : modelos.entrySet()) {
                if (state.getValue(entry.getKey())) {
                    escolhido = entry.getValue();
                    break;
                }
            }

            if (temVariacao) {
                return ConfiguredModel.allYRotations(escolhido, 0, false);
            }
            return ConfiguredModel.builder().modelFile(escolhido).build();
        });
    }

    private void blocoComVarianteY(DeferredBlock<? extends Block> bloco, String textura, String categoria) {

        String nomeBloco = bloco.getId().getPath();
        ModelFile base = models().cubeAll(nomeBloco, textura(textura, categoria));
        modeloItemPadrao(bloco);

        getVariantBuilder(bloco.get()).forAllStates(state -> {
            return ConfiguredModel.allYRotations(base, 0, false);
        });
    }

    private void blocoComCamadasTopBottom(
            DeferredBlock<? extends Block> bloco, String material, String categoria,
            boolean temVariacao, BooleanProperty... camadas) {

        if (camadas.length == 0) {
            throw new IllegalArgumentException("Block Need at least one layer: " + bloco.getId());
        }

        String nomeBloco = bloco.getId().getPath();

        ModelFile base = models().cubeBottomTop(nomeBloco,
                textura(material + "_side", categoria),
                textura(material + "_bottom", categoria),
                textura(material + "_top", categoria));

        modeloItemPadrao(bloco);

        Map<BooleanProperty, ModelFile> modelos = new LinkedHashMap<>();
        for (BooleanProperty camada : camadas) {
            String nomeCamada = camada.getName();

            modelos.put(camada, models().cubeBottomTop(
                    material + "_" + nomeCamada,
                    textura(material + "_side_" + nomeCamada, categoria),
                    textura(material + "_bottom", categoria),
                    textura(nomeCamada, "extras")));
        }

        getVariantBuilder(bloco.get()).forAllStates(state -> {
            ModelFile escolhido = base;
            for (var entry : modelos.entrySet()) {
                if (state.getValue(entry.getKey())) {
                    escolhido = entry.getValue();
                    break;
                }
            }

            if (temVariacao) {
                return ConfiguredModel.allYRotations(escolhido, 0, false);
            }
            return ConfiguredModel.builder().modelFile(escolhido).build();
        });
    }
}