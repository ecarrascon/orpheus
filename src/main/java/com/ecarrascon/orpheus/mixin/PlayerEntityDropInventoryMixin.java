package com.ecarrascon.orpheus.mixin;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityDropInventoryMixin {

    @Inject(method = "dropInventory", at = @At("HEAD"), cancellable = true)
    private void noEnterTheIfToNotDropTheItems(CallbackInfo info) {
        if (!((PlayerEntity) (Object) this).getWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY)
                && ((PlayerEntity) (Object) this).getInventory().contains(ItemsRegistry.ORPHEUS_LYRE.get().getDefaultStack())
                && Orpheus.CONFIG_VALUES.getOrpheusLyrePower().matches(".*(?:keep|both).*")) {
            info.cancel();
        }
    }

    @Inject(method = "getXpToDrop", at = @At("HEAD"), cancellable = true)
    private void noEnterTheIfToNotDropTheXp(CallbackInfoReturnable<Integer> cir) {
        if (!((PlayerEntity) (Object) this).getWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY)
                && ((PlayerEntity) (Object) this).getInventory().contains(ItemsRegistry.ORPHEUS_LYRE.get().getDefaultStack())
                && Orpheus.CONFIG_VALUES.getOrpheusLyrePower().matches(".*(?:keep|both).*")) {
            cir.setReturnValue(0);
            cir.cancel();
        }
    }

}
