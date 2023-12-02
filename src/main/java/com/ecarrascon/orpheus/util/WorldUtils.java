package com.ecarrascon.orpheus.util;


import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class WorldUtils {
    public static void summonLightning(Level pLevel, Player pPlayer) {
        LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, pLevel);
        lightning.setVisualOnly(true);
        lightning.setPos(pPlayer.position());
        pLevel.addFreshEntity(lightning);

    }

    public static boolean isSurroundedByBlocksTag(Level world, BlockPos pos, TagKey<Block> tagKey, int heightAdd) {
        int yPos = pos.getY() + heightAdd;

        int startX = pos.getX() - 1;
        int startZ = pos.getZ() - 1;
        int endX = pos.getX() + 1;
        int endZ = pos.getZ() + 1;

        for (int x = startX; x <= endX; x++) {
            for (int z = startZ; z <= endZ; z++) {
                if (pos.getX() == x && pos.getZ() == z) continue;
                BlockState blockState = world.getBlockState(new BlockPos(x, yPos, z));
                if (!blockState.is(tagKey)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isSurroundedByBlocks(Level world, BlockPos pos, Block blocks, int heightAdd) {
        int yPos = pos.getY() + heightAdd;

        int startX = pos.getX() - 1;
        int startZ = pos.getZ() - 1;
        int endX = pos.getX() + 1;
        int endZ = pos.getZ() + 1;

        for (int x = startX; x <= endX; x++) {
            for (int z = startZ; z <= endZ; z++) {
                if (pos.getX() == x && pos.getZ() == z) continue;
                BlockState blockState = world.getBlockState(new BlockPos(x, yPos, z));
                if (!blockState.is(blocks)) {
                    return false;
                }
            }
        }
        return true;
    }
}
