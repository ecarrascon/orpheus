package com.ecarrascon.mixin;

import com.ecarrascon.registry.ItemsRegistry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerMixin {

	@Redirect(method = "copyFrom", at = @At(value = "INVOKE", target = "net/minecraft/world/GameRules.getBoolean(Lnet/minecraft/world/GameRules$Key;)Z", ordinal = 0))
	private boolean redirectCopyFromCondition(GameRules instance, GameRules.Key<GameRules.BooleanRule> rule, ServerPlayerEntity oldPlayer, boolean alive) {
        return ((ServerPlayerEntity) (Object) this).world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY)
                || oldPlayer.getInventory().contains(ItemsRegistry.ORPHEUS_LYRA.get().getDefaultStack());

    }

}
