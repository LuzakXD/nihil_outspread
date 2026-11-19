package com.lzk.nihiloutspread.blockGens;

import java.util.Map;
import java.util.Set;

import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FamiliaPedra extends FamiliaAbstrata {

    public final DeferredBlock<Block> liso;
    public final Variantes variantesLiso;

    public final Variantes variantesBase;

    public final DeferredBlock<Block> polido;
    public final Variantes variantesPolido;

    public final DeferredBlock<Block> tijolos;
    public final Variantes variantesTijolos;

    public final DeferredBlock<Block> ladrilhos;
    public final Variantes variantesLadrilhos;

    public final DeferredBlock<Block> placas;
    public final Variantes variantesPlacas;

    public final boolean sufixoPolido;

    public FamiliaPedra(
            DeferredRegister.Blocks reg,
            DeferredRegister.Items regItem,
            DeferredBlock<Block> blocoBase,
            String sufixo,
            BlockBehaviour.Properties props,
            Boolean sufixoPolido,
            Map<TipoMaterial, Set<TipoVariante>> composicao) {

        super(reg, regItem, blocoBase, sufixo, TipoFamilia.PEDRA);

        if (composicao.containsKey(TipoMaterial.LADRILHOS)
                && !composicao.containsKey(TipoMaterial.TIJOLOS)) {
            throw new IllegalArgumentException("LADRILHOS requires TIJOLOS");
        }

        if (composicao.containsKey(TipoMaterial.PLACAS)
                && !composicao.containsKey(TipoMaterial.TIJOLOS)) {
            throw new IllegalArgumentException("PLACAS requires TIJOLOS");
        }

        if (composicao.containsKey(TipoMaterial.PLACAS)
                && composicao.containsKey(TipoMaterial.LADRILHOS)) {
            throw new IllegalArgumentException("PLACAS and LADRILHOS are mutually exclusive");
        }

        if (sufixoPolido && !composicao.containsKey(TipoMaterial.POLIDO)) {
            throw new IllegalArgumentException("sufixoPolido is true but POLIDO is not in composicao");
        }

        this.sufixoPolido = sufixoPolido;

        Set<TipoVariante> vBase = composicao.getOrDefault(TipoMaterial.BASE, Set.of());
        this.variantesBase = vBase.isEmpty() ? null
                : registrarVariantes(TipoMaterial.BASE, nomeBase, false, base, props,
                        BlockSetType.STONE, vBase);

        if (composicao.containsKey(TipoMaterial.LISO)) {
            Set<TipoVariante> vLiso = composicao.get(TipoMaterial.LISO);
            this.liso = registrarMaterial(TipoMaterial.LISO, "smooth_" + nomeBase, props);
            this.variantesLiso = registrarVariantes(TipoMaterial.LISO, "smooth_" + nomeBase,
                    false, liso, props, BlockSetType.STONE, vLiso);
        } else {
            this.liso = null;
            this.variantesLiso = null;
        }

        if (composicao.containsKey(TipoMaterial.POLIDO)) {
            Set<TipoVariante> vPolido = composicao.get(TipoMaterial.POLIDO);
            this.polido = registrarMaterial(TipoMaterial.POLIDO, "polished_" + nomeBase, props);
            this.variantesPolido = registrarVariantes(TipoMaterial.POLIDO, "polished_" + nomeBase,
                    false, polido, props, BlockSetType.STONE,
                    vPolido);
        } else {
            this.polido = null;
            this.variantesPolido = null;
        }

        if (sufixoPolido) {
            if (composicao.containsKey(TipoMaterial.TIJOLOS)) {
                Set<TipoVariante> vTijolos = composicao.get(TipoMaterial.TIJOLOS);
                this.tijolos = registrarMaterial(TipoMaterial.TIJOLOS, "polished_" + nomeBase + "_bricks", props);
                this.variantesTijolos = registrarVariantes(TipoMaterial.TIJOLOS, "polished_" + nomeBase + "_brick",
                        true, tijolos, props,
                        BlockSetType.STONE, vTijolos);
            } else {
                this.tijolos = null;
                this.variantesTijolos = null;
            }
        } else {

            if (composicao.containsKey(TipoMaterial.TIJOLOS)) {
                Set<TipoVariante> vTijolos = composicao.get(TipoMaterial.TIJOLOS);
                this.tijolos = registrarMaterial(TipoMaterial.TIJOLOS, nomeBase + "_bricks", props);
                this.variantesTijolos = registrarVariantes(TipoMaterial.TIJOLOS, nomeBase + "_brick",
                        true, tijolos, props,
                        BlockSetType.STONE, vTijolos);
            } else {
                this.tijolos = null;
                this.variantesTijolos = null;
            }
        }

        if (sufixoPolido) {
            if (composicao.containsKey(TipoMaterial.LADRILHOS)) {
                Set<TipoVariante> vladrilhos = composicao.get(TipoMaterial.LADRILHOS);
                this.ladrilhos = registrarMaterial(TipoMaterial.LADRILHOS,
                        "polished_" + nomeBase + "_tiles",
                        props);
                this.variantesLadrilhos = registrarVariantes(TipoMaterial.LADRILHOS, "polished_" + nomeBase + "_tile",
                        true, ladrilhos,
                        props,
                        BlockSetType.STONE,
                        vladrilhos);
            } else {
                this.ladrilhos = null;
                this.variantesLadrilhos = null;
            }
        } else {
            if (composicao.containsKey(TipoMaterial.LADRILHOS)) {
                Set<TipoVariante> vLadrilhos = composicao.get(TipoMaterial.LADRILHOS);
                this.ladrilhos = registrarMaterial(TipoMaterial.LADRILHOS, nomeBase + "_tiles", props);
                this.variantesLadrilhos = registrarVariantes(TipoMaterial.LADRILHOS, nomeBase + "_tile",
                        true, ladrilhos, props,
                        BlockSetType.STONE, vLadrilhos);
            } else {
                this.ladrilhos = null;
                this.variantesLadrilhos = null;
            }
        }

        if (sufixoPolido) {
            if (composicao.containsKey(TipoMaterial.PLACAS)) {
                Set<TipoVariante> vplacas = composicao.get(TipoMaterial.PLACAS);
                this.placas = registrarMaterial(TipoMaterial.PLACAS, "polished_" + nomeBase + "_plates",
                        props);
                this.variantesPlacas = registrarVariantes(TipoMaterial.PLACAS, "polished_" + nomeBase + "_plate",
                        true, placas,
                        props,
                        BlockSetType.STONE,
                        vplacas);
            } else {
                this.placas = null;
                this.variantesPlacas = null;
            }
        } else {
            if (composicao.containsKey(TipoMaterial.PLACAS)) {
                Set<TipoVariante> vPlacas = composicao.get(TipoMaterial.PLACAS);
                this.placas = registrarMaterial(TipoMaterial.PLACAS, nomeBase + "_plates", props);
                this.variantesPlacas = registrarVariantes(TipoMaterial.PLACAS, nomeBase + "_plate",
                        true, placas, props,
                        BlockSetType.STONE, vPlacas);
            } else {
                this.placas = null;
                this.variantesPlacas = null;
            }
        }

    }
}