package com.ecarrascon.orpheus.registry;

import com.ecarrascon.orpheus.Orpheus;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class VillagersRegistry {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, Orpheus.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(Registries.VILLAGER_PROFESSION, Orpheus.MOD_ID);

    public static final DeferredHolder<PoiType, PoiType> PHILOSOPHER_INTEREST_POINT = POI_TYPES.register("philosopher_interest_point",
            () -> new PoiType(ImmutableSet.copyOf(BlocksRegistry.EPIPHANY_TABLE.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final DeferredHolder<VillagerProfession, VillagerProfession> PHILOSOPHER =
            VILLAGER_PROFESSIONS.register("philosopher", () -> new VillagerProfession("philosopher",
                    holder -> holder.value() == PHILOSOPHER_INTEREST_POINT.get(), holder -> holder.value() == PHILOSOPHER_INTEREST_POINT.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_LIBRARIAN));



    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
