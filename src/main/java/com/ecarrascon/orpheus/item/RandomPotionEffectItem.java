package com.ecarrascon.orpheus.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class RandomPotionEffectItem extends HoneyBottleItem {
    public RandomPotionEffectItem(Settings settings) {
        super(settings);
    }


    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient) {
            user.addStatusEffect(getRandomEffect(world));
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_AMETHYST_BLOCK_HIT,
                    SoundCategory.PLAYERS, 1.0f, 1.0f);
            if (!((PlayerEntity) user).getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }
        return super.finishUsing(stack, world, user);
    }


    private StatusEffectInstance getRandomEffect(World world) {
        List<StatusEffect> possibleEffects = Arrays.asList(
                StatusEffects.SPEED,
                StatusEffects.STRENGTH,
                StatusEffects.ABSORPTION,
                StatusEffects.RESISTANCE,
                StatusEffects.REGENERATION
                );

        StatusEffect randomEffect = possibleEffects.get(world.getRandom().nextBetween(0, possibleEffects.size() - 1));

        return new StatusEffectInstance(randomEffect, 1800, 3);
    }
}
