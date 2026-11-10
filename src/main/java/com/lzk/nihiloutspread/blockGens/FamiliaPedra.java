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
            BlockBehaviour.Properties props,
            Boolean sufixoPolido,
            Map<TipoMaterial, Set<TipoVariante>> composicao) {

        super(reg, regItem, blocoBase);

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
        if (vBase.contains(TipoVariante.PILAR)) {
            pilares.put(TipoMaterial.BASE, registrarPilar(nomeBase + "_pillar", props));
        }
        this.variantesBase = vBase.isEmpty() ? null : registrarVariantes(nomeBase, base, props, vBase);

        if (vBase.contains(TipoVariante.PLACAPRESSAO)) {
            pressoes.put(TipoMaterial.BASE,
                    registrarPlacaPressao(nomeBase + "_pressure_plate", props, BlockSetType.STONE));
        }

        if (vBase.contains(TipoVariante.BOTAO)) {
            bototes.put(TipoMaterial.BASE, registrarBotao(nomeBase + "_button", props, BlockSetType.STONE));
        }

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

        if (composicao.containsKey(TipoMaterial.POLIDO)) {
            Set<TipoVariante> vPolido = composicao.get(TipoMaterial.POLIDO);
            this.polido = registrarMaterial(TipoMaterial.POLIDO, "polished_" + nomeBase, props);
            if (vPolido.contains(TipoVariante.PILAR)) {
                pilares.put(TipoMaterial.POLIDO, registrarPilar("polished_" + nomeBase + "_pillar", props));
            }
            this.variantesPolido = registrarVariantes("polished_" + nomeBase, polido, props, vPolido);

            if (vPolido.contains(TipoVariante.PLACAPRESSAO)) {
                pressoes.put(TipoMaterial.POLIDO, registrarPlacaPressao("polished_"
                        + nomeBase + "_pressure_plate", props,
                        BlockSetType.STONE));
            }

            if (vPolido.contains(TipoVariante.BOTAO)) {
                bototes.put(TipoMaterial.POLIDO, registrarBotao(
                        "polished_" + nomeBase + "_button", props, BlockSetType.STONE));
            }
            if (vPolido.contains(TipoVariante.TALHADO)) {
                talhados.put(TipoMaterial.POLIDO, registrar("chiseled_polished_" + nomeBase, props));
            }
        } else {
            this.polido = null;
            this.variantesPolido = null;
        }
        if (sufixoPolido) {
            if (composicao.containsKey(TipoMaterial.TIJOLOS)) {
                Set<TipoVariante> vTijolos = composicao.get(TipoMaterial.TIJOLOS);
                this.tijolos = registrarMaterial(TipoMaterial.TIJOLOS, "polished_" + nomeBase + "_bricks",
                        props);

                if (vTijolos.contains(TipoVariante.RACHADO)) {
                    rachados.put(TipoMaterial.TIJOLOS,
                            registrar("cracked_polished_" + nomeBase + "_bricks", props));
                }

                if (vTijolos.contains(TipoVariante.TALHADO)) {
                    talhados.put(TipoMaterial.TIJOLOS,
                            registrar("chiseled_polished_" + nomeBase + "_bricks", props));
                }
                if (vTijolos.contains(TipoVariante.PILAR)) {
                    pilares.put(TipoMaterial.TIJOLOS,
                            registrarPilar("polished_" + nomeBase + "_brick_pillar", props));
                }
                this.variantesTijolos = registrarVariantes("polished_" + nomeBase + "_brick",
                        tijolos,
                        props,
                        vTijolos);
            } else {
                this.tijolos = null;
                this.variantesTijolos = null;
            }
        } else {

            if (composicao.containsKey(TipoMaterial.TIJOLOS)) {
                Set<TipoVariante> vTijolos = composicao.get(TipoMaterial.TIJOLOS);
                this.tijolos = registrarMaterial(TipoMaterial.TIJOLOS, nomeBase + "_bricks", props);

                if (vTijolos.contains(TipoVariante.RACHADO)) {
                    rachados.put(TipoMaterial.TIJOLOS, registrar("cracked_" + nomeBase + "_bricks", props));
                }

                if (vTijolos.contains(TipoVariante.PILAR)) {
                    pilares.put(TipoMaterial.TIJOLOS, registrarPilar(nomeBase + "_brick_pillar", props));
                }
                this.variantesTijolos = registrarVariantes(nomeBase + "_brick", tijolos, props, vTijolos);
                if (vTijolos.contains(TipoVariante.TALHADO)) {
                    talhados.put(TipoMaterial.TIJOLOS, registrar("chiseled_" + nomeBase + "_bricks", props));
                }
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

                if (vladrilhos.contains(TipoVariante.RACHADO)) {
                    rachados.put(TipoMaterial.LADRILHOS,
                            registrar("cracked_polished_" + nomeBase + "_tiles", props));
                }

                if (vladrilhos.contains(TipoVariante.TALHADO)) {
                    talhados.put(TipoMaterial.LADRILHOS,
                            registrar("chiseled_polished_" + nomeBase + "_tiles", props));
                }
                if (vladrilhos.contains(TipoVariante.PILAR)) {
                    pilares.put(TipoMaterial.LADRILHOS,
                            registrarPilar("polished_" + nomeBase + "_tile_pillar", props));
                }
                this.variantesLadrilhos = registrarVariantes("polished_" + nomeBase + "_tile", ladrilhos,
                        props,
                        vladrilhos);
            } else {
                this.ladrilhos = null;
                this.variantesLadrilhos = null;
            }
        } else {
            if (composicao.containsKey(TipoMaterial.LADRILHOS)) {
                Set<TipoVariante> vLadrilhos = composicao.get(TipoMaterial.LADRILHOS);
                this.ladrilhos = registrarMaterial(TipoMaterial.LADRILHOS, nomeBase + "_tiles", props);

                if (vLadrilhos.contains(TipoVariante.RACHADO)) {
                    rachados.put(TipoMaterial.LADRILHOS, registrar("cracked_" + nomeBase + "_tiles", props));
                }

                if (vLadrilhos.contains(TipoVariante.PILAR)) {
                    pilares.put(TipoMaterial.LADRILHOS, registrarPilar(nomeBase + "_tile_pillar", props));
                }
                this.variantesLadrilhos = registrarVariantes(nomeBase + "_tile", ladrilhos, props, vLadrilhos);
                if (vLadrilhos.contains(TipoVariante.TALHADO)) {
                    talhados.put(TipoMaterial.LADRILHOS, registrar("chiseled_" + nomeBase + "_tiles", props));
                }
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

                if (vplacas.contains(TipoVariante.RACHADO)) {
                    rachados.put(TipoMaterial.PLACAS,
                            registrar("cracked_polished_" + nomeBase + "_plates", props));
                }

                if (vplacas.contains(TipoVariante.TALHADO)) {
                    talhados.put(TipoMaterial.PLACAS,
                            registrar("chiseled_polished_" + nomeBase + "_plates", props));
                }
                if (vplacas.contains(TipoVariante.PILAR)) {
                    pilares.put(TipoMaterial.PLACAS,
                            registrarPilar("polished_" + nomeBase + "_plate_pillar", props));
                }
                this.variantesPlacas = registrarVariantes("polished_" + nomeBase + "_plate", placas,
                        props,
                        vplacas);
            } else {
                this.placas = null;
                this.variantesPlacas = null;
            }
        } else {
            if (composicao.containsKey(TipoMaterial.PLACAS)) {
                Set<TipoVariante> vPlacas = composicao.get(TipoMaterial.PLACAS);
                this.placas = registrarMaterial(TipoMaterial.PLACAS, nomeBase + "_plates", props);

                if (vPlacas.contains(TipoVariante.RACHADO)) {
                    rachados.put(TipoMaterial.PLACAS, registrar("cracked_" + nomeBase + "_plates", props));
                }

                if (vPlacas.contains(TipoVariante.PILAR)) {
                    pilares.put(TipoMaterial.PLACAS, registrarPilar(nomeBase + "_plate_pillar", props));
                }
                this.variantesPlacas = registrarVariantes(nomeBase + "_plate", placas, props, vPlacas);
                if (vPlacas.contains(TipoVariante.TALHADO)) {
                    talhados.put(TipoMaterial.PLACAS, registrar("chiseled_" + nomeBase + "_plate", props));
                }
            } else {
                this.placas = null;
                this.variantesPlacas = null;
            }
        }

    }
}