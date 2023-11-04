package com.ecarrascon.orpheus.entity.client;


import com.ecarrascon.orpheus.entity.animation.ViperAnimations;
import com.ecarrascon.orpheus.entity.custom.ViperEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class NoseHornedViperModel<T extends ViperEntity> extends SinglePartEntityModel<T> {
	private final ModelPart viperaammodytes;
	private final ModelPart head;

	public NoseHornedViperModel(ModelPart root) {
		this.viperaammodytes = root.getChild("viperaammodytes");
		this.head = viperaammodytes.getChild("body").getChild("head");


	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData viperaammodytes = modelPartData.addChild("viperaammodytes", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData body = viperaammodytes.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 15).cuboid(-0.75F, -0.3125F, -1.025F, 2.0F, 2.4F, 2.0F, new Dilation(0.0F))
				.uv(16, 13).cuboid(-0.75F, -1.4625F, -0.475F, 1.0F, 1.15F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-7.25F, -2.3875F, 0.025F));

		ModelPartData one = body.addChild("one", ModelPartBuilder.create().uv(1, 6).cuboid(-1.0F, -1.5F, -1.2F, 2.0F, 3.0F, 2.4F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -1.5F, 0.0F));

		ModelPartData two = body.addChild("two", ModelPartBuilder.create().uv(2, 0).cuboid(-1.0F, -1.25F, -1.5F, 2.0F, 2.5F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -1.25F, 0.0F));

		ModelPartData three = body.addChild("three", ModelPartBuilder.create().uv(10, 8).cuboid(-1.0F, -1.325F, -1.1F, 2.0F, 2.65F, 2.2F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -1.325F, 0.0F));

		ModelPartData four = body.addChild("four", ModelPartBuilder.create().uv(15, 3).cuboid(-1.0F, -1.125F, -1.25F, 2.0F, 2.25F, 2.5F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -1.125F, 0.0F));

		ModelPartData five = body.addChild("five", ModelPartBuilder.create().uv(8, 13).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -1.0F, 0.0F));

		ModelPartData tail_two = body.addChild("tail_two", ModelPartBuilder.create().uv(16, 0).cuboid(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(7.0F, -0.5F, 0.0F));

		ModelPartData tail_one = body.addChild("tail_one", ModelPartBuilder.create().uv(9, 0).cuboid(-1.0F, -0.55F, -0.65F, 2.0F, 1.1F, 1.3F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -0.55F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void setAngles(ViperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		updateAnimation(entity.walkingAnimationState, ViperAnimations.VIPERAAMMODYTES_WALK, ageInTicks, Math.min((float)entity.getVelocity().lengthSquared() * 200f, 8f));

	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		viperaammodytes.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}


	@Override
	public ModelPart getPart() {
		return viperaammodytes;
	}
}