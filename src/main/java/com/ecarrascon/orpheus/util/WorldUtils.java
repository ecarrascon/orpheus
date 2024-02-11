package com.ecarrascon.orpheus.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldUtils {
    public static void summonLightning(PlayerEntity player, World world) {
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightning.setCosmetic(true);
        lightning.setPosition(player.getPos());
        world.spawnEntity(lightning);
    }

    public static boolean isSurroundedByBlocksTag(World world, BlockPos pos, TagKey<Block> tagKey, int heightAdd) {
        int yPos = pos.getY() + heightAdd;

        int startX = pos.getX() - 1;
        int startZ = pos.getZ() - 1;
        int endX = pos.getX() + 1;
        int endZ = pos.getZ() + 1;

        for (int x = startX; x <= endX; x++) {
            for (int z = startZ; z <= endZ; z++) {
                if (pos.getX() == x && pos.getZ() == z) continue;
                BlockState blockState = world.getBlockState(new BlockPos(x, yPos, z));
                if (!blockState.isIn(tagKey)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isSurroundedByBlocks(World world, BlockPos pos, Block blocks, int heightAdd) {
        int yPos = pos.getY() + heightAdd;

        int startX = pos.getX() - 1;
        int startZ = pos.getZ() - 1;
        int endX = pos.getX() + 1;
        int endZ = pos.getZ() + 1;

        for (int x = startX; x <= endX; x++) {
            for (int z = startZ; z <= endZ; z++) {
                if (pos.getX() == x && pos.getZ() == z) continue;
                BlockState blockState = world.getBlockState(new BlockPos(x, yPos, z));
                if (!blockState.isOf(blocks)) {
                    return false;
                }
            }
        }
        return true;
    }
}
