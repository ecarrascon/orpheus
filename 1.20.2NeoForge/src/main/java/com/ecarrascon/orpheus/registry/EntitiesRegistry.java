package com.ecarrascon.orpheus.registry;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.entity.custom.ViperEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class EntitiesRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, Orpheus.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<ViperEntity>> VIPER =
            ENTITY_TYPES.register("viper", () -> EntityType.Builder.of(ViperEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.3f).build("viper"));
}
