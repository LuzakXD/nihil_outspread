package com.lzk.nihiloutspread.dataGens;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.lzk.nihiloutspread.blockGens.FamiliaAbstrata;
import com.lzk.nihiloutspread.blockGens.FamiliaPedra;
import com.lzk.nihiloutspread.blockGens.TipoMaterial;
import com.lzk.nihiloutspread.init.ModBlocks;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ProvedorReceitas extends RecipeProvider {

        public ProvedorReceitas(PackOutput output, CompletableFuture<Provider> registries) {
                super(output, registries);
        }

        @Override
        protected void buildRecipes(RecipeOutput output) {
                receitaPedra(ModBlocks.HONEYCOMB_BLOCK_FAMILY, output, true);
                receitaPedra(ModBlocks.GRAPHITE_BLOCK_FAMILY, output, true);
                receitaPedra(ModBlocks.COTTONITE_BLOCK_FAMILY, output, true);
                receitaPedra(ModBlocks.NILIGHT_BLOCK_FAMILY, output, true);
        }

        private void receitaVariantes(DeferredBlock<? extends Block> escada,
                        DeferredBlock<? extends Block> laje,
                        DeferredBlock<? extends Block> muro,
                        Block ingrediente, RecipeOutput output) {
                if (escada != null)
                        stairBuilder(escada.get(), Ingredient.of(ingrediente))
                                        .unlockedBy("has_material", has(ingrediente)).save(output);
                if (laje != null)
                        slabBuilder(RecipeCategory.BUILDING_BLOCKS, laje.get(), Ingredient.of(ingrediente))
                                        .unlockedBy("has_material", has(ingrediente)).save(output);
                if (muro != null)
                        wallBuilder(RecipeCategory.BUILDING_BLOCKS, muro.get(), Ingredient.of(ingrediente))
                                        .unlockedBy("has_material", has(ingrediente)).save(output);
        }

        private void receitaVariantesCortador(FamiliaAbstrata familia, RecipeOutput output,
                        DeferredBlock<Block> ingrediente, Set<TipoMaterial> tipos) {
                for (var entry : familia.talhados.entrySet()) {
                        if (tipos.contains(entry.getKey())) {
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                entry.getValue().get(), ingrediente.get(), 1);
                        }
                }
                for (var entry : familia.pilares.entrySet()) {
                        if (tipos.contains(entry.getKey())) {
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                entry.getValue().get(), ingrediente.get(), 1);
                        }
                }
        }

        private void receitaCortadorDePedra(FamiliaPedra familia, RecipeOutput output) {

                // Variantes base
                if (familia.variantesBase != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesBase.escada().get(), familia.base.get(), 1);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesBase.laje().get(), familia.base.get(), 2);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesBase.muro().get(), familia.base.get(), 1);
                }

                // Variantes tijolos
                if (familia.variantesTijolos != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolos.escada().get(), familia.base.get(), 1);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolos.laje().get(), familia.base.get(), 2);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolos.muro().get(), familia.base.get(), 1);
                }

                // Variantes ladrilhos
                if (familia.variantesLadrilhos != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesLadrilhos.escada().get(), familia.base.get(), 1);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesLadrilhos.laje().get(), familia.base.get(), 2);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesLadrilhos.muro().get(), familia.base.get(), 1);
                }

                // Variantes placas
                if (familia.variantesPlacas != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesPlacas.escada().get(), familia.base.get(), 1);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesPlacas.laje().get(), familia.base.get(), 2);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesPlacas.muro().get(), familia.base.get(), 1);
                }

                // Variantes polido
                if (familia.variantesPolido != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesPolido.escada().get(), familia.base.get(), 1);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesPolido.laje().get(), familia.base.get(), 2);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesPolido.muro().get(), familia.base.get(), 1);
                }

                // Variantes liso
                if (familia.variantesLiso != null && familia.liso != null) {
                        if (familia.variantesLiso.escada() != null)
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                familia.variantesLiso.escada().get(), familia.liso.get(), 1);
                        if (familia.variantesLiso.laje() != null)
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                familia.variantesLiso.laje().get(), familia.liso.get(), 2);
                        if (familia.variantesLiso.muro() != null)
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                familia.variantesLiso.muro().get(), familia.liso.get(), 1);
                }

                // Blocos
                if (familia.polido != null)
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.polido.get(), familia.base.get(), 1);

                if (familia.sufixoPolido) {
                        if (familia.tijolos != null) {
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                familia.tijolos.get(), familia.base.get(), 1);
                                if (familia.polido != null)
                                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                        familia.tijolos.get(), familia.polido.get(), 1);
                        }

                        if (familia.ladrilhos != null) {
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                familia.ladrilhos.get(), familia.base.get(), 1);
                                if (familia.polido != null)
                                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                        familia.ladrilhos.get(), familia.polido.get(), 1);
                                if (familia.tijolos != null)
                                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                        familia.ladrilhos.get(), familia.tijolos.get(), 1);
                        }

                        if (familia.placas != null) {
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                familia.placas.get(), familia.base.get(), 1);
                                if (familia.polido != null)
                                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                        familia.placas.get(), familia.polido.get(), 1);
                                if (familia.tijolos != null)
                                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                        familia.placas.get(), familia.tijolos.get(), 1);
                        }
                } else {
                        if (familia.tijolos != null)
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                familia.tijolos.get(), familia.base.get(), 1);

                        if (familia.ladrilhos != null) {
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                familia.ladrilhos.get(), familia.base.get(), 1);
                                if (familia.tijolos != null) {
                                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                        familia.ladrilhos.get(), familia.tijolos.get(), 1);
                                }
                        }

                        if (familia.placas != null) {
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                familia.placas.get(), familia.base.get(), 1);
                                if (familia.tijolos != null) {
                                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                        familia.placas.get(), familia.tijolos.get(), 1);
                                }
                        }
                }

                // Talhados/Pilares
                receitaVariantesCortador(familia, output, familia.base,
                                Set.of(
                                                TipoMaterial.BASE,
                                                TipoMaterial.POLIDO,
                                                TipoMaterial.TIJOLOS,
                                                TipoMaterial.LADRILHOS,
                                                TipoMaterial.PLACAS));

                if (familia.polido != null)
                        receitaVariantesCortador(familia, output, familia.polido,
                                        Set.of(
                                                        TipoMaterial.POLIDO));

                if (familia.tijolos != null)
                        receitaVariantesCortador(familia, output, familia.tijolos,
                                        Set.of(
                                                        TipoMaterial.TIJOLOS,
                                                        TipoMaterial.LADRILHOS,
                                                        TipoMaterial.PLACAS));

                if (familia.ladrilhos != null)
                        receitaVariantesCortador(familia, output, familia.ladrilhos,
                                        Set.of(TipoMaterial.LADRILHOS));

                if (familia.placas != null)
                        receitaVariantesCortador(familia, output, familia.placas,
                                        Set.of(TipoMaterial.PLACAS));

                if (familia.liso != null)
                        receitaVariantesCortador(familia, output, familia.liso,
                                        Set.of(TipoMaterial.LISO));
        }

        private void receitaPedra(FamiliaPedra familia, RecipeOutput output, boolean cortadorDePedra) {

                if (familia.sufixoPolido) {
                        if (familia.tijolos != null) {
                                polished(output, RecipeCategory.BUILDING_BLOCKS,
                                                familia.tijolos.get(),
                                                familia.polido.get());
                        }

                } else {
                        if (familia.tijolos != null) {
                                polished(output, RecipeCategory.BUILDING_BLOCKS,
                                                familia.tijolos.get(),
                                                familia.base.get());
                        }
                }
                if (familia.tijolos != null) {
                        polished(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.tijolos.get(),
                                        familia.base.get());
                }

                if (familia.ladrilhos != null) {
                        polished(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.ladrilhos.get(),
                                        familia.tijolos.get());
                }

                if (familia.placas != null) {
                        polished(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.placas.get(),
                                        familia.tijolos.get());
                }

                if (familia.polido != null) {
                        polished(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.polido.get(),
                                        familia.base.get());
                }

                // Variantes
                if (familia.variantesBase != null) {
                        receitaVariantes(familia.variantesBase.escada(), familia.variantesBase.laje(),
                                        familia.variantesBase.muro(), familia.base.get(), output);
                }
                if (familia.variantesTijolos != null) {
                        receitaVariantes(familia.variantesTijolos.escada(), familia.variantesTijolos.laje(),
                                        familia.variantesTijolos.muro(), familia.tijolos.get(), output);
                }
                if (familia.variantesLadrilhos != null) {
                        receitaVariantes(familia.variantesLadrilhos.escada(), familia.variantesLadrilhos.laje(),
                                        familia.variantesLadrilhos.muro(), familia.ladrilhos.get(), output);
                }
                if (familia.variantesPlacas != null) {
                        receitaVariantes(familia.variantesPlacas.escada(), familia.variantesPlacas.laje(),
                                        familia.variantesPlacas.muro(), familia.placas.get(), output);
                }
                if (familia.variantesPolido != null) {
                        receitaVariantes(familia.variantesPolido.escada(), familia.variantesPolido.laje(),
                                        familia.variantesPolido.muro(), familia.polido.get(), output);
                }
                if (familia.variantesLiso != null) {
                        receitaVariantes(familia.variantesLiso.escada(), familia.variantesLiso.laje(),
                                        familia.variantesLiso.muro(), familia.liso.get(), output);
                }

                if (cortadorDePedra) {
                        receitaCortadorDePedra(familia, output);
                }

                // Rachados/Lisos
                for (var entry : familia.rachados.entrySet()) {
                        DeferredBlock<Block> material = familia.materialDe(entry.getKey());
                        if (material != null) {
                                smeltingResultFromBase(output, entry.getValue().get(), material.get());
                        }
                }

                if (familia.liso != null) {
                        smeltingResultFromBase(output, familia.liso.get(), familia.base.get());
                }

                // Talhados
                for (var entry : familia.talhados.entrySet()) {
                        DeferredBlock<Block> material = familia.materialDe(entry.getKey());
                        if (material != null) {
                                chiseled(output, RecipeCategory.BUILDING_BLOCKS, entry.getValue().get(),
                                                material.get());
                        }
                }

                // Placa de pressão
                for (var entry : familia.pressoes.entrySet()) {
                        DeferredBlock<Block> material = familia.materialDe(entry.getKey());
                        if (material != null) {
                                pressurePlate(output, entry.getValue().get(), material.get());
                        }
                }

                // Botões
                for (var entry : familia.bototes.entrySet()) {
                        DeferredBlock<Block> material = familia.materialDe(entry.getKey());
                        if (material != null) {
                                buttonBuilder(entry.getValue().get(), Ingredient.of(material.get()))
                                                .unlockedBy(getHasName(material.get()), has(material.get()))
                                                .save(output);
                        }
                }

                // Pilares
                for (var entry : familia.pilares.entrySet()) {
                        DeferredBlock<Block> material = familia.materialDe(entry.getKey());
                        if (material != null) {
                                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, entry.getValue().get(), 2)
                                                .define('#', material.get())
                                                .pattern("#")
                                                .pattern("#")
                                                .unlockedBy(getHasName(material.get()), has(material.get()))
                                                .save(output);
                        }
                }
        }
}