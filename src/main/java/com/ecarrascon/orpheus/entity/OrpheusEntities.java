package com.ecarrascon.orpheus.entity;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.entity.custom.ViperEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class OrpheusEntities {

    public static final EntityType<ViperEntity> VIPER = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(Orpheus.MOD_ID, "viper"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ViperEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f)).build());


}
