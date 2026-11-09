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

public class FamiliaPedra extends FamiliaAbstrata {

    public final DeferredBlock<Block> liso;
    public final Variantes variantesLiso;

    public final Variantes variantesBase;

    public final DeferredBlock<Block> polido;
    public final Variantes variantesPolido;

    public final DeferredBlock<Block> tijolos;
    public final Variantes variantesTijolos;

    public final DeferredBlock<Block> tijolosPolido;
    public final Variantes variantesTijolosPolido;

    public final Map<TipoMaterialPedra, DeferredBlock<PressurePlateBlock>> pressoes;
    public final Map<TipoMaterialPedra, DeferredBlock<ButtonBlock>> bototes;

    public final Map<TipoMaterialPedra, DeferredBlock<Block>> rachados;
    public final Map<TipoMaterialPedra, DeferredBlock<Block>> talhados;
    public final Map<TipoMaterialPedra, DeferredBlock<RotatedPillarBlock>> pilares;

    public DeferredBlock<Block> materialDe(TipoMaterialPedra tipo) {
        return switch (tipo) {
            case BASE -> base;
            case LISO -> liso;
            case POLIDO -> polido;
            case TIJOLOS -> tijolos;
            case TIJOLOS_POLIDO -> tijolosPolido;
            case LADRILHOS -> null;
            case PLACAS -> null;
        };
    }

    public FamiliaPedra(
            DeferredRegister.Blocks reg,
            DeferredRegister.Items regItem,
            DeferredBlock<Block> blocoBase,
            BlockBehaviour.Properties props,
            Map<TipoMaterialPedra, Set<TipoVariante>> composicao) {

        super(reg, regItem, blocoBase);

        if (composicao.containsKey(TipoMaterialPedra.TIJOLOS_POLIDO)
                && !composicao.containsKey(TipoMaterialPedra.POLIDO)) {
            throw new IllegalArgumentException("TIJOLOS_POLIDO requires POLIDO");
        }
        if (composicao.containsKey(TipoMaterialPedra.TIJOLOS)
                && composicao.containsKey(TipoMaterialPedra.TIJOLOS_POLIDO)) {
            throw new IllegalArgumentException("TIJOLOS and TIJOLOS_POLIDO are mutually exclusive");
        }

        Map<TipoMaterialPedra, DeferredBlock<PressurePlateBlock>> pressoesTemp = new EnumMap<>(TipoMaterialPedra.class);
        Map<TipoMaterialPedra, DeferredBlock<ButtonBlock>> bototesTemp = new EnumMap<>(TipoMaterialPedra.class);

        Map<TipoMaterialPedra, DeferredBlock<Block>> rachadoTemp = new EnumMap<>(TipoMaterialPedra.class);
        Map<TipoMaterialPedra, DeferredBlock<Block>> talhadosTemp = new EnumMap<>(TipoMaterialPedra.class);
        Map<TipoMaterialPedra, DeferredBlock<RotatedPillarBlock>> pilaresTemp = new EnumMap<>(TipoMaterialPedra.class);

        Set<TipoVariante> vBase = composicao.getOrDefault(TipoMaterialPedra.BASE, Set.of());
        if (vBase.contains(TipoVariante.PILAR)) {
            pilaresTemp.put(TipoMaterialPedra.BASE, registrarPilar(nomeBase + "_pillar", props));
        }
        this.variantesBase = vBase.isEmpty() ? null : registrarVariantes(nomeBase, base, props, vBase);

        if (vBase.contains(TipoVariante.PLACAPRESSAO)) {
            pressoesTemp.put(TipoMaterialPedra.BASE, registrarPlacaPressao(nomeBase + "_pressure_plate", props,
                    BlockSetType.STONE));
        }

        if (vBase.contains(TipoVariante.BOTAO)) {
            bototesTemp.put(TipoMaterialPedra.BASE, registrarBotao(nomeBase + "_button", props, BlockSetType.STONE));
        }

        if (vBase.contains(TipoVariante.TALHADO)) {
            talhadosTemp.put(TipoMaterialPedra.BASE, registrar("chiseled_" + nomeBase, props));
        }

        if (composicao.containsKey(TipoMaterialPedra.LISO)) {
            Set<TipoVariante> vLiso = composicao.get(TipoMaterialPedra.LISO);
            this.liso = registrar("smooth_" + nomeBase, props);
            if (vLiso.contains(TipoVariante.PILAR)) {
                pilaresTemp.put(TipoMaterialPedra.LISO, registrarPilar("smooth_" + nomeBase + "_pillar", props));
            }
            this.variantesLiso = registrarVariantes("smooth_" + nomeBase, liso, props, vLiso);
            if (vLiso.contains(TipoVariante.TALHADO)) {
                talhadosTemp.put(TipoMaterialPedra.LISO, registrar("chiseled_smooth_" + nomeBase, props));
            }
        } else {
            this.liso = null;
            this.variantesLiso = null;
        }

        if (composicao.containsKey(TipoMaterialPedra.POLIDO)) {
            Set<TipoVariante> vPolido = composicao.get(TipoMaterialPedra.POLIDO);
            this.polido = registrar("polished_" + nomeBase, props);
            if (vPolido.contains(TipoVariante.PILAR)) {
                pilaresTemp.put(TipoMaterialPedra.POLIDO, registrarPilar("polished_" + nomeBase + "_pillar", props));
            }
            this.variantesPolido = registrarVariantes("polished_" + nomeBase, polido, props, vPolido);

            if (vPolido.contains(TipoVariante.PLACAPRESSAO)) {
                pressoesTemp.put(TipoMaterialPedra.POLIDO, registrarPlacaPressao("polished_"
                        + nomeBase + "_pressure_plate", props,
                        BlockSetType.STONE));
            }

            if (vPolido.contains(TipoVariante.BOTAO)) {
                bototesTemp.put(TipoMaterialPedra.POLIDO, registrarBotao(
                        "polished_" + nomeBase + "_button", props, BlockSetType.STONE));
            }
            if (vPolido.contains(TipoVariante.TALHADO)) {
                talhadosTemp.put(TipoMaterialPedra.POLIDO, registrar("chiseled_polished_" + nomeBase, props));
            }
        } else {
            this.polido = null;
            this.variantesPolido = null;
        }

        if (composicao.containsKey(TipoMaterialPedra.TIJOLOS)) {
            Set<TipoVariante> vTijolos = composicao.get(TipoMaterialPedra.TIJOLOS);
            this.tijolos = registrar(nomeBase + "_bricks", props);

            if (vTijolos.contains(TipoVariante.RACHADO)) {
                rachadoTemp.put(TipoMaterialPedra.TIJOLOS, registrar("cracked_" + nomeBase + "_bricks", props));
            }

            if (vTijolos.contains(TipoVariante.PILAR)) {
                pilaresTemp.put(TipoMaterialPedra.TIJOLOS, registrarPilar(nomeBase + "_brick_pillar", props));
            }
            this.variantesTijolos = registrarVariantes(nomeBase + "_brick", tijolos, props, vTijolos);
            if (vTijolos.contains(TipoVariante.TALHADO)) {
                talhadosTemp.put(TipoMaterialPedra.TIJOLOS, registrar("chiseled_" + nomeBase + "_bricks", props));
            }
        } else {
            this.tijolos = null;
            this.variantesTijolos = null;
        }

        if (composicao.containsKey(TipoMaterialPedra.TIJOLOS_POLIDO)) {
            Set<TipoVariante> vTijolosPolido = composicao.get(TipoMaterialPedra.TIJOLOS_POLIDO);
            this.tijolosPolido = registrar("polished_" + nomeBase + "_bricks", props);

            if (vTijolosPolido.contains(TipoVariante.RACHADO)) {
                rachadoTemp.put(TipoMaterialPedra.TIJOLOS_POLIDO,
                        registrar("cracked_polished_" + nomeBase + "_bricks", props));
            }

            if (vTijolosPolido.contains(TipoVariante.TALHADO)) {
                talhadosTemp.put(TipoMaterialPedra.TIJOLOS_POLIDO,
                        registrar("chiseled_polished_" + nomeBase + "_bricks", props));
            }
            if (vTijolosPolido.contains(TipoVariante.PILAR)) {
                pilaresTemp.put(TipoMaterialPedra.TIJOLOS_POLIDO,
                        registrarPilar("polished_" + nomeBase + "_brick_pillar", props));
            }
            this.variantesTijolosPolido = registrarVariantes("polished_" + nomeBase + "_brick", tijolosPolido, props,
                    vTijolosPolido);
        } else {
            this.tijolosPolido = null;
            this.variantesTijolosPolido = null;
        }
        this.pressoes = pressoesTemp;
        this.bototes = bototesTemp;
        this.rachados = rachadoTemp;
        this.talhados = talhadosTemp;
        this.pilares = pilaresTemp;
    }
}