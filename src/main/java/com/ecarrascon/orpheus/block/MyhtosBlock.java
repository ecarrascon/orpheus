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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class MyhtosBlock extends Block {
    public static final EnumProperty<MythosStateEnum> DIMENSION = EnumProperty.create("dimension", MythosStateEnum.class);

    public MyhtosBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(DIMENSION, MythosStateEnum.OVERWORLD));
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pLevel.isClientSide() && pLevel.dimension().equals(Level.OVERWORLD)) {
            pLevel.setBlock(pPos, pState.setValue(DIMENSION, MythosStateEnum.OVERWORLD), 2);
        }
        if (!pLevel.isClientSide() && pLevel.dimension().equals(Level.NETHER)) {
            pLevel.setBlock(pPos, pState.setValue(DIMENSION, MythosStateEnum.NETHER), 2);
        }

        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pLevel.isClientSide() && pEntity instanceof Player player) {
            updateBlockStateBasedOnDimension(pState, pLevel, pPos);
            // Get Calliope's Love
            if (pLevel.dimension().equals(Level.OVERWORLD) && WorldUtils.isSurroundedByBlocksTag(pLevel, pPos, BlockTags.SMALL_FLOWERS, 1)) {
                pLevel.setBlock(pPos, pState.setValue(DIMENSION, MythosStateEnum.OVERWORLD_ACTIVE), 2);

                if (player.isHolding(ItemsRegistry.HELLENIC_CODEX.get())
                        && player.isShiftKeyDown() ) {
                    WorldUtils.summonLightning(pLevel, player);
                    PlayerUtils.decrementHeldItem(player, ItemsRegistry.HELLENIC_CODEX.get());
                    giveCalliopesLoveItem(player, pLevel);
                    pLevel.destroyBlock(pPos, false);
                    pLevel.playSound(null, pPos, SoundEvents.TRIDENT_RETURN, SoundSource.BLOCKS, 1f, 1f);

                }
            }

            // Get Orpheus Lyre
            if (pLevel.dimension().equals(Level.NETHER) && WorldUtils.isSurroundedByBlocks(pLevel, pPos, Blocks.MAGMA_BLOCK, 0)) {
                pLevel.setBlock(pPos, pState.setValue(DIMENSION, MythosStateEnum.NETHER_ACTIVE), 2);

                if (player.isHolding(ItemsRegistry.APOLLOS_SON.get())
                        && player.isShiftKeyDown() ) {
                    WorldUtils.summonLightning(pLevel, player);
                    PlayerUtils.decrementHeldItem(player, ItemsRegistry.APOLLOS_SON.get());
                    giveOrpheusLyreItem(player, pLevel);
                    pLevel.destroyBlock(pPos, false);
                    pLevel.playSound(null, pPos, SoundEvents.TRIDENT_RETURN, SoundSource.BLOCKS, 1f, 1f);
                }
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    private void updateBlockStateBasedOnDimension(BlockState pState, Level pLevel, BlockPos pPos) {
        if (pLevel.dimension().equals(Level.OVERWORLD)) {
            pLevel.setBlock(pPos, pState.setValue(DIMENSION, MythosStateEnum.OVERWORLD), 2);
        }
        if (pLevel.dimension().equals(Level.NETHER)) {
            pLevel.setBlock(pPos, pState.setValue(DIMENSION, MythosStateEnum.NETHER), 2);

        }
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

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(DIMENSION);

    }
}
