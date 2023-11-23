package com.ecarrascon.orpheus.block;

import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

import java.util.function.Predicate;

public class MyhtosBlock extends Block {
    public MyhtosBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pLevel.isClientSide() && pEntity instanceof Player player) {
            // Get Calliope's Love
            if (player.isHolding(ItemsRegistry.HELLENIC_CODEX.get())
                    && player.isShiftKeyDown() && isSurroundedByFlowers(pLevel, pPos)) {
                summonLightning(pLevel, player);
                player.getMainHandItem().shrink(1);
                giveCalliopesLoveItem(player, pLevel);
                pLevel.destroyBlock(pPos, false);
                pLevel.playSound(null, pPos, SoundEvents.TRIDENT_RETURN, SoundSource.BLOCKS, 1f, 1f);

            }

            // Get Orpheus Lyre
            if (pLevel.dimension().equals(Level.NETHER) && player.isHolding(ItemsRegistry.APOLLOS_SON.get())
                    && player.isShiftKeyDown() && isSurroundedByMagma(pLevel, pPos)) {
                summonLightning(pLevel, player);
                player.getMainHandItem().shrink(1);
                giveOrpheusLyreItem(player, pLevel);
                pLevel.destroyBlock(pPos, false);
                pLevel.playSound(null, pPos, SoundEvents.TRIDENT_RETURN, SoundSource.BLOCKS, 1f, 1f);
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    private void summonLightning(Level pLevel, Player pPlayer) {
        LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, pLevel);
        lightning.setVisualOnly(true);
        lightning.setPos(pPlayer.position());
        pLevel.addFreshEntity(lightning);

    }

    private boolean isSurroundedByFlowers(Level world, BlockPos pos) {
        int yPos = pos.getY() + 1;

        int startX = pos.getX() - 1;
        int startZ = pos.getZ() - 1;
        int endX = pos.getX() + 1;
        int endZ = pos.getZ() + 1;


        for (int x = startX; x <= endX; x++) {
            for (int z = startZ; z <= endZ; z++) {
                if (pos.getX() == x && pos.getZ() == z) continue;
                BlockState blockState = world.getBlockState(new BlockPos(x, yPos, z));
                if (!blockState.is(BlockTags.SMALL_FLOWERS) || !blockState.is(BlockTags.TALL_FLOWERS) || !blockState.is(BlockTags.FLOWERS)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSurroundedByMagma(Level world, BlockPos pos) {
        int yPos = pos.getY();

        int startX = pos.getX() - 1;
        int startZ = pos.getZ() - 1;
        int endX = pos.getX() + 1;
        int endZ = pos.getZ() + 1;

        for (int x = startX; x <= endX; x++) {
            for (int z = startZ; z <= endZ; z++) {
                if (pos.getX() == x && pos.getZ() == z) continue;
                BlockState blockState = world.getBlockState(new BlockPos(x, yPos, z));
                if (!blockState.is(Blocks.MAGMA_BLOCK)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void giveCalliopesLoveItem(Player player, Level world) {
        ItemStack calliopesLove = ItemsRegistry.CALLIOPES_LOVE.get().getDefaultInstance();
        if (!player.getInventory().add(calliopesLove)) {
            Block.dropResources(player.getFeetBlockState(), world, player.blockPosition(), null, null, calliopesLove);
        }
    }

    private void giveOrpheusLyreItem(Player player, Level world) {
        ItemStack orpheusLyre = ItemsRegistry.ORPHEUS_LYRE.get().getDefaultInstance();
        if (!player.getInventory().add(orpheusLyre)) {
            Block.dropResources(player.getFeetBlockState(), world, player.blockPosition(), null, null, orpheusLyre);
        }
    }

}
