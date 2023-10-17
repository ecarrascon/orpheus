package com.ecarrascon.orpheus.villager;

import com.ecarrascon.orpheus.OrpheusMod;
import com.ecarrascon.orpheus.registry.BlocksRegistry;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class Villager {

    public static final VillagerProfession PHILOSOPHER = registerProfession("philosopher",
            RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, new Identifier(OrpheusMod.MOD_ID, "philosopher_interest_point")));

    public static final PointOfInterestType PHILOSOPHER_INTEREST_POINT = registerInterestPoint("philosopher_interest_point",
            BlocksRegistry.EPIPHANY_TABLE.get());

    public static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registry.VILLAGER_PROFESSION, new Identifier(OrpheusMod.MOD_ID, name),
                VillagerProfessionBuilder.create().id(new Identifier(OrpheusMod.MOD_ID, name)).workstation(type)
                        .workSound(SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN).build());
    }

    public static PointOfInterestType registerInterestPoint(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier(OrpheusMod.MOD_ID, name),
                1, 1, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }
    public static void registerVillager() {
        OrpheusMod.LOGGER.debug("Registering Villager");
    }
}
