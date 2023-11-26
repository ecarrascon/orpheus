package com.ecarrascon.orpheus.block;

import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.util.PlayerUtils;
import com.ecarrascon.orpheus.util.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MythosBlock extends Block {
    public MythosBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient() && entity instanceof PlayerEntity player) {
            // Get Calliope's Love
            if (player.isHolding(ItemsRegistry.HELLENIC_CODEX.get())
                    && player.isSneaking() && WorldUtils.isSurroundedByBlocksTag(world, pos, BlockTags.FLOWERS, 1)) {
                WorldUtils.summonLightning(player, world);
                PlayerUtils.decrementHeldItem(player, ItemsRegistry.HELLENIC_CODEX.get());
                giveCalliopesLoveItem(player, world);
                world.breakBlock(pos, false);
                world.playSound(null, pos, SoundEvents.ITEM_TRIDENT_RETURN, SoundCategory.BLOCKS, 1f, 1f);

            }

            // Get Orpheus Lyre
            if (world.getRegistryKey().equals(World.NETHER) && player.isHolding(ItemsRegistry.APOLLOS_SON.get())
                    && player.isSneaking() && WorldUtils.isSurroundedByBlocks(world, pos, Blocks.MAGMA_BLOCK, 0)) {
                WorldUtils.summonLightning(player, world);
                PlayerUtils.decrementHeldItem(player, ItemsRegistry.APOLLOS_SON.get());
                giveOrpheusLyreItem(player, world);
                world.breakBlock(pos, false);
                world.playSound(null, pos, SoundEvents.ITEM_TRIDENT_RETURN, SoundCategory.BLOCKS, 1f, 1f);
            }
        }
        super.onSteppedOn(world, pos, state, entity);
    }



    private void giveCalliopesLoveItem(PlayerEntity player, World world) {
        ItemStack calliopesLove = ItemsRegistry.CALLIOPES_LOVE.get().getDefaultStack();
        if (!player.getInventory().insertStack(calliopesLove)) {
            Block.dropStack(world, player.getBlockPos(), calliopesLove);
        }
    }

    private void giveOrpheusLyreItem(PlayerEntity player, World world) {
        ItemStack calliopesLove = ItemsRegistry.ORPHEUS_LYRE.get().getDefaultStack();
        if (!player.getInventory().insertStack(calliopesLove)) {
            Block.dropStack(world, player.getBlockPos(), calliopesLove);
        }
    }

}
