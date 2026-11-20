package com.lzk.nihiloutspread.dataGens;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.lzk.nihiloutspread.blockGens.*;
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
                receitaPedra(ModBlocks.HONEYCOMB_FAMILY, output, true);
                receitaPedra(ModBlocks.GRAPHITE_FAMILY, output, true);
                receitaPedra(ModBlocks.COTTONITE_FAMILY, output, true);
                receitaPedra(ModBlocks.SODALITE_FAMILY, output, true);
                receitaPedra(ModBlocks.NILIGHT_FAMILY, output, true);

                receitaPedra(ModBlocks.ABYSSAL_STONE_FAMILY, output, true);

                receitaPedra(ModBlocks.ABYSSAL_MOSS_FAMILY, output, false);

                carpet(output, ModBlocks.ABYSSAL_MOSS_CARPET, ModBlocks.ABYSSAL_MOSS_BLOCK);
                carpet(output, ModBlocks.ABYSSAL_FLORAL_MOSS_CARPET, ModBlocks.ABYSSAL_FLORAL_MOSS_BLOCK);

        }

        private void receitaVariantes(Variantes variantes, Block ingrediente, RecipeOutput output) {
                if (variantes.escada() != null)
                        stairBuilder(variantes.escada().get(), Ingredient.of(ingrediente))
                                        .unlockedBy("has_material", has(ingrediente)).save(output);
                if (variantes.laje() != null)
                        slabBuilder(RecipeCategory.BUILDING_BLOCKS, variantes.laje().get(), Ingredient.of(ingrediente))
                                        .unlockedBy("has_material", has(ingrediente)).save(output);
                if (variantes.muro() != null)
                        wallBuilder(RecipeCategory.BUILDING_BLOCKS, variantes.muro().get(), Ingredient.of(ingrediente))
                                        .unlockedBy("has_material", has(ingrediente)).save(output);
                if (variantes.cerca() != null)
                        fenceBuilder(variantes.cerca().get(), Ingredient.of(ingrediente))
                                        .unlockedBy("has_material", has(ingrediente)).save(output);
                if (variantes.porta() != null)
                        doorBuilder(variantes.porta().get(), Ingredient.of(ingrediente))
                                        .unlockedBy("has_material", has(ingrediente)).save(output);
                if (variantes.portao() != null)
                        fenceGateBuilder(variantes.portao().get(), Ingredient.of(ingrediente))
                                        .unlockedBy("has_material", has(ingrediente)).save(output);
                if (variantes.alcapao() != null)
                        trapdoorBuilder(variantes.alcapao().get(), Ingredient.of(ingrediente))
                                        .unlockedBy("has_material", has(ingrediente)).save(output);

                if (variantes.rachado() != null)
                        smeltingResultFromBase(output, variantes.rachado(), ingrediente);
                if (variantes.talhado() != null)
                        chiseled(output, RecipeCategory.BUILDING_BLOCKS, variantes.talhado(), ingrediente);
                if (variantes.pressao() != null)
                        pressurePlate(output, variantes.pressao(), ingrediente);
                if (variantes.botao() != null)
                        buttonBuilder(variantes.botao(), Ingredient.of(ingrediente))
                                        .unlockedBy(getHasName(ingrediente), has(ingrediente))
                                        .save(output);
                if (variantes.pilar() != null)
                        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, variantes.pilar(), 2)
                                        .define('#', ingrediente)
                                        .pattern("#")
                                        .pattern("#")
                                        .unlockedBy(getHasName(ingrediente), has(ingrediente))
                                        .save(output);
        }

        private void receitaVariantesCortador(FamiliaAbstrata familia, RecipeOutput output,
                        DeferredBlock<Block> ingrediente, Set<TipoMaterial> tipos) {
                for (Variantes v : familia.variantes()) {
                        if (!tipos.contains(v.tipo()))
                                continue;
                        if (v.talhado() != null)
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                v.talhado().get(), ingrediente.get(), 1);
                        if (v.pilar() != null)
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                v.pilar().get(), ingrediente.get(), 1);
                }
        }

        private void receitaCortadorDePedra(FamiliaPedra familia, RecipeOutput output) {

                for (Variantes v : familia.variantes()) {
                        DeferredBlock<Block> ingrediente = (v.tipo() == TipoMaterial.LISO && familia.liso != null)
                                        ? familia.liso
                                        : familia.base;

                        if (v.escada() != null)
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                v.escada().get(), ingrediente.get(), 1);
                        if (v.laje() != null)
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                v.laje().get(), ingrediente.get(), 2);
                        if (v.muro() != null)
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                v.muro().get(), ingrediente.get(), 1);
                }

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
                                if (familia.tijolos != null)
                                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                        familia.ladrilhos.get(), familia.tijolos.get(), 1);
                        }
                        if (familia.placas != null) {
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                familia.placas.get(), familia.base.get(), 1);
                                if (familia.tijolos != null)
                                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                        familia.placas.get(), familia.tijolos.get(), 1);
                        }
                }

                receitaVariantesCortador(familia, output, familia.base,
                                Set.of(TipoMaterial.BASE, TipoMaterial.POLIDO, TipoMaterial.TIJOLOS,
                                                TipoMaterial.LADRILHOS, TipoMaterial.PLACAS));

                if (familia.polido != null)
                        receitaVariantesCortador(familia, output, familia.polido,
                                        Set.of(TipoMaterial.POLIDO));
                if (familia.tijolos != null)
                        receitaVariantesCortador(familia, output, familia.tijolos,
                                        Set.of(TipoMaterial.TIJOLOS, TipoMaterial.LADRILHOS, TipoMaterial.PLACAS));
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
                        if (familia.tijolos != null)
                                polished(output, RecipeCategory.BUILDING_BLOCKS,
                                                familia.tijolos.get(), familia.polido.get());
                } else {
                        if (familia.tijolos != null)
                                polished(output, RecipeCategory.BUILDING_BLOCKS,
                                                familia.tijolos.get(), familia.base.get());
                }

                if (familia.placas != null)
                        polished(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.placas.get(), familia.tijolos.get());

                if (familia.polido != null)
                        polished(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.polido.get(), familia.base.get());

                for (Variantes v : familia.variantes()) {
                        DeferredBlock<Block> material = familia.materialDe(v.tipo());
                        if (material == null)
                                continue;

                        receitaVariantes(v, material.get(), output);

                }

                if (cortadorDePedra)
                        receitaCortadorDePedra(familia, output);

                if (familia.liso != null)
                        smeltingResultFromBase(output, familia.liso.get(), familia.base.get());
        }
}