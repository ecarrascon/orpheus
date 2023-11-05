package com.ecarrascon.orpheus.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class OrpheusPlacedFeatures {

    public static final RegistryEntry<PlacedFeature> TEARS_OF_HADES_ORE_PLACED = PlacedFeatures.register("tears_of_hades_ore_placed",
            OrpheusConfiguredFeatures.TEARS_OF_HADES_ORE_SMALL, modifiersWithCount(7,
                    HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));

    public static final RegistryEntry<PlacedFeature> TEARS_OF_HADES_ORE_PLACED_LARGE = PlacedFeatures.register("tears_of_hades_ore_placed_large",
            OrpheusConfiguredFeatures.TEARS_OF_HADES_ORE_SMALL, modifiersWithRarity(9,
                    HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
    public static final RegistryEntry<PlacedFeature> TEARS_OF_HADES_ORE_PLACED_BURIED = PlacedFeatures.register("tears_of_hades_ore_placed_buried",
            OrpheusConfiguredFeatures.TEARS_OF_HADES_ORE_SMALL, modifiersWithCount(4,
                    HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }
    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}
