package com.ecarrascon.orpheus.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BrotoiPalladiumItem extends Item {
    private final int EFFECT_DURATION = 400; // Initial duration in ticks
    private int timer = EFFECT_DURATION;

    public BrotoiPalladiumItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (timer == EFFECT_DURATION) {
            if (!pLevel.isClientSide() && pEntity instanceof Player player) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, EFFECT_DURATION, 3));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, EFFECT_DURATION, 5));
                timer = 0;
            }
        } else if(!pLevel.isClientSide()) {
            timer++;
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }


}
