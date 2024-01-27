package com.ecarrascon.orpheus.block;

import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.util.PlayerUtils;
import com.ecarrascon.orpheus.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;

public class MyhtosBlock extends Block {
    public static final EnumProperty<MythosStateEnum> DIMENSION = EnumProperty.create("dimension", MythosStateEnum.class);

    public MyhtosBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(DIMENSION, MythosStateEnum.OVERWORLD));
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Level world = pContext.getLevel();

        if (!world.isClientSide()) {
            if (world.dimension().equals(Level.OVERWORLD)) {
                this.registerDefaultState(this.defaultBlockState().setValue(DIMENSION, MythosStateEnum.OVERWORLD));
            } else if (world.dimension().equals(Level.NETHER)) {
                this.registerDefaultState(this.defaultBlockState().setValue(DIMENSION, MythosStateEnum.NETHER));
            }
        }

        return super.getStateForPlacement(pContext);
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof Player player && !world.isClientSide()) {
            updateBlockStateBasedOnDimension(state, world, pos);
            tryGodsCall(world, pos, state, player);
        }

        super.stepOn(world, pos, state, entity);
    }

    private void updateBlockStateBasedOnDimension(BlockState state, Level world, BlockPos pos) {
        if (world.dimension().equals(Level.OVERWORLD)) {
            world.setBlock(pos, state.setValue(DIMENSION, MythosStateEnum.OVERWORLD), 3);
        } else if (world.dimension().equals(Level.NETHER)) {
            world.setBlock(pos, state.setValue(DIMENSION, MythosStateEnum.NETHER), 3);
        }
    }

    private void tryGodsCall(Level world, BlockPos pos, BlockState state, Player player) {
        if (world.dimension().equals(Level.OVERWORLD)) {
            tryCalliopeCall(world, pos, state, player);
        } else if (world.dimension().equals(Level.NETHER)) {
            tryOrpheusCall(world, pos, state, player);
        }
    }

    private void tryCalliopeCall(Level world, BlockPos pos, BlockState state, Player player) {
        if (WorldUtils.isSurroundedByBlocksTag(world, pos, BlockTags.FLOWERS, 1)) {
            world.setBlock(pos, state.setValue(DIMENSION, MythosStateEnum.OVERWORLD_ACTIVE), 3);

            if (player.isHolding(ItemsRegistry.HELLENIC_CODEX.get()) && player.isShiftKeyDown()) {
                activateCalliopeCall(world, pos, player);
            }
        }
    }

    private void tryOrpheusCall(Level world, BlockPos pos, BlockState state, Player player) {
        if (WorldUtils.isSurroundedByBlocks(world, pos, Blocks.MAGMA_BLOCK, 0)) {
            world.setBlock(pos, state.setValue(DIMENSION, MythosStateEnum.NETHER_ACTIVE), 3);

            if (player.isHolding(ItemsRegistry.APOLLOS_SON.get()) && player.isShiftKeyDown()) {
                activateOrpheusCall(world, pos, player);
            }
        }
    }

    private void activateCalliopeCall(Level world, BlockPos pos, Player player) {
        WorldUtils.summonLightning(world, player);
        PlayerUtils.decrementHeldItem(player, ItemsRegistry.HELLENIC_CODEX.get());
        ItemStack calliopesLove = ItemsRegistry.CALLIOPES_LOVE.get().getDefaultInstance();
        if (!player.getInventory().add(calliopesLove)) {
            Block.popResource(world, player.blockPosition(), calliopesLove);
        }
        world.destroyBlock(pos, false);
        world.playSound(null, pos, SoundEvents.TRIDENT_RETURN, SoundSource.BLOCKS, 1f, 1f);
    }

    private void activateOrpheusCall(Level world, BlockPos pos, Player player) {
        WorldUtils.summonLightning(world, player);
        PlayerUtils.decrementHeldItem(player, ItemsRegistry.APOLLOS_SON.get());
        ItemStack orpheusLyre = ItemsRegistry.ORPHEUS_LYRE.get().getDefaultInstance();
        if (!player.getInventory().add(orpheusLyre)) {
            Block.popResource(world, player.blockPosition(), orpheusLyre);
        }
        world.destroyBlock(pos, false);
        world.playSound(null, pos, SoundEvents.TRIDENT_RETURN, SoundSource.BLOCKS, 1f, 1f);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DIMENSION);
    }
}