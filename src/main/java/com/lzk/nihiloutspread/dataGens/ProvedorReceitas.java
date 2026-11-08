package com.lzk.nihiloutspread.dataGens;

import java.util.concurrent.CompletableFuture;

import com.lzk.nihiloutspread.blockGens.FamiliaPedra;
import com.lzk.nihiloutspread.init.ModBlocks;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
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

        private void receitaCortadorDePedra(FamiliaPedra familia, RecipeOutput output) {

                // apartir da base
                if (familia.variantesBase != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesBase.escada().get(), familia.base.get(), 1);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesBase.laje().get(), familia.base.get(), 2);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesBase.muro().get(), familia.base.get(), 1);
                }

                if (familia.polido != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.polido.get(), familia.base.get(), 1);
                }

                if (familia.variantesPolido != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesPolido.escada().get(), familia.base.get(), 1);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesPolido.laje().get(), familia.base.get(), 2);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesPolido.muro().get(), familia.base.get(), 1);
                }

                if (familia.tijolos != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.tijolos.get(), familia.base.get(), 1);
                }
                if (familia.variantesTijolos != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolos.escada().get(), familia.base.get(), 1);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolos.laje().get(), familia.base.get(), 2);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolos.muro().get(), familia.base.get(), 1);
                }

                if (familia.tijolosPolido != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.tijolosPolido.get(), familia.base.get(), 1);
                }
                if (familia.variantesTijolosPolido != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolosPolido.escada().get(), familia.base.get(), 1);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolosPolido.laje().get(), familia.base.get(), 2);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolosPolido.muro().get(), familia.base.get(), 1);
                }

                for (var entry : familia.talhados.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> familia.base;
                                case LISO -> null;
                                case POLIDO -> familia.polido;
                                case TIJOLOS -> familia.tijolos;
                                case TIJOLOS_POLIDO -> familia.tijolosPolido;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
                        if (material != null) {

                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                entry.getValue().get(), familia.base.get(), 1);
                        }
                }

                for (var entry : familia.pilares.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> familia.base;
                                case LISO -> null;
                                case POLIDO -> familia.polido;
                                case TIJOLOS -> familia.tijolos;
                                case TIJOLOS_POLIDO -> familia.tijolosPolido;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
                        if (material != null) {
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                entry.getValue().get(), familia.base.get(), 1);
                        }
                }

                // apartir do polido
                if (familia.variantesPolido != null && familia.polido != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesPolido.escada().get(), familia.polido.get(), 1);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesPolido.laje().get(), familia.polido.get(), 2);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesPolido.muro().get(), familia.polido.get(), 1);
                }

                if (familia.tijolosPolido != null && familia.polido != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.tijolosPolido.get(), familia.polido.get(), 1);
                }
                if (familia.variantesTijolosPolido != null && familia.polido != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolosPolido.escada().get(), familia.polido.get(), 1);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolosPolido.laje().get(), familia.polido.get(), 2);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolosPolido.muro().get(), familia.polido.get(), 1);
                }

                for (var entry : familia.talhados.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> null;
                                case LISO -> null;
                                case POLIDO -> familia.polido;
                                case TIJOLOS -> familia.tijolos;
                                case TIJOLOS_POLIDO -> familia.tijolosPolido;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
                        if (material != null) {

                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                entry.getValue().get(), familia.polido.get(), 1);
                        }
                }

                for (var entry : familia.pilares.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> null;
                                case LISO -> null;
                                case POLIDO -> familia.polido;
                                case TIJOLOS -> familia.tijolos;
                                case TIJOLOS_POLIDO -> familia.tijolosPolido;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
                        if (material != null) {
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                entry.getValue().get(), familia.polido.get(), 1);
                        }
                }

                // a partir dos tijolos
                if (familia.variantesTijolos != null && familia.tijolos != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolos.escada().get(), familia.tijolos.get(), 1);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolos.laje().get(), familia.tijolos.get(), 2);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolos.muro().get(), familia.tijolos.get(), 1);
                }

                for (var entry : familia.talhados.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> null;
                                case LISO -> null;
                                case POLIDO -> null;
                                case TIJOLOS -> familia.tijolos;
                                case TIJOLOS_POLIDO -> familia.tijolosPolido;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
                        if (material != null) {

                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                entry.getValue().get(), familia.tijolos.get(), 1);
                        }
                }

                for (var entry : familia.pilares.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> null;
                                case LISO -> null;
                                case POLIDO -> null;
                                case TIJOLOS -> familia.tijolos;
                                case TIJOLOS_POLIDO -> familia.tijolosPolido;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
                        if (material != null) {
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                entry.getValue().get(), familia.tijolos.get(), 1);
                        }
                }

                // a partir dos tijolos polido
                if (familia.variantesTijolosPolido != null && familia.tijolosPolido != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolosPolido.escada().get(), familia.tijolosPolido.get(),
                                        1);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolosPolido.laje().get(), familia.tijolosPolido.get(),
                                        2);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesTijolosPolido.muro().get(), familia.tijolosPolido.get(),
                                        1);
                }

                for (var entry : familia.talhados.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> null;
                                case LISO -> null;
                                case POLIDO -> null;
                                case TIJOLOS -> null;
                                case TIJOLOS_POLIDO -> familia.tijolosPolido;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
                        if (material != null) {

                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                entry.getValue().get(), familia.tijolosPolido.get(), 1);
                        }
                }

                for (var entry : familia.pilares.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> null;
                                case LISO -> null;
                                case POLIDO -> null;
                                case TIJOLOS -> null;
                                case TIJOLOS_POLIDO -> familia.tijolosPolido;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
                        if (material != null) {
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                entry.getValue().get(), familia.tijolosPolido.get(), 1);
                        }
                }

                // a partir dos lisos
                if (familia.variantesLiso != null && familia.liso != null) {
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesLiso.escada().get(), familia.tijolosPolido.get(),
                                        1);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesLiso.laje().get(), familia.liso.get(),
                                        2);
                        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                        familia.variantesLiso.muro().get(), familia.liso.get(),
                                        1);
                }

                for (var entry : familia.talhados.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> null;
                                case LISO -> familia.liso;
                                case POLIDO -> null;
                                case TIJOLOS -> null;
                                case TIJOLOS_POLIDO -> null;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
                        if (material != null) {

                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                entry.getValue().get(), familia.base.get(), 1);
                        }
                }

                for (var entry : familia.pilares.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> null;
                                case LISO -> familia.liso;
                                case POLIDO -> null;
                                case TIJOLOS -> null;
                                case TIJOLOS_POLIDO -> null;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
                        if (material != null) {
                                stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS,
                                                entry.getValue().get(), familia.base.get(), 1);
                        }
                }
        }

        private void receitaPedra(FamiliaPedra familia, RecipeOutput output, boolean cortadorDePedra) {

                if (familia.polido != null) {
                        polished(output, RecipeCategory.BUILDING_BLOCKS, familia.polido.get(),
                                        familia.base.get());
                }

                if (familia.tijolos != null) {
                        polished(output, RecipeCategory.BUILDING_BLOCKS, familia.tijolos.get(), familia.base.get());
                }

                if (familia.tijolosPolido != null) {
                        polished(output, RecipeCategory.BUILDING_BLOCKS, familia.tijolosPolido.get(),
                                        familia.polido.get());
                }

                if (familia.variantesBase != null) {
                        receitaVariantes(familia.variantesBase.escada(), familia.variantesBase.laje(),
                                        familia.variantesBase.muro(), familia.base.get(), output);
                }

                if (familia.variantesPolido != null) {
                        receitaVariantes(familia.variantesPolido.escada(), familia.variantesPolido.laje(),
                                        familia.variantesPolido.muro(), familia.polido.get(), output);
                }

                if (familia.variantesTijolos != null) {
                        receitaVariantes(familia.variantesTijolos.escada(), familia.variantesTijolos.laje(),
                                        familia.variantesTijolos.muro(), familia.tijolos.get(), output);
                }

                if (familia.variantesTijolosPolido != null) {
                        receitaVariantes(familia.variantesTijolosPolido.escada(), familia.variantesTijolosPolido.laje(),
                                        familia.variantesTijolosPolido.muro(), familia.tijolosPolido.get(), output);
                }

                if (familia.variantesLiso != null) {
                        if (familia.variantesLiso.escada() != null)
                                receitaVariantes(familia.variantesLiso.escada(), familia.variantesLiso.laje(),
                                                familia.variantesLiso.muro(), familia.liso.get(), output);
                }

                if (cortadorDePedra) {
                        receitaCortadorDePedra(familia, output);
                }

                for (var entry : familia.rachados.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> null;
                                case LISO -> null;
                                case POLIDO -> null;
                                case TIJOLOS -> familia.tijolos;
                                case TIJOLOS_POLIDO -> familia.tijolosPolido;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
                        if (material != null) {
                                smeltingResultFromBase(output, entry.getValue().get(), material.get());
                        }
                }

                if (familia.liso != null) {
                        smeltingResultFromBase(output, familia.liso.get(), familia.base.get());
                }

                for (var entry : familia.talhados.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> familia.base;
                                case LISO -> familia.liso;
                                case POLIDO -> familia.polido;
                                case TIJOLOS -> familia.tijolos;
                                case TIJOLOS_POLIDO -> familia.tijolosPolido;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
                        if (material != null) {
                                chiseled(output, RecipeCategory.BUILDING_BLOCKS, entry.getValue().get(),
                                                material.get());
                        }
                }

                for (var entry : familia.pressoes.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> familia.base;
                                case LISO -> null;
                                case POLIDO -> familia.polido;
                                case TIJOLOS -> null;
                                case TIJOLOS_POLIDO -> null;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
                        if (material != null) {
                                pressurePlate(output, entry.getValue().get(),
                                                material.get());
                        }
                }

                for (var entry : familia.bototes.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> familia.base;
                                case LISO -> null;
                                case POLIDO -> familia.polido;
                                case TIJOLOS -> null;
                                case TIJOLOS_POLIDO -> null;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
                        if (material != null) {
                                buttonBuilder(entry.getValue().get(),
                                                Ingredient.of(new ItemLike[] { material.get() }));
                        }
                }

                for (var entry : familia.pilares.entrySet()) {
                        DeferredBlock<Block> material = switch (entry.getKey()) {
                                case BASE -> familia.base;
                                case LISO -> familia.liso;
                                case POLIDO -> familia.polido;
                                case TIJOLOS -> familia.tijolos;
                                case TIJOLOS_POLIDO -> familia.tijolosPolido;
                                case LADRILHOS -> null;
                                case PLACAS -> null;
                        };
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