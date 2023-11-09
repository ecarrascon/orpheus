package com.ecarrascon.orpheus.entity.client;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.entity.custom.ViperEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ViperRenderer extends MobEntityRenderer<ViperEntity, ViperModel<ViperEntity>> {

    private static final Identifier TEXTURE = new Identifier(Orpheus.MOD_ID, "textures/entity/vipera_ammodytes.png");

    public ViperRenderer(EntityRendererFactory.Context context) {
        super(context, new ViperModel<>(context.getPart(OrpheusModelLayers.VIPER)), 0.5f); // Shadow
    }

    @Override
    public Identifier getTexture(ViperEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(ViperEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if (mobEntity.isBaby()) {
            matrixStack.scale(2f,2f,2f);
        } else {
            matrixStack.scale(1f,1f,1f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
