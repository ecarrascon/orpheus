package com.ecarrascon.orpheus.block;

import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
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
            if (player.getMainHandStack().isItemEqual(ItemsRegistry.HELLENIC_CODEX.get().getDefaultStack())
                    && player.isSneaking() && isSurroundedByFlowers(world, pos)) {
                summonLightning(player, world);
                player.getMainHandStack().decrement(1);
                giveCalliopesLoveItem(player, world);
                world.breakBlock(pos, false);
                world.playSound(null, pos, SoundEvents.ITEM_TRIDENT_RETURN, SoundCategory.BLOCKS, 1f, 1f);
            }

            // Get Orpheus Lyre
            if (world.getRegistryKey().equals(World.NETHER) && player.getMainHandStack().isItemEqual(ItemsRegistry.APOLLOS_SON.get().getDefaultStack())
                    && player.isSneaking() && isSurroundedByMagma(world, pos)) {
                summonLightning(player, world);
                player.getMainHandStack().decrement(1);
                giveOrpheusLyreItem(player, world);
                world.breakBlock(pos, false);
                world.playSound(null, pos, SoundEvents.ITEM_TRIDENT_RETURN, SoundCategory.BLOCKS, 1f, 1f);
            }
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    private void summonLightning(PlayerEntity player, World world) {
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightning.setCosmetic(true);
        lightning.setPosition(player.getPos());
        world.spawnEntity(lightning);
    }

    private boolean isSurroundedByFlowers(World world, BlockPos pos) {
        int yPos = pos.getY() + 1;

        int startX = pos.getX() - 1;
        int startZ = pos.getZ() - 1;
        int endX = pos.getX() + 1;
        int endZ = pos.getZ() + 1;

        for (int x = startX; x <= endX; x++) {
            for (int z = startZ; z <= endZ; z++) {
                if (pos.getX() == x && pos.getZ() == z) continue;
                BlockState blockState = world.getBlockState(new BlockPos(x, yPos, z));
                if (!blockState.isIn(BlockTags.FLOWERS)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSurroundedByMagma(World world, BlockPos pos) {
        int yPos = pos.getY();

        int startX = pos.getX() - 1;
        int startZ = pos.getZ() - 1;
        int endX = pos.getX() + 1;
        int endZ = pos.getZ() + 1;

        for (int x = startX; x <= endX; x++) {
            for (int z = startZ; z <= endZ; z++) {
                if (pos.getX() == x && pos.getZ() == z) continue;
                BlockState blockState = world.getBlockState(new BlockPos(x, yPos, z));
                if (!blockState.equals(Blocks.MAGMA_BLOCK.getDefaultState())) {
                    return false;
                }
            }
        }
        return true;
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
