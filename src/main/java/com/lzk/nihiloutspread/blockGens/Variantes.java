package com.lzk.nihiloutspread.blockGens;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public record Variantes(
        DeferredBlock<StairBlock> escada,
        DeferredBlock<SlabBlock> laje,
        DeferredBlock<WallBlock> muro,
        DeferredBlock<DoorBlock> porta,
        DeferredBlock<FenceBlock> cerca,
        DeferredBlock<FenceGateBlock> portao,
        DeferredBlock<PressurePlateBlock> placaDePressao,
        DeferredBlock<ButtonBlock> botao) {
}
