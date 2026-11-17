package com.lzk.nihiloutspread.blockGens;

import java.util.Map;
import java.util.Set;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FamiliaMadeira extends FamiliaAbstrata {

    public final DeferredBlock<RotatedPillarBlock> tronco;

    public final DeferredBlock<RotatedPillarBlock> madeira;

    public final DeferredBlock<Block> folha;

    public final Variantes variantesBase;

    public final DeferredBlock<Block> mosaico;
    public final Variantes variantesMosaico;

    public FamiliaMadeira(
            DeferredRegister.Blocks reg,
            DeferredRegister.Items regItem,
            DeferredBlock<Block> blocoBase,
            String sufixo,
            BlockBehaviour.Properties props,
            Map<TipoMaterial, Set<TipoVariante>> composicao) {

        super(reg, regItem, blocoBase, sufixo);

        Set<TipoVariante> vBase = composicao.getOrDefault(TipoMaterial.BASE, Set.of());
        this.variantesBase = vBase.isEmpty() ? null : registrarVariantes(nomeBase, base, props, vBase);

        if (vBase.contains(TipoVariante.PLACAPRESSAO)) {
            pressoes.put(TipoMaterial.BASE,
                    registrarPlacaPressao(nomeBase + "_pressure_plate", props, BlockSetType.OAK));
        }

        if (vBase.contains(TipoVariante.BOTAO)) {
            botoes.put(TipoMaterial.BASE, registrarBotao(nomeBase + "_button", props, BlockSetType.OAK));
        }

        if (composicao.containsKey(TipoMaterial.TRONCO)) {
            Set<TipoVariante> vTronco = composicao.get(TipoMaterial.TRONCO);
            this.tronco = registrarPilar(nomeBase + "_log", props);
            if (vTronco.contains(TipoVariante.DESCASCADO)) {
                descascados.put(TipoMaterial.TRONCO, registrarPilar("stripped_" + nomeBase + "_log", props));
            }
        } else {
            this.tronco = null;
        }

        if (composicao.containsKey(TipoMaterial.MADEIRA)) {
            Set<TipoVariante> vMadeira = composicao.get(TipoMaterial.MADEIRA);
            this.madeira = registrarPilar(nomeBase + "_wood", props);
            if (vMadeira.contains(TipoVariante.DESCASCADO)) {
                descascados.put(TipoMaterial.MADEIRA, registrarPilar("stripped_" + nomeBase + "_wood", props));
            }
        } else {
            this.madeira = null;
        }

        if (composicao.containsKey(TipoMaterial.FOLHA)) {
            this.folha = registrarMaterial(TipoMaterial.FOLHA, nomeBase + "_leaves", props);
        } else {
            this.folha = null;
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