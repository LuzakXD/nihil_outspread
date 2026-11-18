package com.lzk.nihiloutspread.blockGens;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public abstract class FamiliaAbstrata {

    public final DeferredBlock<Block> base;
    public final String nomeCompleto;
    public final String nomeBase;
    public final String sufixo;

    protected final DeferredRegister.Blocks registro;
    protected final DeferredRegister.Items registroItem;

    protected final List<DeferredBlock<? extends Block>> todosOsBlocos = new ArrayList<>();
    protected final List<Variantes> todosAsVariantes = new ArrayList<>();

    protected final Map<TipoMaterial, DeferredBlock<Block>> materiais = new EnumMap<>(TipoMaterial.class);

    // TODO: Add Sound, Block and Wood Types.

    protected FamiliaAbstrata(DeferredRegister.Blocks reg, DeferredRegister.Items regItem,
            DeferredBlock<Block> blocoBase, String sufixo) {

        this.registro = reg;
        this.registroItem = regItem;
        this.base = blocoBase;
        materiais.put(TipoMaterial.BASE, base);
        this.nomeCompleto = blocoBase.getId().getPath();
        this.sufixo = sufixo;

        this.nomeBase = limparNome(nomeCompleto, sufixo);
    }

    public String limparNome(String nome, String sufixo) {
        if (nome.endsWith(sufixo))
            nome = nome.substring(0, nome.length() - sufixo.length());
        return nome;
    }

    protected DeferredBlock<Block> registrarMaterial(TipoMaterial tipo, String nome,
            BlockBehaviour.Properties props) {
        DeferredBlock<Block> bloco = registrar(nome, props);
        materiais.put(tipo, bloco);
        return bloco;
    }

    protected DeferredBlock<Block> registrar(String nome, BlockBehaviour.Properties props) {
        DeferredBlock<Block> bloco = registro.register(nome, () -> new Block(props));
        registroItem.registerSimpleBlockItem(nome, bloco);
        todosOsBlocos.add(bloco);
        return bloco;
    }

    protected Variantes registrarVariantes(
            TipoMaterial tipoMaterial,
            String prefixo,
            boolean plural,
            DeferredBlock<? extends Block> blocoBaseDaVariante,
            BlockBehaviour.Properties props,
            BlockSetType tipo,
            Set<TipoVariante> variantes) {

        DeferredBlock<StairBlock> escada = null;
        DeferredBlock<SlabBlock> laje = null;
        DeferredBlock<WallBlock> muro = null;

        DeferredBlock<FenceBlock> cerca = null;
        DeferredBlock<FenceGateBlock> portao = null;
        DeferredBlock<DoorBlock> porta = null;
        DeferredBlock<TrapDoorBlock> alcapao = null;

        DeferredBlock<RotatedPillarBlock> pilar = null;
        DeferredBlock<RotatedPillarBlock> descascado = null;
        DeferredBlock<Block> talhado = null;
        DeferredBlock<Block> rachado = null;

        DeferredBlock<PressurePlateBlock> pressao = null;
        DeferredBlock<ButtonBlock> botao = null;
        String sufixo = plural ? "s" : "";

        if (variantes.contains(TipoVariante.DESCASCADO)) {
            descascado = registro.register("stripped_" + prefixo, () -> new RotatedPillarBlock(props));
            registroItem.registerSimpleBlockItem("stripped_" + prefixo, descascado);
            todosOsBlocos.add(descascado);

        }

        if (variantes.contains(TipoVariante.PILAR)) {
            pilar = registro.register(prefixo + "_pillar", () -> new RotatedPillarBlock(props));
            registroItem.registerSimpleBlockItem(prefixo + "_pillar", pilar);
            todosOsBlocos.add(pilar);
        }

        if (variantes.contains(TipoVariante.ESCADA)) {
            escada = registro.register(prefixo + "_stairs",
                    () -> new StairBlock(blocoBaseDaVariante.get().defaultBlockState(), props));
            registroItem.registerSimpleBlockItem(prefixo + "_stairs", escada);
            todosOsBlocos.add(escada);
        }
        if (variantes.contains(TipoVariante.LAJE)) {
            laje = registro.register(prefixo + "_slab", () -> new SlabBlock(props));
            registroItem.registerSimpleBlockItem(prefixo + "_slab", laje);
            todosOsBlocos.add(laje);
        }
        if (variantes.contains(TipoVariante.MURO)) {
            muro = registro.register(prefixo + "_wall", () -> new WallBlock(props));
            registroItem.registerSimpleBlockItem(prefixo + "_wall", muro);
            todosOsBlocos.add(muro);
        }

        if (variantes.contains(TipoVariante.TALHADO)) {
            talhado = registro.register("chiseled_" + prefixo + sufixo, () -> new Block(props));
            registroItem.registerSimpleBlockItem("chiseled_" + prefixo + sufixo, talhado);
            todosOsBlocos.add(talhado);
        }

        if (variantes.contains(TipoVariante.RACHADO)) {
            rachado = registro.register("cracked_" + prefixo + sufixo, () -> new Block(props));
            registroItem.registerSimpleBlockItem("cracked_" + prefixo + sufixo, rachado);
            todosOsBlocos.add(rachado);
        }

        if (variantes.contains(TipoVariante.CERCA)) {
            cerca = registro.register(prefixo + "_fence", () -> new FenceBlock(props));
            registroItem.registerSimpleBlockItem(prefixo + "_fence", cerca);
            todosOsBlocos.add(cerca);
        }

        if (variantes.contains(TipoVariante.PORTAO)) {
            portao = registro.register(prefixo + "_fence_gate", () -> new FenceGateBlock(WoodType.OAK, props));
            registroItem.registerSimpleBlockItem(prefixo + "_fence_gate", portao);
            todosOsBlocos.add(portao);
        }

        if (variantes.contains(TipoVariante.PORTA)) {
            porta = registro.register(prefixo + "_door", () -> new DoorBlock(
                    tipo, props
                            .noOcclusion()));
            registroItem.registerSimpleBlockItem(prefixo + "_door", porta);
            todosOsBlocos.add(porta);
        }

        if (variantes.contains(TipoVariante.ALCAPAO)) {
            alcapao = registro.register(prefixo + "_trapdoor", () -> new TrapDoorBlock(tipo, props
                    .noOcclusion()));
            registroItem.registerSimpleBlockItem(prefixo + "_trapdoor", alcapao);
            todosOsBlocos.add(alcapao);
        }

        if (variantes.contains(TipoVariante.PLACAPRESSAO)) {
            pressao = registro.register(prefixo + "_pressure_plate",
                    () -> new PressurePlateBlock(tipo, props));
            registroItem.registerSimpleBlockItem(prefixo + "_pressure_plate", pressao);
            todosOsBlocos.add(pressao);
        }

        if (variantes.contains(TipoVariante.BOTAO)) {
            botao = registro.register(prefixo + "_button",
                    () -> new ButtonBlock(tipo, 20, props));
            registroItem.registerSimpleBlockItem(prefixo + "_button", botao);
            todosOsBlocos.add(botao);
        }
        Variantes v = new Variantes(escada, laje, muro, porta, alcapao, cerca, portao, pilar, descascado, talhado,
                rachado, pressao, botao, tipoMaterial);
        todosAsVariantes.add(v);
        return v;
    }

    protected DeferredBlock<RotatedPillarBlock> registrarTronco(String nome, BlockBehaviour.Properties props) {
        DeferredBlock<RotatedPillarBlock> tronco = registro.register(nome, () -> new RotatedPillarBlock(props));
        registroItem.registerSimpleBlockItem(nome, tronco);
        todosOsBlocos.add(tronco);
        return tronco;
    }

    protected DeferredBlock<LeavesBlock> registrarFolha(TipoMaterial tipo, String nome,
            BlockBehaviour.Properties props) {
        DeferredBlock<LeavesBlock> folha = registro.register(nome, () -> new LeavesBlock(props
                .mapColor(MapColor.PLANT)
                .strength(0.2F)
                .randomTicks()
                .sound(SoundType.GRASS)
                .noOcclusion()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY)));

        registroItem.registerSimpleBlockItem(nome, folha);
        todosOsBlocos.add(folha);
        return folha;
    }

    public List<Item> devolverItens(boolean temBase) {
        List<Item> itens = new ArrayList<>();
        if (temBase)
            itens.add(base.get().asItem());
        for (DeferredBlock<? extends Block> bloco : todosOsBlocos) {
            itens.add(bloco.get().asItem());
        }
        return itens;
    }

    public List<Variantes> variantes() {
        return todosAsVariantes;
    }

    public DeferredBlock<Block> materialDe(TipoMaterial tipo) {
        return materiais.get(tipo);
    }

    private static boolean always(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return true;
    }

    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return false;
    }
}