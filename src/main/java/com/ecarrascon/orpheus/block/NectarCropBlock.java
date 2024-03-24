package com.ecarrascon.orpheus.block;

import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.util.PlayerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;

public class NectarCropBlock extends CropBlock {
    public NectarCropBlock(Properties pProperties) {
        super(pProperties);
    }

    protected ItemLike getBaseSeedId() {
        return ItemsRegistry.NECTAR_SEED.get();
    }


    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity player) {
        if (player instanceof Player playerStepping && (isMaxAge(state) && !world.isClientSide() && !player.isSpectator()
                && playerStepping.isHolding(Items.HONEY_BOTTLE))) {

            PlayerUtils.decrementHeldItem(playerStepping, Items.HONEY_BOTTLE);

            ItemStack brotioNectar = ItemsRegistry.BROTOI_NECTAR.get().getDefaultInstance();
            world.playSound(null, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1f, 1f);
            if (!playerStepping.addItem(brotioNectar))
                playerStepping.drop(brotioNectar, false);

            world.setBlock(pos, state.setValue(AGE, 0), 2);

        }

        super.stepOn(world, pos, state, player);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level world, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {

        if (isMaxAge(state) && !world.isClientSide() && !player.isSpectator()
                && player.isHolding(Items.HONEY_BOTTLE)) {

            PlayerUtils.decrementHeldItem(player, Items.HONEY_BOTTLE);

            ItemStack brotioNectar = ItemsRegistry.BROTOI_NECTAR.get().getDefaultInstance();
            world.playSound(null, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1f, 1f);
            if (!player.addItem(brotioNectar))
                player.drop(brotioNectar, false);


            world.setBlock(pos, state.setValue(AGE, 0), 2);

            return false;
        }

        return super.onDestroyedByPlayer(state, world, pos, player, willHarvest, fluid);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand pHand, BlockHitResult pHit) {

        if (isMaxAge(state) && !world.isClientSide() && !player.isSpectator()
                && player.isHolding(Items.HONEY_BOTTLE)) {

            PlayerUtils.decrementHeldItem(player, Items.HONEY_BOTTLE);

            ItemStack brotioNectar = ItemsRegistry.BROTOI_NECTAR.get().getDefaultInstance();
            world.playSound(null, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1f, 1f);
            if (!player.addItem(brotioNectar))
                player.drop(brotioNectar, false);



            world.setBlock(pos, state.setValue(AGE, 0), 2);


        }

        return super.use(state, world, pos, player, pHand, pHit);
    }
}
