package com.lzk.nihiloutspread.init;

import com.lzk.nihiloutspread.NihilOutspread;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NihilOutspread.MODID);

    // Items
    public static final DeferredItem<Item> EXAMPLE_ITEM;

    static {

        EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties()
                .food(new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(2f).build()));
    }

}
