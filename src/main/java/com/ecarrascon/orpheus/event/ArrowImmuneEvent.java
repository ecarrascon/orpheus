package com.ecarrascon.orpheus.event;

import com.ecarrascon.orpheus.config.ConfigDataCommon;
import com.ecarrascon.orpheus.Orpheus;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Orpheus.MOD_ID)
public class ArrowImmuneEvent {
    @SubscribeEvent
    public static void allowDamage(LivingHurtEvent event) {
        if (ConfigDataCommon.ORPHEUS_LYRE_POWER.get().matches(".*(?:protect|both).*")) {
            if (event.getEntity() instanceof Player) {
                if (event.getSource().is(DamageTypes.ARROW)) {
                    event.setAmount(0);
                }
            }
        }
    }
}
