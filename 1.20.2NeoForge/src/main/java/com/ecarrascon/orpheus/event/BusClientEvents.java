package com.ecarrascon.orpheus.event;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.entity.client.ModelLayers;
import com.ecarrascon.orpheus.entity.client.ViperModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;


@Mod.EventBusSubscriber(modid = Orpheus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLayers.VIPER_LAYER, ViperModel::createBodyLayer);

    }
}
