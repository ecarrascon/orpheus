package com.ecarrascon.orpheus;

import com.ecarrascon.orpheus.entity.OrpheusEntities;
import com.ecarrascon.orpheus.entity.client.NoseHornedViperModel;
import com.ecarrascon.orpheus.entity.client.OrpheusModelLayers;
import com.ecarrascon.orpheus.entity.client.ViperRenderer;
import com.ecarrascon.orpheus.registry.BlocksRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class OrpheusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksRegistry.NECTAR_CROP.get(), RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksRegistry.MOLY_HERB.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksRegistry.POTTED_MOLY_HERB.get(), RenderLayer.getCutout());


        EntityRendererRegistry.register(OrpheusEntities.VIPER, ViperRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(OrpheusModelLayers.VIPER, NoseHornedViperModel::getTexturedModelData);
    }
}
