package com.ecarrascon.orpheus.item;

import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.util.PlayerUtils;
import com.ecarrascon.orpheus.util.WorldUtils;
import net.fabricmc.yarn.constants.ParrotVariants;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.CatVariantTags;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class PandorasPithosItem extends Item {

    public PandorasPithosItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        if (!world.isClient && !player.isSpectator()) {
            PlayerUtils.decrementHeldItem(player, ItemsRegistry.PANDORAS_PITHOS.get());
            WorldUtils.summonLightning(player, world);
            player.addStatusEffect(getRandomEffect(world));
            getRandomItem(player, world);
            spawnRandomEntity(player, world);
        }

        return super.use(world, player, hand);
    }

    private StatusEffectInstance getRandomEffect(World world) {
        List<StatusEffect> possibleEffects = Arrays.asList(
                StatusEffects.SPEED,
                StatusEffects.STRENGTH,
                StatusEffects.ABSORPTION,
                StatusEffects.RESISTANCE,
                StatusEffects.REGENERATION,
                StatusEffects.POISON,
                StatusEffects.NAUSEA,
                StatusEffects.BLINDNESS,
                StatusEffects.WITHER,
                StatusEffects.WEAKNESS
        );

        StatusEffect randomEffect = possibleEffects.get(world.getRandom().nextBetween(0, possibleEffects.size() - 1));

        return new StatusEffectInstance(randomEffect, 1800, 3);
    }

    private void getRandomItem(PlayerEntity player, World world) {
        List<ItemStack> possibleItems = Arrays.asList(
                Items.DIAMOND.getDefaultStack(),
                ItemsRegistry.TEARS_OF_HADES.get().getDefaultStack(),
                Items.BONE.getDefaultStack(),
                Items.STICK.getDefaultStack()
        );

        ItemStack randomItem = possibleItems.get(world.getRandom().nextBetween(0, possibleItems.size() - 1));
        int random = world.getRandom().nextBetween(0, 6);
        for (int i = 0; i < random; i++) {
            ItemStack itemCopy = randomItem.copy();
            if (!player.getInventory().insertStack(itemCopy)) {
                Block.dropStack(world, player.getBlockPos(), itemCopy);
            }
        }

    }

    private void spawnRandomEntity(PlayerEntity player, World world) {
        List<EntityType<?>> possibleEntities = Arrays.asList(
                EntityType.VINDICATOR,
                EntityType.PIG
        );

        EntityType<?> randomEntityType = possibleEntities.get(world.getRandom().nextBetween(0, possibleEntities.size() - 1));

        int witherPossiblity = world.getRandom().nextBetween(0, 199);

        if (witherPossiblity == 0) {
            randomEntityType = EntityType.WITHER; // 0.5% chance to spawn the Wither
        }

        int random = world.getRandom().nextBetween(0, 7); // After randomly choosing the entity it will spawn 0 to 6 entities

        for (int i = 0; i < random; i++) {
            Entity randomEntity = randomEntityType.create(world);
            if (randomEntity != null) {
                randomEntity.setPosition(player.getPos());
                world.spawnEntity(randomEntity);
                if (witherPossiblity == 1 && i == 0) {
                    // Hi! If you are reading this, you are such a netrunner.
                    // Second, all this "cat" thing is just an Easter Egg, my cat is called Zeus and yeah, funny!
                    CatEntity zeus = getZeus(player, world);
                    world.spawnEntity(zeus);
                }
            }
        }
    }


    private CatEntity getZeus(PlayerEntity player, World world) {
        CatEntity zeus = new CatEntity(EntityType.CAT, world);
        zeus.setCustomName(Text.of("Zeus"));
        zeus.setHealth(500f);
        zeus.setGlowing(true);
        zeus.setPosition(player.getPos());
        return zeus;
    }

}
