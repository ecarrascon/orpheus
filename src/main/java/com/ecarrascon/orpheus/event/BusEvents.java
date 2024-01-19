package com.ecarrascon.orpheus.event;

import com.ecarrascon.orpheus.Orpheus;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Orpheus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
    }
}
