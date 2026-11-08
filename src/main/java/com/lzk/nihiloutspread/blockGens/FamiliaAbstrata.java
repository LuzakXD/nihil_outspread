package com.lzk.nihiloutspread.blockGens;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public abstract class FamiliaAbstrata {

    public final DeferredBlock<Block> base;
    public final String nomeCompleto;
    public final String nomeBase;

    protected final DeferredRegister.Blocks registro;
    protected final DeferredRegister.Items registroItem;

    protected final List<DeferredBlock<? extends Block>> todosOsBlocos = new ArrayList<>();

    protected FamiliaAbstrata(DeferredRegister.Blocks reg, DeferredRegister.Items regItem,
            DeferredBlock<Block> blocoBase) {

        this.registro = reg;
        this.registroItem = regItem;
        this.base = blocoBase;
        this.nomeCompleto = blocoBase.getId().getPath();

        String limpo = nomeCompleto;
        if (limpo.endsWith("_block"))
            limpo = limpo.substring(0, limpo.length() - "_block".length());
        this.nomeBase = limpo;
    }

    protected DeferredBlock<Block> registrar(String nome, BlockBehaviour.Properties props) {
        DeferredBlock<Block> bloco = registro.register(nome, () -> new Block(props));
        registroItem.registerSimpleBlockItem(nome, bloco);
        todosOsBlocos.add(bloco);
        return bloco;
    }

    protected Variantes registrarVariantes(
            String prefixo,
            DeferredBlock<Block> blocoBaseDaVariante,
            BlockBehaviour.Properties props,
            Set<TipoVariante> variantes) {
        DeferredBlock<StairBlock> escada = null;
        DeferredBlock<SlabBlock> laje = null;
        DeferredBlock<WallBlock> muro = null;

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

        return new Variantes(escada, laje, muro, null, null, null, null, null);
    }

    protected DeferredBlock<RotatedPillarBlock> registrarPilar(String nome, BlockBehaviour.Properties props) {
        DeferredBlock<RotatedPillarBlock> pilar = registro.register(nome, () -> new RotatedPillarBlock(props));
        registroItem.registerSimpleBlockItem(nome, pilar);
        todosOsBlocos.add(pilar);
        return pilar;
    }

    protected DeferredBlock<PressurePlateBlock> registrarPlacaPressao(String nome, BlockBehaviour.Properties props,
            BlockSetType tipo) {
        DeferredBlock<PressurePlateBlock> pressao = registro.register(nome, () -> new PressurePlateBlock(
                tipo, props));
        registroItem.registerSimpleBlockItem(nome, pressao);
        todosOsBlocos.add(pressao);
        return pressao;
    }

    protected DeferredBlock<ButtonBlock> registrarBotao(String nome, BlockBehaviour.Properties props,
            BlockSetType tipo) {
        DeferredBlock<ButtonBlock> botao = registro.register(nome, () -> new ButtonBlock(
                tipo, 20, props));
        registroItem.registerSimpleBlockItem(nome, botao);
        todosOsBlocos.add(botao);
        return botao;
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
}