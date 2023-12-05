package com.ecarrascon.orpheus.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public class PlayerUtils {
    public static void decrementHeldItemWithAmount(PlayerEntity player, Item item, int amount) {
        if (player.getMainHandStack().isOf(item)) {
            player.getMainHandStack().decrement(amount);
        } else if (player.getOffHandStack().isOf(item)) {
            player.getOffHandStack().decrement(amount);
        }
    }
    public static void decrementHeldItem(PlayerEntity player, Item item) {
        if (player.getMainHandStack().isOf(item)) {
            player.getMainHandStack().decrement(1);
        } else if (player.getOffHandStack().isOf(item)) {
            player.getOffHandStack().decrement(1);
        }
    }
}
