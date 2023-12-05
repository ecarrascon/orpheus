package com.ecarrascon.orpheus.villager;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.BlocksRegistry;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.google.common.collect.ImmutableSet;
import fzzyhmstrs.structurized_reborn.impl.FabricStructurePoolRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class Villager {

    public static final VillagerProfession PHILOSOPHER = registerProfession("philosopher",
            RegistryKey.of(Registries.POINT_OF_INTEREST_TYPE.getKey(), new Identifier(Orpheus.MOD_ID, "philosopher_interest_point")));

    public static final PointOfInterestType PHILOSOPHER_INTEREST_POINT = registerInterestPoint("philosopher_interest_point",
            BlocksRegistry.EPIPHANY_TABLE.get());

    public static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, new Identifier(Orpheus.MOD_ID, name),
                VillagerProfessionBuilder.create().id(new Identifier(Orpheus.MOD_ID, name)).workstation(type)
                        .workSound(SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN).build());
    }

    public static void registerPhilosopherHouses() {
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/plains/houses"),
                new Identifier(Orpheus.MOD_ID, "philosopher_house_village"),
                1
        );
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/desert/houses"),
                new Identifier(Orpheus.MOD_ID, "philosopher_house_village_desert"),
                1
        );
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/savanna/houses"),
                new Identifier(Orpheus.MOD_ID, "philosopher_house_village_savanna"),
                1
        );
        FabricStructurePoolRegistry.registerSimple(
                new Identifier("minecraft:village/snowy/houses"),
                new Identifier(Orpheus.MOD_ID, "philosopher_house_village_snowy"),
                1
        );
    }

    public static PointOfInterestType registerInterestPoint(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier(Orpheus.MOD_ID, name),
                1, 1, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }
    public static void registerVillager() {
        Orpheus.LOGGER.debug("Registering Villager");

    }

    public static void registerVillagerTradeOffer() {

        // Level 1
        TradeOfferHelper.registerVillagerOffers(PHILOSOPHER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(ItemsRegistry.HOMERS_THE_ODYSSEY_SCROLL_FRAGMENT.get(), 1),
                    new ItemStack(ItemsRegistry.MOLY_HERB.get(), 1),
                    14, 2, 0f
            ));

            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(ItemsRegistry.HOMERS_THE_ILIAD_SCROLL_FRAGMENT.get(), 1),
                    new ItemStack(ItemsRegistry.PEGASUS_FEATHER.get(), 8),
                    16, 2, 0f
            ));


        });

        // Level 2
        TradeOfferHelper.registerVillagerOffers(PHILOSOPHER, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(ItemsRegistry.ARISTOTLES_NICOMACHEAN_ETHICS_SCROLL_FRAGMENT.get(), 1),
                    new ItemStack(ItemsRegistry.PALLADIUM_WOODEN_FRAGMENT.get(), 1),
                    9, 4, 0f
            ));

            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(ItemsRegistry.PLATOS_REPUBLIC_SCROLL_FRAGMENT.get(), 1),
                    new ItemStack(ItemsRegistry.NECTAR_SEED.get(), 1),
                    5, 4, 0f
            ));

        });

        // Level 3
        TradeOfferHelper.registerVillagerOffers(PHILOSOPHER, 3, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(ItemsRegistry.HERACLITIAN_FLUX_FRAGMENT.get(), 9),
                    new ItemStack(ItemsRegistry.HERACLITIAN_FLUX_POTION.get(), 1),
                    16, 8, 0f
            ));


            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(ItemsRegistry.THUCYDIDES_PELOPONNESIAN_WAR_SCROLL_FRAGMENT.get(), 1),
                    new ItemStack(ItemsRegistry.HEPHAESTUS_ARMOR_FRAGMENT.get(), 1),
                    24, 8, 0f
            ));

        });

        // Level 4
        TradeOfferHelper.registerVillagerOffers(PHILOSOPHER, 4, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(ItemsRegistry.STOIC_MEDITATIVE_STONE.get(), 1),
                    new ItemStack(ItemsRegistry.PANDORAS_PITHOS.get(), 1),
                    2, 27, 0f
            ));
        });

        // Level 5
        TradeOfferHelper.registerVillagerOffers(PHILOSOPHER, 5, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(ItemsRegistry.HELLENIC_CODEX.get(), 1),
                    new ItemStack(ItemsRegistry.TEARS_OF_HADES.get(), 3),
                    5, 2, 0f
            ));
        });

    }


}
