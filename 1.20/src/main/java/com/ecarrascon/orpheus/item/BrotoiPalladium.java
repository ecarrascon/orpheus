package com.ecarrascon.orpheus.item;

import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BrotoiPalladium extends Item {

    private final int EFFECT_DURATION = 400; // Initial duration in ticks
    private int timer = EFFECT_DURATION;

    public BrotoiPalladium(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (timer == EFFECT_DURATION) {
            if (!world.isClient() && entity instanceof PlayerEntity player) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, EFFECT_DURATION, 3));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, EFFECT_DURATION, 5));
                timer = 0;
            }
        } else if(!world.isClient()) {
            timer++;
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
