package com.lzk.nihiloutspread.blockGens;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
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

    public final Map<TipoMaterialAreia, DeferredBlock<PressurePlateBlock>> pressoes;
    public final Map<TipoMaterialAreia, DeferredBlock<ButtonBlock>> bototes;

    public final Map<TipoMaterialAreia, DeferredBlock<Block>> talhados;
    public final Map<TipoMaterialAreia, DeferredBlock<RotatedPillarBlock>> pilares;

    public DeferredBlock<Block> materialDe(TipoMaterialAreia tipo) {
        return switch (tipo) {
            case BASE -> base;
            case LISO -> liso;
            case CORTADO -> cortado;
        };
    }

    public FamiliaAreia(
            DeferredRegister.Blocks reg,
            DeferredRegister.Items regItem,
            DeferredBlock<Block> blocoBase,
            BlockBehaviour.Properties props,
            Map<TipoMaterialAreia, Set<TipoVariante>> composicao) {

        super(reg, regItem, blocoBase);

        Map<TipoMaterialAreia, DeferredBlock<PressurePlateBlock>> pressoesTemp = new EnumMap<>(TipoMaterialAreia.class);
        Map<TipoMaterialAreia, DeferredBlock<ButtonBlock>> bototesTemp = new EnumMap<>(TipoMaterialAreia.class);

        Map<TipoMaterialAreia, DeferredBlock<Block>> talhadosTemp = new EnumMap<>(TipoMaterialAreia.class);
        Map<TipoMaterialAreia, DeferredBlock<RotatedPillarBlock>> pilaresTemp = new EnumMap<>(TipoMaterialAreia.class);

        Set<TipoVariante> vBase = composicao.getOrDefault(TipoMaterialAreia.BASE, Set.of());
        if (vBase.contains(TipoVariante.PILAR)) {
            pilaresTemp.put(TipoMaterialAreia.BASE, registrarPilar(nomeBase + "_pillar", props));
        }
        this.variantesBase = vBase.isEmpty() ? null : registrarVariantes(nomeBase, base, props, vBase);

        if (vBase.contains(TipoVariante.PLACAPRESSAO)) {
            pressoesTemp.put(TipoMaterialAreia.BASE, registrarPlacaPressao(nomeBase + "_pressure_plate", props,
                    BlockSetType.STONE));
        }

        if (vBase.contains(TipoVariante.BOTAO)) {
            bototesTemp.put(TipoMaterialAreia.BASE, registrarBotao(nomeBase + "_button", props, BlockSetType.STONE));
        }

        if (vBase.contains(TipoVariante.TALHADO)) {
            talhadosTemp.put(TipoMaterialAreia.BASE, registrar("chiseled_" + nomeBase, props));
        }

        if (composicao.containsKey(TipoMaterialAreia.LISO)) {
            Set<TipoVariante> vLiso = composicao.get(TipoMaterialAreia.LISO);
            this.liso = registrar("smooth_" + nomeBase, props);
            if (vLiso.contains(TipoVariante.PILAR)) {
                pilaresTemp.put(TipoMaterialAreia.LISO, registrarPilar("smooth_" + nomeBase + "_pillar", props));
            }
            this.variantesLiso = registrarVariantes("smooth_" + nomeBase, liso, props, vLiso);
            if (vLiso.contains(TipoVariante.TALHADO)) {
                talhadosTemp.put(TipoMaterialAreia.LISO, registrar("chiseled_smooth_" + nomeBase, props));
            }
        } else {
            this.liso = null;
            this.variantesLiso = null;
        }

        if (composicao.containsKey(TipoMaterialAreia.CORTADO)) {
            Set<TipoVariante> vCortado = composicao.get(TipoMaterialAreia.CORTADO);
            this.cortado = registrar("cut_" + nomeBase, props);

            if (vCortado.contains(TipoVariante.PILAR)) {
                pilaresTemp.put(TipoMaterialAreia.CORTADO, registrarPilar("cut_" + nomeBase + "_pillar", props));
            }
            this.variantesCortado = registrarVariantes("cut_" + nomeBase, cortado, props, vCortado);
            if (vCortado.contains(TipoVariante.TALHADO)) {
                talhadosTemp.put(TipoMaterialAreia.CORTADO, registrar("chiseled_cut_" + nomeBase, props));
            }
        } else {
            this.cortado = null;
            this.variantesCortado = null;
        }

        this.pressoes = pressoesTemp;
        this.bototes = bototesTemp;
        this.talhados = talhadosTemp;
        this.pilares = pilaresTemp;
    }
}