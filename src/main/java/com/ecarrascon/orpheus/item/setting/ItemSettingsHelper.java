package com.ecarrascon.orpheus.item.setting;

import net.minecraft.world.item.Item;

public class ItemSettingsHelper {
    public static Item.Properties stackableItem() {
        return new Item.Properties();
    }

    public static Item.Properties noStackableItem() {
        return new Item.Properties().stacksTo(1);
    }


}
