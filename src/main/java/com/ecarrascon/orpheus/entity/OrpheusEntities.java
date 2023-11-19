package com.ecarrascon.orpheus.entity;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.entity.custom.ViperEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class OrpheusEntities {

    public static final EntityType<ViperEntity> VIPER = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Orpheus.MOD_ID, "viper"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ViperEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 0.3f)).build());


}
