package com.lzk.nihiloutspread.init;

import java.util.function.Supplier;

import com.lzk.nihiloutspread.NihilOutspread;
import com.lzk.nihiloutspread.entity.MurkletEntity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

        public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister
                        .create(BuiltInRegistries.ENTITY_TYPE, NihilOutspread.MODID);

        public static final Supplier<EntityType<MurkletEntity>> MURKLET = ENTITY_TYPE.register("murklet",
                        () -> EntityType.Builder
                                        .of(MurkletEntity::new, MobCategory.CREATURE)
                                        .sized(0.4f, 0.6f)
                                        .build("nihil_outspread:murklet"));

}
