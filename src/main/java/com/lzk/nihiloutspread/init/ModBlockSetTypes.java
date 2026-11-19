package com.lzk.nihiloutspread.init;

import java.util.HashMap;
import java.util.Map;

import com.lzk.nihiloutspread.NihilOutspread;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModBlockSetTypes {
        private static final Map<BlockSetType, WoodType> WOOD_BY_SET = new HashMap<>();

        public static final BlockSetType NYVERIN = BlockSetType.register(
                        new BlockSetType(
                                        NihilOutspread.MODID + ":nyverin",
                                        true,
                                        true,
                                        true,
                                        BlockSetType.PressurePlateSensitivity.EVERYTHING,
                                        ModSoundType.NYVERIN,
                                        SoundEvents.WOODEN_DOOR_CLOSE,
                                        SoundEvents.WOODEN_DOOR_OPEN,
                                        SoundEvents.WOODEN_TRAPDOOR_CLOSE,
                                        SoundEvents.WOODEN_TRAPDOOR_OPEN,
                                        SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF,
                                        SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON,
                                        SoundEvents.WOODEN_BUTTON_CLICK_OFF,
                                        SoundEvents.WOODEN_BUTTON_CLICK_ON));

        public static final WoodType NYVERIN_WOOD = registrarWOOD(
                        new WoodType(NYVERIN.name() + "_wood", ModBlockSetTypes.NYVERIN));

        private static WoodType registrarWOOD(WoodType woodType) {
                WoodType.register(woodType);
                WOOD_BY_SET.put(woodType.setType(), woodType);
                return woodType;
        }

        public static WoodType getWoodType(BlockSetType setType) {
                WoodType custom = WOOD_BY_SET.get(setType);
                if (custom != null) {
                        return custom;
                }
                return WoodType.values()
                                .filter(wt -> wt.setType() == setType)
                                .findFirst()
                                .orElse(null);
        }
}
