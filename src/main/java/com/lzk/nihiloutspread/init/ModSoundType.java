package com.lzk.nihiloutspread.init;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.common.util.DeferredSoundType;

public class ModSoundType {

    public static final SoundType NYVERIN = new DeferredSoundType(
            1.0f,
            1.0f,
            () -> SoundEvents.WOOD_BREAK,
            () -> SoundEvents.WOOD_STEP,
            () -> SoundEvents.WOOD_PLACE,
            () -> SoundEvents.WOOD_HIT,
            () -> SoundEvents.WOOD_FALL);
}
