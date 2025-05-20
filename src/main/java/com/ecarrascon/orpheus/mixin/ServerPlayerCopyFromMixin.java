package com.ecarrascon.orpheus.mixin;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerCopyFromMixin {

    // KeepInventory when Orpheus Lyre in inventory, "PlayerEntityDropInventoryMixin" makes that items not being dropped
    @Redirect(method = "copyFrom", at = @At(value = "INVOKE", target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z", ordinal = 0))
    private boolean redirectCopyFromCondition(GameRules instance, GameRules.Key<GameRules.BooleanRule> rule, ServerPlayerEntity oldPlayer, boolean alive) {
        return ((ServerPlayerEntity) (Object) this).getServerWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY)
                || (oldPlayer.getInventory().contains(ItemsRegistry.ORPHEUS_LYRE.get().getDefaultStack())
                && Orpheus.CONFIG_VALUES.getOrpheusLyrePower().matches(".*(?:keep|both).*"));

    }

    @Inject(
            method = "copyFrom(Lnet/minecraft/server/network/ServerPlayerEntity;Z)V",
            at = @At("TAIL")
    )
    private void onRestoreFrom(ServerPlayerEntity oldPlayer, boolean keepEverything, CallbackInfo ci) {
        ServerPlayerEntity self = (ServerPlayerEntity) (Object) this;
        ServerWorld world = self.getServerWorld();

        boolean vanillaKeep = world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY);
        boolean lyreKeep = Orpheus.CONFIG_VALUES.getOneTimeUse()
                && oldPlayer.getInventory().contains(ItemsRegistry.ORPHEUS_LYRE.get().getDefaultStack())
                && Orpheus.CONFIG_VALUES.getOrpheusLyrePower().matches(".*(?:keep|both).*");

        if (!vanillaKeep && lyreKeep) {
            Inventory inv = self.getInventory();
            ItemStack lyre = ItemsRegistry.ORPHEUS_LYRE.get().getDefaultStack();
            for (int i = 0; i < inv.size(); i++) {
                ItemStack stack = inv.getStack(i);
                if (ItemStack.areItemsEqual(stack, lyre)) {
                    inv.removeStack(i, 1);
                    self.currentScreenHandler.sendContentUpdates();
                    break;
                }
            }
        }
    }

}
