package com.ecarrascon.orpheus.block;

import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.util.PlayerUtils;
import com.ecarrascon.orpheus.util.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MythosBlock extends Block {

    public static final EnumProperty<MythosStateEnum> DIMENSION = EnumProperty.of("dimension", MythosStateEnum.class);

    public MythosBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(DIMENSION, MythosStateEnum.OVERWORLD));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if (!ctx.getWorld().isClient()) {
            if (ctx.getWorld().getRegistryKey().equals(World.OVERWORLD)) {
                this.setDefaultState(this.getDefaultState().with(DIMENSION, MythosStateEnum.OVERWORLD));
            } else if (ctx.getWorld().getRegistryKey().equals(World.NETHER)) {
                this.setDefaultState(this.getDefaultState().with(DIMENSION, MythosStateEnum.NETHER));
            }
        }

        return super.getPlacementState(ctx);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof PlayerEntity player && !world.isClient()) {
            updateBlockStateBasedOnDimension(world, pos, state);
            tryGodsCall(world, pos, state, player);
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    private void updateBlockStateBasedOnDimension(World world, BlockPos pos, BlockState state) {
        if (world.getRegistryKey().equals(World.OVERWORLD)) {
            world.setBlockState(pos, state.with(DIMENSION, MythosStateEnum.OVERWORLD));
        } else if (world.getRegistryKey().equals(World.NETHER)) {
            world.setBlockState(pos, state.with(DIMENSION, MythosStateEnum.NETHER));
        }
    }

    private void tryGodsCall(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (world.getRegistryKey().equals(World.OVERWORLD)) {
            tryCalliopeCall(world, pos, state, player);
        } else if (world.getRegistryKey().equals(World.NETHER)) {
            tryOrpheusCall(world, pos, state, player);
        }
    }

    private void tryCalliopeCall(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (WorldUtils.isSurroundedByBlocksTag(world, pos, BlockTags.FLOWERS, 1)) {
            world.setBlockState(pos, state.with(DIMENSION, MythosStateEnum.OVERWORLD_ACTIVE));

            if (player.isHolding(ItemsRegistry.HELLENIC_CODEX.get()) && player.isSneaking()) {
                activateCalliopeCall(world, pos, player);
            }
        }
    }

    private void tryOrpheusCall(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (WorldUtils.isSurroundedByBlocks(world, pos, Blocks.MAGMA_BLOCK, 0)) {
            world.setBlockState(pos, state.with(DIMENSION, MythosStateEnum.NETHER_ACTIVE));

            if (player.isHolding(ItemsRegistry.APOLLOS_SON.get()) && player.isSneaking()) {
                activateOrpheusCall(world, pos, player);
            }
        }
    }

    private void activateCalliopeCall(World world, BlockPos pos, PlayerEntity player) {
        WorldUtils.summonLightning(player, world);
        PlayerUtils.decrementHeldItem(player, ItemsRegistry.HELLENIC_CODEX.get());
        ItemStack calliopesLove = ItemsRegistry.CALLIOPES_LOVE.get().getDefaultStack();
        if (!player.getInventory().insertStack(calliopesLove)) {
            Block.dropStack(world, player.getBlockPos(), calliopesLove);
        }
        world.breakBlock(pos, false);
        world.playSound(null, pos, SoundEvents.ITEM_TRIDENT_RETURN, SoundCategory.BLOCKS, 1f, 1f);
    }

    private void activateOrpheusCall(World world, BlockPos pos, PlayerEntity player) {
        WorldUtils.summonLightning(player, world);
        PlayerUtils.decrementHeldItem(player, ItemsRegistry.APOLLOS_SON.get());
        ItemStack orpheusLyre = ItemsRegistry.ORPHEUS_LYRE.get().getDefaultStack();
        if (!player.getInventory().insertStack(orpheusLyre)) {
            Block.dropStack(world, player.getBlockPos(), orpheusLyre);
        }
        world.breakBlock(pos, false);
        world.playSound(null, pos, SoundEvents.ITEM_TRIDENT_RETURN, SoundCategory.BLOCKS, 1f, 1f);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DIMENSION);
    }
}
