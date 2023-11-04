package com.ecarrascon.orpheus.entity.custom;

import com.ecarrascon.orpheus.entity.OrpheusEntities;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ViperEntity extends AnimalEntity {

    public final AnimationState walkingAnimationState = new AnimationState();
    public ViperEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        goalSelector.add(0, new SwimGoal(this));
        goalSelector.add(1, new MeleeAttackGoal(this, 1.4d, false));
        goalSelector.add(2, new AnimalMateGoal(this, 1d));
        goalSelector.add(4, new FollowParentGoal(this, 1d));
        goalSelector.add(5, new WanderAroundFarGoal(this, 1d));
        goalSelector.add(5, new SwimAroundGoal(this, 1.25d, 120));
        goalSelector.add(6, new LookAtEntityGoal(this, LivingEntity.class, 6f));
        goalSelector.add(7, new LookAroundGoal(this));

    }



    public static DefaultAttributeContainer.Builder createViperAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.ACACIA_BOAT);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return OrpheusEntities.VIPER.create(world);
    }

    private boolean shouldWalk() {
        return isOnGround() && getVelocity().horizontalLengthSquared() > 1e-6d && !isInsideWaterOrBubbleColumn();
    }

    @Override
    public void tick() {
        super.tick();
        updateAnimations();

    }

    private void updateAnimations() {
        if (getWorld().isClient) {
            if (shouldWalk()) walkingAnimationState.startIfNotRunning(age);
            else walkingAnimationState.stop();
        }
    }
}
