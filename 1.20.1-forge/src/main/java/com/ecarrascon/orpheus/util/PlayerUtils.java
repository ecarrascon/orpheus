package com.ecarrascon.orpheus.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class PlayerUtils {
    public static void decrementHeldItemWithAmount(Player player, Item item, int amount) {
        if (player.getMainHandItem().is(item)) {
            player.getMainHandItem().shrink(amount);
        } else if (player.getOffhandItem().is(item)) {
            player.getOffhandItem().shrink(amount);
        }
    }
    public static void decrementHeldItem(Player player, Item item) {
        if (player.getMainHandItem().is(item)) {
            player.getMainHandItem().shrink(1);
        } else if (player.getOffhandItem().is(item)) {
            player.getOffhandItem().shrink(1);
        }
    }

}
