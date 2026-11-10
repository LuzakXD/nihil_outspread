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

    public FamiliaAreia(
            DeferredRegister.Blocks reg,
            DeferredRegister.Items regItem,
            DeferredBlock<Block> blocoBase,
            BlockBehaviour.Properties props,
            Map<TipoMaterial, Set<TipoVariante>> composicao) {

        super(reg, regItem, blocoBase);

        Set<TipoVariante> vBase = composicao.getOrDefault(TipoMaterial.BASE, Set.of());
        if (vBase.contains(TipoVariante.PILAR)) {
            pilares.put(TipoMaterial.BASE, registrarPilar(nomeBase + "_pillar", props));
        }
        this.variantesBase = vBase.isEmpty() ? null : registrarVariantes(nomeBase, base, props, vBase);

        if (vBase.contains(TipoVariante.TALHADO)) {
            talhados.put(TipoMaterial.BASE, registrar("chiseled_" + nomeBase, props));
        }

        if (composicao.containsKey(TipoMaterial.LISO)) {
            Set<TipoVariante> vLiso = composicao.get(TipoMaterial.LISO);
            this.liso = registrarMaterial(TipoMaterial.LISO, "smooth_" + nomeBase, props);
            if (vLiso.contains(TipoVariante.PILAR)) {
                pilares.put(TipoMaterial.LISO, registrarPilar("smooth_" + nomeBase + "_pillar", props));
            }
            this.variantesLiso = registrarVariantes("smooth_" + nomeBase, liso, props, vLiso);
            if (vLiso.contains(TipoVariante.TALHADO)) {
                talhados.put(TipoMaterial.LISO, registrar("chiseled_smooth_" + nomeBase, props));
            }
        } else {
            this.liso = null;
            this.variantesLiso = null;
        }

        if (composicao.containsKey(TipoMaterial.CORTADO)) {
            Set<TipoVariante> vCortado = composicao.get(TipoMaterial.CORTADO);
            this.cortado = registrarMaterial(TipoMaterial.CORTADO, "cut_" + nomeBase, props);

            if (vCortado.contains(TipoVariante.PILAR)) {
                pilares.put(TipoMaterial.CORTADO, registrarPilar("cut_" + nomeBase + "_pillar", props));
            }
            this.variantesCortado = registrarVariantes("cut_" + nomeBase, cortado, props, vCortado);
            if (vCortado.contains(TipoVariante.TALHADO)) {
                talhados.put(TipoMaterial.CORTADO, registrar("chiseled_cut_" + nomeBase, props));
            }
        } else {
            this.cortado = null;
            this.variantesCortado = null;
        }
    }
}