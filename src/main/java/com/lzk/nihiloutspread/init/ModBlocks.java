package com.lzk.nihiloutspread.init;

import java.util.Map;
import java.util.Set;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.blockGens.*;
import com.lzk.nihiloutspread.blocks.*;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TallGrassBlock;

import java.util.function.Supplier;

public class ModBlocks {

        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NihilOutspread.MODID);

        private static <T extends Block> DeferredBlock<T> registrarBlocoItem(String nome, Supplier<T> fabrica) {
                DeferredBlock<T> bloco = BLOCKS.register(nome, fabrica);
                ModItems.ITEMS.registerSimpleBlockItem(nome, bloco);
                return bloco;
        }

        private static final ResourceKey<Block> honeycombKey = ResourceKey.create(
                        Registries.BLOCK,
                        ResourceLocation.withDefaultNamespace("honeycomb_block"));

        public static final DeferredBlock<Block> NIHIL_GRASS_BLOCK = registrarBlocoItem("nihil_grass_block",
                        () -> new NihilGrassBlock(BlockBehaviour.Properties.of()
                                        .sound(SoundType.GRASS)
                                        .mapColor(MapColor.COLOR_BLACK).strength(0.5f, 0.5f)
                                        .instrument(NoteBlockInstrument.BASEDRUM)));

        public static final DeferredBlock<Block> CHECKER_GRASS_BLOCK = registrarBlocoItem("checker_grass_block",
                        () -> new CheckerGrassBlock(BlockBehaviour.Properties.of()
                                        .sound(SoundType.GRASS)
                                        .mapColor(MapColor.COLOR_BLACK).strength(0.5f, 0.5f)
                                        .instrument(NoteBlockInstrument.BASEDRUM)));

        public static final DeferredBlock<Block> NIHIL_DIRT = registrarBlocoItem("nihil_dirt",
                        () -> new Block(BlockBehaviour.Properties.of()
                                        .sound(SoundType.GRASS)
                                        .mapColor(MapColor.COLOR_BLACK).strength(0.5f, 0.5f)
                                        .instrument(NoteBlockInstrument.GUITAR)));

        public static final DeferredBlock<Block> NILIGHT = registrarBlocoItem("nilight",
                        () -> new NilightBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.COLOR_BLACK).strength(3, 10)
                                        .instrument(NoteBlockInstrument.BASEDRUM)));

        public static final FamiliaPedra NILIGHT_BLOCK_FAMILY = new FamiliaPedra(
                        BLOCKS,
                        ModItems.ITEMS,
                        NILIGHT,
                        BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(3, 10),
                        false,
                        Map.of(
                                        TipoMaterial.BASE,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO),
                                        TipoMaterial.POLIDO,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO,
                                                        TipoVariante.TALHADO,
                                                        TipoVariante.PLACAPRESSAO,
                                                        TipoVariante.BOTAO),
                                        TipoMaterial.TIJOLOS,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO,
                                                        TipoVariante.RACHADO),
                                        TipoMaterial.PLACAS,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO,
                                                        TipoVariante.RACHADO)));

        public static final DeferredBlock<Block> GRAPHITE = registrarBlocoItem("graphite",
                        () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(1.5f, 6)));

        public static final FamiliaPedra GRAPHITE_BLOCK_FAMILY = new FamiliaPedra(
                        BLOCKS,
                        ModItems.ITEMS,
                        GRAPHITE,
                        BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(1.5f, 6),
                        true,
                        Map.of(
                                        TipoMaterial.BASE,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO),
                                        TipoMaterial.POLIDO,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO),
                                        TipoMaterial.TIJOLOS,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO,
                                                        TipoVariante.RACHADO)));

        public static final DeferredBlock<ColoredFallingBlock> VELVET_SAND = registrarBlocoItem("velvet_sand",
                        () -> new ColoredFallingBlock(new ColorRGBA(148046076),
                                        BlockBehaviour.Properties.of()
                                                        .mapColor(MapColor.COLOR_ORANGE)
                                                        .instrument(NoteBlockInstrument.SNARE)
                                                        .strength(0.5F)
                                                        .sound(SoundType.SAND)));

        public static final DeferredBlock<Block> VELVESTONE = registrarBlocoItem("velvestone",
                        () -> new Block(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.COLOR_ORANGE)
                                        .instrument(NoteBlockInstrument.SNARE)
                                        .strength(1.5F)
                                        .sound(SoundType.STONE)));

        public static final FamiliaAreia VELVESTONE_BLOCK_FAMILY = new FamiliaAreia(
                        BLOCKS,
                        ModItems.ITEMS,
                        VELVESTONE,
                        BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(1.5F),
                        Map.of(
                                        TipoMaterial.BASE,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO),
                                        TipoMaterial.PEDREGOSO,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO),
                                        TipoMaterial.CORTADO,
                                        Set.of(
                                                        TipoVariante.ESCADA),
                                        TipoMaterial.LISO,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE)));

        public static final DeferredBlock<Block> COTTONITE = registrarBlocoItem("cottonite",
                        () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(1,
                                        6)));

        public static final FamiliaPedra COTTONITE_BLOCK_FAMILY = new FamiliaPedra(
                        BLOCKS,
                        ModItems.ITEMS,
                        COTTONITE,
                        BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(1, 4),
                        true,
                        Map.of(
                                        TipoMaterial.BASE,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO),
                                        TipoMaterial.POLIDO,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO,
                                                        TipoVariante.PILAR),
                                        TipoMaterial.TIJOLOS,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO),
                                        TipoMaterial.LADRILHOS,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO)));

        // TODO: fazer essas graminhas terem sua propria class
        // pra poder sobreviver apenas em grama nihil

        public static final DeferredBlock<Block> NIHIL_FERN = registrarBlocoItem("nihil_fern",
                        () -> new TallGrassBlock(
                                        BlockBehaviour.Properties.of()
                                                        .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                                                        .replaceable()
                                                        .noCollission()
                                                        .instabreak()
                                                        .sound(SoundType.GRASS)
                                                        .offsetType(OffsetType.XYZ)
                                                        .ignitedByLava()
                                                        .pushReaction(PushReaction.DESTROY)));

        public static final DeferredBlock<Block> NIHIL_SHORT_GRASS = registrarBlocoItem(
                        "nihil_short_grass",
                        () -> new TallGrassBlock(
                                        BlockBehaviour.Properties.of()
                                                        .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                                                        .replaceable()
                                                        .noCollission()
                                                        .instabreak()
                                                        .sound(SoundType.GRASS)
                                                        .offsetType(OffsetType.XYZ)
                                                        .ignitedByLava()
                                                        .pushReaction(PushReaction.DESTROY)));

        public static final DeferredBlock<Block> NIHIL_SMALL_HERB = registrarBlocoItem(
                        "nihil_small_herb",
                        () -> new TallGrassBlock(
                                        BlockBehaviour.Properties.of()
                                                        .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                                                        .replaceable()
                                                        .noCollission()
                                                        .instabreak()
                                                        .sound(SoundType.GRASS)
                                                        .offsetType(OffsetType.XYZ)
                                                        .ignitedByLava()
                                                        .pushReaction(PushReaction.DESTROY)));

        public static final DeferredBlock<Block> HONEYCOMB_BASE = DeferredBlock.createBlock(honeycombKey);

        public static final FamiliaPedra HONEYCOMB_BLOCK_FAMILY = new FamiliaPedra(BLOCKS,
                        ModItems.ITEMS,
                        HONEYCOMB_BASE,
                        BlockBehaviour.Properties.ofFullCopy(Blocks.HONEYCOMB_BLOCK),
                        true,
                        Map.of(
                                        TipoMaterial.POLIDO, Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO,
                                                        TipoVariante.PILAR),
                                        TipoMaterial.TIJOLOS, Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO)));

        public static final DeferredBlock<Block> ABYSSAL_MOSS_BLOCK = registrarBlocoItem("abyssal_moss_block",
                        () -> new AbyssalMossBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.PLANT)
                                        .strength(0.1f)
                                        .instrument(NoteBlockInstrument.FLUTE)
                                        .sound(SoundType.MOSS)));

        public static final FamiliaPedra ABYSSAL_MOSS_BLOCK_FAMILY = new FamiliaPedra(BLOCKS,
                        ModItems.ITEMS,
                        ABYSSAL_MOSS_BLOCK,
                        BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.PLANT)
                                        .strength(0.1f)
                                        .instrument(NoteBlockInstrument.FLUTE)
                                        .sound(SoundType.MOSS),
                        false,
                        Map.of(
                                        TipoMaterial.BASE,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO)));

        public static final DeferredBlock<Block> ABYSSAL_FLORAL_MOSS_BLOCK = registrarBlocoItem(
                        "abyssal_floral_moss_block",
                        () -> new AbyssalMossBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.PLANT)
                                        .strength(0.1f)
                                        .instrument(NoteBlockInstrument.FLUTE)
                                        .sound(SoundType.MOSS)));

        public static final DeferredBlock<Block> ABYSSAL_MOSS_CARPET = registrarBlocoItem("abyssal_moss_carpet",
                        () -> new CarpetBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.PLANT)
                                        .strength(0.1f)
                                        .pushReaction(PushReaction.DESTROY)
                                        .sound(SoundType.MOSS)));

        public static final DeferredBlock<Block> ABYSSAL_FLORAL_MOSS_CARPET = registrarBlocoItem(
                        "abyssal_floral_moss_carpet",
                        () -> new CarpetBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.PLANT)
                                        .strength(0.1f)
                                        .pushReaction(PushReaction.DESTROY)
                                        .sound(SoundType.MOSS)));

        public static final DeferredBlock<Block> ABYSSAL_STONE = registrarBlocoItem(
                        "abyssal_stone",
                        () -> new AbyssalStoneBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.METAL)
                                        .sound(SoundType.DEEPSLATE)
                                        .strength(2f, 4f)
                                        .instrument(NoteBlockInstrument.BASEDRUM)));

        public static final DeferredBlock<Block> ABYSSAL_MOSSY_STONE = registrarBlocoItem(
                        "abyssal_mossy_stone",
                        () -> new AbyssalMossyStoneBlock(BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.PLANT)
                                        .sound(SoundType.DEEPSLATE)
                                        .strength(2.1f, 4f)
                                        .instrument(NoteBlockInstrument.BASEDRUM)));

        public static final FamiliaPedra ABYSSAL_STONE_FAMILY = new FamiliaPedra(
                        BLOCKS,
                        ModItems.ITEMS,
                        ABYSSAL_STONE,
                        BlockBehaviour.Properties.of()
                                        .mapColor(MapColor.METAL)
                                        .sound(SoundType.DEEPSLATE)
                                        .strength(2f, 6f)
                                        .instrument(NoteBlockInstrument.BASEDRUM),
                        false,
                        Map.of(
                                        TipoMaterial.BASE,
                                        Set.of(
                                                        TipoVariante.ESCADA,
                                                        TipoVariante.LAJE,
                                                        TipoVariante.MURO,
                                                        TipoVariante.PLACAPRESSAO,
                                                        TipoVariante.BOTAO)));

        // Redstone da Colmeia

        public static final DeferredBlock<Block> XOR_GATE = registrarBlocoItem(
                        "xor_gate",
                        () -> new XORGateBlock(BlockBehaviour.Properties.of()
                                        .instabreak()
                                        .sound(SoundType.STONE)
                                        .pushReaction(PushReaction.DESTROY)));

        public static final DeferredBlock<Block> OR_GATE = registrarBlocoItem(
                        "or_gate",
                        () -> new ORGateBlock(BlockBehaviour.Properties.of()
                                        .instabreak()
                                        .sound(SoundType.STONE)
                                        .pushReaction(PushReaction.DESTROY)));

        public static final DeferredBlock<Block> AND_GATE = registrarBlocoItem(
                        "and_gate",
                        () -> new ANDGateBlock(BlockBehaviour.Properties.of()
                                        .instabreak()
                                        .sound(SoundType.STONE)
                                        .pushReaction(PushReaction.DESTROY)));

}
