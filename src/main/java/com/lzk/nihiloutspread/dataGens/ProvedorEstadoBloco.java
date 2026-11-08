package com.lzk.nihiloutspread.dataGens;

import java.util.LinkedHashMap;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.blockGens.FamiliaPedra;
import com.lzk.nihiloutspread.blocks.*;
import com.lzk.nihiloutspread.init.ModBlocks;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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
        gerarModelosDaFamilia(ModBlocks.HONEYCOMB_BLOCK_FAMILY, false, true, "hive");
        gerarModelosDaFamilia(ModBlocks.GRAPHITE_BLOCK_FAMILY, true, true, "nihil");
        gerarModelosDaFamilia(ModBlocks.COTTONITE_BLOCK_FAMILY, true, true, "nihil");
        blocoComCamadas(ModBlocks.NILIGHT, "nilight", "nihil", NilightBlock.SNOWY, NilightBlock.MOSSY,
                NilightBlock.DIRTY);
        gerarModelosDaFamilia(ModBlocks.NILIGHT_BLOCK_FAMILY, false, true, "nihil");

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

    private void gerarModelosDaFamilia(FamiliaPedra familia, boolean temBase, boolean lajePolidaTexturaUnica,
            String categoria) {

        // BASE
        if (temBase) {
            simpleBlock(familia.base.get(),
                    models().cubeAll(familia.base.getId().getPath(), textura(familia.nomeCompleto, categoria)));
            modeloItemPadrao(familia.base);
        }

        if (familia.variantesBase != null) {
            stairsBlock(familia.variantesBase.escada().get(), textura(familia.nomeCompleto, categoria));
            modeloItemPadrao(familia.variantesBase.escada());

            slabBlock(familia.variantesBase.laje().get(), modLoc("block/" + familia.base.getId().getPath()),
                    textura(familia.nomeCompleto, categoria));
            modeloItemPadrao(familia.variantesBase.laje());

            wallBlock(familia.variantesBase.muro().get(), textura(familia.nomeCompleto, categoria));
            modeloItemMuro(familia.variantesBase.muro(), familia.nomeCompleto, categoria);
        }

        // POLIDO
        if (familia.polido != null) {
            String nomePolido = familia.polido.getId().getPath();
            simpleBlock(familia.polido.get(),
                    models().cubeAll(nomePolido, textura(nomePolido, categoria)));
            modeloItemPadrao(familia.polido);

            if (familia.variantesPolido != null) {
                stairsBlock(familia.variantesPolido.escada().get(), textura(nomePolido, categoria));
                modeloItemPadrao(familia.variantesPolido.escada());

                if (lajePolidaTexturaUnica) {
                    ModelFile doubleslabCustom = models().cubeBottomTop(
                            nomePolido + "_slab_double",
                            textura(nomePolido + "_slab_side", categoria), // side
                            textura(nomePolido, categoria), // bottom
                            textura(nomePolido, categoria) // top
                    );
                    slabBlock(familia.variantesPolido.laje().get(),
                            doubleslabCustom.getLocation(),
                            textura(nomePolido + "_slab_side", categoria), // side
                            textura(nomePolido, categoria), // bottom
                            textura(nomePolido, categoria)); // top
                } else {
                    slabBlock(familia.variantesPolido.laje().get(), modLoc("block/" + nomePolido),
                            textura(nomePolido, categoria));
                }
                modeloItemPadrao(familia.variantesPolido.laje());

                wallBlock(familia.variantesPolido.muro().get(), textura(nomePolido, categoria));
                modeloItemMuro(familia.variantesPolido.muro(), nomePolido, categoria);
            }
        }

        // TIJOLOS
        if (familia.tijolos != null) {
            String nomeTijolos = familia.tijolos.getId().getPath();
            simpleBlock(familia.tijolos.get(),
                    models().cubeAll(nomeTijolos, textura(nomeTijolos, categoria)));
            modeloItemPadrao(familia.tijolos);

            if (familia.variantesTijolos != null) {
                stairsBlock(familia.variantesTijolos.escada().get(), textura(nomeTijolos, categoria));
                modeloItemPadrao(familia.variantesTijolos.escada());

                slabBlock(familia.variantesTijolos.laje().get(), modLoc("block/" + nomeTijolos),
                        textura(nomeTijolos, categoria));
                modeloItemPadrao(familia.variantesTijolos.laje());

                wallBlock(familia.variantesTijolos.muro().get(), textura(nomeTijolos, categoria));
                modeloItemMuro(familia.variantesTijolos.muro(), nomeTijolos, categoria);
            }
        }

        // TIJOLOS RACHADOS
        for (var entry : familia.rachados.entrySet()) {
            String nomeRachado = entry.getValue().getId().getPath();
            simpleBlock(entry.getValue().get(),
                    models().cubeAll(nomeRachado, textura(nomeRachado, categoria)));
            modeloItemPadrao(entry.getValue());
        }

        // TIJOLOS POLIDOS
        if (familia.tijolosPolido != null) {
            String nomeTijolosPolido = familia.tijolosPolido.getId().getPath();
            simpleBlock(familia.tijolosPolido.get(),
                    models().cubeAll(nomeTijolosPolido, textura(nomeTijolosPolido, categoria)));
            modeloItemPadrao(familia.tijolosPolido);

            if (familia.variantesTijolosPolido != null) {
                stairsBlock(familia.variantesTijolosPolido.escada().get(), textura(nomeTijolosPolido, categoria));
                modeloItemPadrao(familia.variantesTijolosPolido.escada());

                slabBlock(familia.variantesTijolosPolido.laje().get(), modLoc("block/" + nomeTijolosPolido),
                        textura(nomeTijolosPolido, categoria));
                modeloItemPadrao(familia.variantesTijolosPolido.laje());

                wallBlock(familia.variantesTijolosPolido.muro().get(), textura(nomeTijolosPolido, categoria));
                modeloItemMuro(familia.variantesTijolosPolido.muro(), nomeTijolosPolido, categoria);
            }
        }
        // LISO
        if (familia.liso != null) {
            String nomeLiso = familia.liso.getId().getPath();
            simpleBlock(familia.liso.get(),
                    models().cubeAll(nomeLiso, textura(nomeLiso, categoria)));
            modeloItemPadrao(familia.liso);

            if (familia.variantesLiso != null) {
                if (familia.variantesLiso.escada() != null) {
                    stairsBlock(familia.variantesLiso.escada().get(), textura(nomeLiso, categoria));
                    modeloItemPadrao(familia.variantesLiso.escada());
                }
                if (familia.variantesLiso.laje() != null) {
                    slabBlock(familia.variantesLiso.laje().get(), modLoc("block/" + nomeLiso),
                            textura(nomeLiso, categoria));
                    modeloItemPadrao(familia.variantesLiso.laje());
                }
                if (familia.variantesLiso.muro() != null) {
                    wallBlock(familia.variantesLiso.muro().get(), textura(nomeLiso, categoria));
                    modeloItemMuro(familia.variantesLiso.muro(), nomeLiso, categoria);
                }
            }
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

    private void blocoComCamadas(DeferredBlock<Block> bloco, String material, String categoria,
            BooleanProperty... camadas) {

        String nomeBloco = bloco.getId().getPath();

        ModelFile base = models().cubeAll(nomeBloco, textura(material, categoria));
        modeloItemPadrao(bloco);

        Map<BooleanProperty, ModelFile> modelos = new LinkedHashMap<>();
        for (BooleanProperty camada : camadas) {
            String nomeCamada = camada.getName();

            modelos.put(camada, models().cube(
                    material + "_" + nomeCamada,
                    textura(material, categoria),
                    textura(nomeCamada, "extras"),
                    textura(material + "_side_" + nomeCamada, categoria),
                    textura(material + "_side_" + nomeCamada, categoria),
                    textura(material + "_side_" + nomeCamada, categoria),
                    textura(material + "_side_" + nomeCamada, categoria)));
        }

        getVariantBuilder(bloco.get()).forAllStates(state -> {
            ModelFile escolhido = base;
            for (var entry : modelos.entrySet()) {
                if (state.getValue(entry.getKey())) {
                    escolhido = entry.getValue();
                    break;
                }
            }
            return ConfiguredModel.builder().modelFile(escolhido).build();
        });
    }
}