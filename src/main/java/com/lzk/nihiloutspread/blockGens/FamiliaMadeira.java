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
    public final Variantes variantesTronco;

    public final DeferredBlock<RotatedPillarBlock> madeira;
    public final Variantes variantesMadeira;

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
        this.variantesBase = vBase.isEmpty() ? null
                : registrarVariantes(TipoMaterial.BASE, nomeBase, false, base, props, BlockSetType.OAK, vBase);

        if (composicao.containsKey(TipoMaterial.TRONCO)) {
            Set<TipoVariante> vTronco = composicao.get(TipoMaterial.TRONCO);
            this.tronco = registrarTronco(nomeBase + "_log", props);
            this.variantesTronco = registrarVariantes(TipoMaterial.TRONCO, nomeBase + "_log",
                    false, tronco, props, BlockSetType.OAK, vTronco);
        } else {
            this.tronco = null;
            this.variantesTronco = null;
        }

        if (composicao.containsKey(TipoMaterial.MADEIRA)) {
            Set<TipoVariante> vMadeira = composicao.get(TipoMaterial.MADEIRA);
            this.madeira = registrarTronco(nomeBase + "_wood", props);
            this.variantesMadeira = registrarVariantes(
                    TipoMaterial.MADEIRA, nomeBase + "_wood",
                    false, madeira, props, BlockSetType.OAK, vMadeira);
        } else {
            this.madeira = null;
            this.variantesMadeira = null;
        }

        if (composicao.containsKey(TipoMaterial.FOLHA)) {
            this.folha = registrarMaterial(TipoMaterial.FOLHA, nomeBase + "_leaves", props);
        } else {
            this.folha = null;
        }

        if (composicao.containsKey(TipoMaterial.MOSAICO)) {
            Set<TipoVariante> vMosaico = composicao.get(TipoMaterial.MOSAICO);
            this.mosaico = registrarMaterial(TipoMaterial.MOSAICO, nomeBase + "_mosaic", props);
            this.variantesMosaico = registrarVariantes(
                    TipoMaterial.MOSAICO, nomeBase + "_mosaic",
                    false, mosaico, props, BlockSetType.OAK,
                    vMosaico);
        } else {
            this.mosaico = null;
            this.variantesMosaico = null;
        }
    }
}