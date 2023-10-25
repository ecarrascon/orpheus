package com.ecarrascon.orpheus;

import com.ecarrascon.orpheus.registry.BlocksRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class OrpheusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksRegistry.NECTAR_CROP.get(), RenderLayer.getCutout());


        BlockRenderLayerMap.INSTANCE.putBlock(BlocksRegistry.MOLY_HERB.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksRegistry.POTTED_MOLY_HERB.get(), RenderLayer.getCutout());
    }
}
