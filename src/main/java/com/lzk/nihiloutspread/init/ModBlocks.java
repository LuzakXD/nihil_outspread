package com.lzk.nihiloutspread.init;

import java.util.Map;
import java.util.Set;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.blockGens.*;
import com.lzk.nihiloutspread.blocks.*;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.SoundType;

import java.util.function.Supplier;

public class ModBlocks {

        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NihilOutspread.MODID);

        private static <T extends Block> DeferredBlock<T> registrarBlocoItem(String nome, Supplier<T> fabrica) {
                DeferredBlock<T> bloco = BLOCKS.register(nome, fabrica);
                ModItems.ITEMS.registerSimpleBlockItem(nome, bloco);
                return bloco;
        }

        // Nihil
        public static final DeferredBlock<Block> NIHIL_GRASS_BLOCK;
        public static final DeferredBlock<Block> CHECKER_GRASS_BLOCK;
        public static final DeferredBlock<Block> NIHIL_DIRT;
        public static final DeferredBlock<NihilBushesBlock> NIHIL_FERN;
        public static final DeferredBlock<NihilBushesBlock> NIHIL_BUSH;
        public static final DeferredBlock<NihilBushesBlock> NIHIL_HERB;
        public static final DeferredBlock<NihilSroutsBlock> NIHIL_SPROUT;
        public static final DeferredBlock<NihilSroutsBlock> DREAD_SPROUT;
        public static final DeferredBlock<PetalsBlock> NIHIL_PETALS;
        public static final DeferredBlock<Block> SPIRAL_PLANKS;
        public static final FamiliaMadeira SPIRAL_FAMILY;
        public static final DeferredBlock<Block> NILIGHT;
        public static final FamiliaPedra NILIGHT_FAMILY;
        public static final DeferredBlock<Block> GRAPHITE;
        public static final FamiliaPedra GRAPHITE_FAMILY;
        public static final DeferredBlock<Block> SODALITE;
        public static final FamiliaPedra SODALITE_FAMILY;
        public static final DeferredBlock<ColoredFallingBlock> VELVET_SAND;
        public static final DeferredBlock<Block> VELVESTONE;
        public static final FamiliaAreia VELVESTONE_FAMILY;
        public static final DeferredBlock<Block> COTTONITE;
        public static final FamiliaPedra COTTONITE_FAMILY;

        static {

                NIHIL_GRASS_BLOCK = registrarBlocoItem("nihil_grass_block",
                                () -> new NihilGrassBlock(BlockBehaviour.Properties.of()
                                                .sound(SoundType.GRASS)
                                                .mapColor(MapColor.COLOR_BLACK)
                                                .strength(0.5f, 0.5f)
                                                .instrument(NoteBlockInstrument.BASEDRUM)));

                CHECKER_GRASS_BLOCK = registrarBlocoItem("checker_grass_block",
                                () -> new CheckerGrassBlock(BlockBehaviour.Properties.of()
                                                .sound(SoundType.GRASS)
                                                .mapColor(MapColor.COLOR_BLACK)
                                                .strength(0.5f, 0.5f)
                                                .instrument(NoteBlockInstrument.BASEDRUM)));

                NIHIL_DIRT = registrarBlocoItem("nihil_dirt",
                                () -> new Block(BlockBehaviour.Properties.of()
                                                .sound(SoundType.GRASS)
                                                .mapColor(MapColor.COLOR_BLACK).strength(0.5f, 0.5f)
                                                .instrument(NoteBlockInstrument.GUITAR)));

                NIHIL_FERN = registrarBlocoItem("nihil_fern",
                                () -> new NihilBushesBlock(
                                                BlockBehaviour.Properties.of()
                                                                .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                                                                .replaceable()
                                                                .noCollission()
                                                                .instabreak()
                                                                .sound(SoundType.GRASS)
                                                                .offsetType(OffsetType.XZ)
                                                                .ignitedByLava()
                                                                .pushReaction(PushReaction.DESTROY)));

                NIHIL_BUSH = registrarBlocoItem(
                                "nihil_bush",
                                () -> new NihilBushesBlock(
                                                BlockBehaviour.Properties.of()
                                                                .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                                                                .replaceable()
                                                                .noCollission()
                                                                .instabreak()
                                                                .sound(SoundType.GRASS)
                                                                .offsetType(OffsetType.XZ)
                                                                .ignitedByLava()
                                                                .pushReaction(PushReaction.DESTROY)));

                NIHIL_HERB = registrarBlocoItem(
                                "nihil_herb",
                                () -> new NihilBushesBlock(
                                                BlockBehaviour.Properties.of()
                                                                .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                                                                .replaceable()
                                                                .noCollission()
                                                                .instabreak()
                                                                .sound(SoundType.GRASS)
                                                                .offsetType(OffsetType.XZ)
                                                                .ignitedByLava()
                                                                .pushReaction(PushReaction.DESTROY)));

                NIHIL_SPROUT = registrarBlocoItem(
                                "nihil_sprout",
                                () -> new NihilSroutsBlock(
                                                BlockBehaviour.Properties.of()
                                                                .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                                                                .replaceable()
                                                                .noCollission()
                                                                .instabreak()
                                                                .sound(SoundType.GRASS)
                                                                .offsetType(OffsetType.XYZ)
                                                                .ignitedByLava()
                                                                .pushReaction(PushReaction.DESTROY)));

                DREAD_SPROUT = registrarBlocoItem(
                                "dread_sprout",
                                () -> new NihilSroutsBlock(
                                                BlockBehaviour.Properties.of()
                                                                .mapColor(MapColor.TERRACOTTA_BLACK)
                                                                .replaceable()
                                                                .noCollission()
                                                                .instabreak()
                                                                .sound(SoundType.GRASS)
                                                                .offsetType(OffsetType.XYZ)
                                                                .ignitedByLava()
                                                                .pushReaction(PushReaction.DESTROY)));

                NIHIL_PETALS = registrarBlocoItem(
                                "nihil_petals",
                                () -> new PetalsBlock(
                                                BlockBehaviour.Properties.of()
                                                                .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                                                                .noCollission()
                                                                .instabreak()
                                                                .sound(SoundType.GRASS)
                                                                .ignitedByLava()
                                                                .pushReaction(PushReaction.DESTROY)));

                SPIRAL_PLANKS = registrarBlocoItem("spiral_planks",
                                () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE)
                                                .strength(2, 3).sound(ModBlockSetTypes.NYVERIN.soundType())) {
                                        @Override
                                        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos,
                                                        Direction direction) {
                                                return true;
                                        }

                                        @Override
                                        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos,
                                                        Direction direction) {
                                                return 5;
                                        }

                                        @Override
                                        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos,
                                                        Direction direction) {
                                                return 20;
                                        }
                                });

                SPIRAL_FAMILY = new FamiliaMadeira(
                                BLOCKS,
                                ModItems.ITEMS,
                                SPIRAL_PLANKS,
                                ModBlockSetTypes.NYVERIN,
                                "_planks",
                                BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1, 4)
                                                .sound(ModBlockSetTypes.NYVERIN.soundType()),
                                Map.of(
                                                TipoMaterial.BASE,
                                                Set.of(
                                                                TipoVariante.ESCADA,
                                                                TipoVariante.LAJE,
                                                                TipoVariante.CERCA,
                                                                TipoVariante.PORTAO,
                                                                TipoVariante.BOTAO,
                                                                TipoVariante.PORTA,
                                                                TipoVariante.ALCAPAO,
                                                                TipoVariante.PLACAPRESSAO),
                                                TipoMaterial.TRONCO,
                                                Set.of(
                                                                TipoVariante.DESCASCADO),
                                                TipoMaterial.MADEIRA,
                                                Set.of(
                                                                TipoVariante.DESCASCADO),
                                                TipoMaterial.FOLHA,
                                                Set.of()));

                NILIGHT = registrarBlocoItem("nilight",
                                () -> new NilightBlock(BlockBehaviour.Properties.of()
                                                .mapColor(MapColor.COLOR_BLACK).strength(3, 10)
                                                .instrument(NoteBlockInstrument.BASEDRUM)));

                NILIGHT_FAMILY = new FamiliaPedra(
                                BLOCKS,
                                ModItems.ITEMS,
                                NILIGHT,
                                BlockSetType.STONE,
                                "",
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

                GRAPHITE = registrarBlocoItem("graphite",
                                () -> new Block(BlockBehaviour.Properties.of()
                                                .mapColor(MapColor.DEEPSLATE)
                                                .strength(1.5f, 6)));

                GRAPHITE_FAMILY = new FamiliaPedra(
                                BLOCKS,
                                ModItems.ITEMS,
                                GRAPHITE,
                                BlockSetType.STONE,
                                "",
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

                SODALITE = registrarBlocoItem("sodalite",
                                () -> new Block(BlockBehaviour.Properties.of()
                                                .mapColor(MapColor.TERRACOTTA_BLUE)
                                                .strength(1, 6)));

                SODALITE_FAMILY = new FamiliaPedra(
                                BLOCKS,
                                ModItems.ITEMS,
                                SODALITE,
                                BlockSetType.STONE,
                                "",
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
                                                                TipoVariante.MURO),
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

                VELVET_SAND = registrarBlocoItem("velvet_sand",
                                () -> new ColoredFallingBlock(new ColorRGBA(148046076),
                                                BlockBehaviour.Properties.of()
                                                                .mapColor(MapColor.COLOR_ORANGE)
                                                                .instrument(NoteBlockInstrument.SNARE)
                                                                .strength(0.5F)
                                                                .sound(SoundType.SAND)));

                VELVESTONE = registrarBlocoItem("velvestone",
                                () -> new Block(BlockBehaviour.Properties.of()
                                                .mapColor(MapColor.COLOR_ORANGE)
                                                .instrument(NoteBlockInstrument.BASEDRUM)
                                                .strength(1.5F)
                                                .sound(SoundType.STONE)));

                VELVESTONE_FAMILY = new FamiliaAreia(
                                BLOCKS,
                                ModItems.ITEMS,
                                VELVESTONE,
                                BlockSetType.STONE,
                                "",
                                BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)
                                                .instrument(NoteBlockInstrument.BASEDRUM).strength(1.5F),
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

                COTTONITE = registrarBlocoItem("cottonite",
                                () -> new Block(BlockBehaviour.Properties.of()
                                                .mapColor(MapColor.TERRACOTTA_WHITE)
                                                .strength(1, 6)));

                COTTONITE_FAMILY = new FamiliaPedra(
                                BLOCKS,
                                ModItems.ITEMS,
                                COTTONITE,
                                BlockSetType.STONE,
                                "",
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

        }

        // Abyss
        public static final DeferredBlock<Block> ABYSSAL_MOSS_BLOCK;
        public static final FamiliaPedra ABYSSAL_MOSS_FAMILY;
        public static final DeferredBlock<Block> ABYSSAL_FLORAL_MOSS_BLOCK;
        public static final DeferredBlock<Block> ABYSSAL_MOSS_CARPET;
        public static final DeferredBlock<Block> ABYSSAL_FLORAL_MOSS_CARPET;
        public static final DeferredBlock<Block> ABYSSAL_STONE;
        public static final DeferredBlock<Block> ABYSSAL_MOSSY_STONE;
        public static final FamiliaPedra ABYSSAL_STONE_FAMILY;

        static {
                ABYSSAL_MOSS_BLOCK = registrarBlocoItem("abyssal_moss_block",
                                () -> new AbyssalMossBlock(BlockBehaviour.Properties.of()
                                                .mapColor(MapColor.PLANT)
                                                .strength(0.1f)
                                                .instrument(NoteBlockInstrument.FLUTE)
                                                .sound(SoundType.MOSS)));

                ABYSSAL_MOSS_FAMILY = new FamiliaPedra(BLOCKS,
                                ModItems.ITEMS,
                                ABYSSAL_MOSS_BLOCK,
                                BlockSetType.STONE,
                                "_block",
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

                ABYSSAL_FLORAL_MOSS_BLOCK = registrarBlocoItem(
                                "abyssal_floral_moss_block",
                                () -> new AbyssalMossBlock(BlockBehaviour.Properties.of()
                                                .mapColor(MapColor.PLANT)
                                                .strength(0.1f)
                                                .instrument(NoteBlockInstrument.FLUTE)
                                                .sound(SoundType.MOSS)));

                ABYSSAL_MOSS_CARPET = registrarBlocoItem("abyssal_moss_carpet",
                                () -> new CarpetBlock(BlockBehaviour.Properties.of()
                                                .mapColor(MapColor.PLANT)
                                                .strength(0.1f)
                                                .pushReaction(PushReaction.DESTROY)
                                                .sound(SoundType.MOSS)));

                ABYSSAL_FLORAL_MOSS_CARPET = registrarBlocoItem(
                                "abyssal_floral_moss_carpet",
                                () -> new CarpetBlock(BlockBehaviour.Properties.of()
                                                .mapColor(MapColor.PLANT)
                                                .strength(0.1f)
                                                .pushReaction(PushReaction.DESTROY)
                                                .sound(SoundType.MOSS)));

                ABYSSAL_STONE = registrarBlocoItem(
                                "abyssal_stone",
                                () -> new AbyssalStoneBlock(BlockBehaviour.Properties.of()
                                                .mapColor(MapColor.METAL)
                                                .sound(SoundType.DEEPSLATE)
                                                .strength(2f, 4f)
                                                .instrument(NoteBlockInstrument.BASEDRUM)));

                ABYSSAL_MOSSY_STONE = registrarBlocoItem(
                                "abyssal_mossy_stone",
                                () -> new AbyssalMossyStoneBlock(BlockBehaviour.Properties.of()
                                                .mapColor(MapColor.PLANT)
                                                .sound(SoundType.DEEPSLATE)
                                                .strength(2.1f, 4f)
                                                .instrument(NoteBlockInstrument.BASEDRUM)));

                ABYSSAL_STONE_FAMILY = new FamiliaPedra(
                                BLOCKS,
                                ModItems.ITEMS,
                                ABYSSAL_STONE,
                                BlockSetType.STONE,
                                "",
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
        }

        // Glacier
        public static final DeferredBlock<Block> NYVERIN_PLANKS;
        public static final FamiliaMadeira NYVERIN_FAMILY;

        static {

                NYVERIN_PLANKS = registrarBlocoItem("nyverin_planks",
                                () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE)
                                                .strength(2, 3).sound(ModBlockSetTypes.NYVERIN.soundType())) {
                                        @Override
                                        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos,
                                                        Direction direction) {
                                                return true;
                                        }

                                        @Override
                                        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos,
                                                        Direction direction) {
                                                return 5;
                                        }

                                        @Override
                                        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos,
                                                        Direction direction) {
                                                return 20;
                                        }
                                });

                NYVERIN_FAMILY = new FamiliaMadeira(
                                BLOCKS,
                                ModItems.ITEMS,
                                NYVERIN_PLANKS,
                                ModBlockSetTypes.NYVERIN,
                                "_planks",
                                BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(1, 4)
                                                .sound(
                                                                ModBlockSetTypes.NYVERIN
                                                                                .soundType()),
                                Map.of(
                                                TipoMaterial.BASE,
                                                Set.of(
                                                                TipoVariante.ESCADA,
                                                                TipoVariante.LAJE,
                                                                TipoVariante.CERCA,
                                                                TipoVariante.PORTAO,
                                                                TipoVariante.BOTAO,
                                                                TipoVariante.PORTA,
                                                                TipoVariante.ALCAPAO,
                                                                TipoVariante.PLACAPRESSAO),
                                                TipoMaterial.TRONCO,
                                                Set.of(
                                                                TipoVariante.DESCASCADO),
                                                TipoMaterial.MADEIRA,
                                                Set.of(
                                                                TipoVariante.DESCASCADO),
                                                TipoMaterial.FOLHA,
                                                Set.of()));
        }

        // Hive
        private static final ResourceKey<Block> honeycombKey;
        public static final DeferredBlock<Block> HONEYCOMB_BASE;
        public static final FamiliaPedra HONEYCOMB_FAMILY;
        public static final DeferredBlock<Block> XOR_GATE;
        public static final DeferredBlock<Block> OR_GATE;
        public static final DeferredBlock<Block> AND_GATE;

        static {
                honeycombKey = ResourceKey.create(
                                Registries.BLOCK,
                                ResourceLocation.withDefaultNamespace("honeycomb_block"));

                HONEYCOMB_BASE = DeferredBlock.createBlock(honeycombKey);

                HONEYCOMB_FAMILY = new FamiliaPedra(BLOCKS,
                                ModItems.ITEMS,
                                HONEYCOMB_BASE,
                                BlockSetType.STONE,
                                "_block",
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

                XOR_GATE = registrarBlocoItem(
                                "xor_gate",
                                () -> new XORGateBlock(BlockBehaviour.Properties.of()
                                                .instabreak()
                                                .sound(SoundType.CORAL_BLOCK)
                                                .pushReaction(PushReaction.DESTROY)));

                OR_GATE = registrarBlocoItem(
                                "or_gate",
                                () -> new ORGateBlock(BlockBehaviour.Properties.of()
                                                .instabreak()
                                                .sound(SoundType.CORAL_BLOCK)
                                                .pushReaction(PushReaction.DESTROY)));

                AND_GATE = registrarBlocoItem(
                                "and_gate",
                                () -> new ANDGateBlock(BlockBehaviour.Properties.of()
                                                .instabreak()
                                                .sound(SoundType.CORAL_BLOCK)
                                                .pushReaction(PushReaction.DESTROY)));

        }

}
