package com.lzk.nihiloutspread.blockGens;

import java.util.Map;
import java.util.Set;

import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FamiliaMadeira extends FamiliaAbstrata {

    public final Variantes variantesBase;

    public final DeferredBlock<Block> mosaico;
    public final Variantes variantesMosaico;

    public FamiliaMadeira(
            DeferredRegister.Blocks reg,
            DeferredRegister.Items regItem,
            DeferredBlock<Block> blocoBase,
            BlockBehaviour.Properties props,
            Map<TipoMaterial, Set<TipoVariante>> composicao) {

        super(reg, regItem, blocoBase);

        Set<TipoVariante> vBase = composicao.getOrDefault(TipoMaterial.BASE, Set.of());
        this.variantesBase = vBase.isEmpty() ? null : registrarVariantes(nomeBase, base, props, vBase);

        if (vBase.contains(TipoVariante.PLACAPRESSAO)) {
            pressoes.put(TipoMaterial.BASE,
                    registrarPlacaPressao(nomeBase + "_pressure_plate", props, BlockSetType.WOOD));
        }

        if (vBase.contains(TipoVariante.BOTAO)) {
            botoes.put(TipoMaterial.BASE, registrarBotao(nomeBase + "_button", props, BlockSetType.WOOD));
        }

        if (composicao.containsKey(TipoMaterial.MOSAICO)) {
            Set<TipoVariante> vMosaico = composicao.get(TipoMaterial.MOSAICO);
            this.mosaico = registrarMaterial(TipoMaterial.MOSAICO, nomeBase + "_mosaic", props);
            this.variantesMosaico = registrarVariantes(nomeBase + "_mosaic", mosaico, props, vMosaico);
        } else {
            this.mosaico = null;
            this.variantesMosaico = null;
        }
    }
}