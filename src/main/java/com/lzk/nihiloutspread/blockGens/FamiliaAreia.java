package com.lzk.nihiloutspread.blockGens;

import java.util.Map;
import java.util.Set;

import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FamiliaAreia extends FamiliaAbstrata {

    public final DeferredBlock<Block> cortado;
    public final Variantes variantesCortado;

    public final Variantes variantesBase;

    public final DeferredBlock<Block> liso;
    public final Variantes variantesLiso;

    public final DeferredBlock<Block> pedregoso;
    public final Variantes variantesPedregoso;

    public FamiliaAreia(
            DeferredRegister.Blocks reg,
            DeferredRegister.Items regItem,
            DeferredBlock<Block> blocoBase,
            String sufixo,
            BlockBehaviour.Properties props,
            Map<TipoMaterial, Set<TipoVariante>> composicao) {

        super(reg, regItem, blocoBase, sufixo, TipoFamilia.AREIA);

        Set<TipoVariante> vBase = composicao.getOrDefault(TipoMaterial.BASE, Set.of());
        this.variantesBase = vBase.isEmpty() ? null
                : registrarVariantes(
                        TipoMaterial.BASE, nomeBase,
                        false, base, props,
                        BlockSetType.STONE, vBase);

        if (composicao.containsKey(TipoMaterial.PEDREGOSO)) {
            Set<TipoVariante> vPedregoso = composicao.get(TipoMaterial.PEDREGOSO);
            this.pedregoso = registrarMaterial(TipoMaterial.PEDREGOSO, "cobbled_" + nomeBase, props);
            this.variantesPedregoso = registrarVariantes(TipoMaterial.PEDREGOSO, "cobbled_" + nomeBase,
                    false, pedregoso, props, BlockSetType.STONE,
                    vPedregoso);
        } else {
            this.pedregoso = null;
            this.variantesPedregoso = null;
        }

        if (composicao.containsKey(TipoMaterial.LISO)) {
            Set<TipoVariante> vLiso = composicao.get(TipoMaterial.LISO);
            this.liso = registrarMaterial(TipoMaterial.LISO, "smooth_" + nomeBase, props);
            this.variantesLiso = registrarVariantes(TipoMaterial.LISO, "smooth_" + nomeBase,
                    false, liso, props, BlockSetType.STONE, vLiso);
        } else {
            this.liso = null;
            this.variantesLiso = null;
        }

        if (composicao.containsKey(TipoMaterial.CORTADO)) {
            Set<TipoVariante> vCortado = composicao.get(TipoMaterial.CORTADO);
            this.cortado = registrarMaterial(TipoMaterial.CORTADO, "cut_" + nomeBase, props);
            this.variantesCortado = registrarVariantes(TipoMaterial.CORTADO, "cut_" + nomeBase,
                    false, cortado, props, BlockSetType.STONE, vCortado);
        } else {
            this.cortado = null;
            this.variantesCortado = null;
        }
    }
}