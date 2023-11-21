package com.ecarrascon.orpheus.block;

import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NectarCropBlock extends CropBlock {
    public NectarCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ItemsRegistry.NECTAR_SEED.get();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (isMature(state) && !world.isClient() && !player.isSpectator()
                && player.isHolding(Items.HONEY_BOTTLE)) {

            player.getMainHandStack().decrement(1);

            ItemStack brotioNectar = ItemsRegistry.BROTOI_NECTAR.get().getDefaultStack();
            world.playSound(null, pos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1f, 1f);
            if (!player.giveItemStack(brotioNectar))
                player.dropItem(brotioNectar, false);



            world.setBlockState(pos, state.with(AGE, 0), 2);


        }

        return super.onUse(state, world, pos, player, hand, hit);

    }

}
