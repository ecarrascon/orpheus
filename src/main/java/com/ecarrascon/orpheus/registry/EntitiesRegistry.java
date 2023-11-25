package com.ecarrascon.orpheus.registry;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.entity.custom.ViperEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntitiesRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Orpheus.MOD_ID);

    public static final RegistryObject<EntityType<ViperEntity>> VIPER =
            ENTITY_TYPES.register("viper", () -> EntityType.Builder.of(ViperEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.3f).build("viper"));
}
