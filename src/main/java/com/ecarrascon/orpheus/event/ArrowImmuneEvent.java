package com.ecarrascon.orpheus.event;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class ArrowImmuneEvent implements ServerLivingEntityEvents.AllowDamage{
    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        return !(entity instanceof PlayerEntity player)
                || !source.isProjectile()
                || !player.getInventory().contains(ItemsRegistry.ORPHEUS_LYRE.get().getDefaultStack())
                || !Orpheus.CONFIG_VALUES.getOrpheusLyrePower().matches(".*(?:protect|both).*");
    }
}
