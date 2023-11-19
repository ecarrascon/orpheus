package com.ecarrascon.orpheus.world.feature;

import com.ecarrascon.orpheus.Orpheus;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class OrpheusPlacedFeatures {

    public static final RegistryKey<PlacedFeature> TEARS_OF_HADES_ORE_PLACED_KEY = registerKey("tears_of_hades_ore_placed");
    public static final RegistryKey<PlacedFeature> TEARS_OF_HADES_ORE_PLACED_LARGE_KEY = registerKey("tears_of_hades_ore_placed_large");
    public static final RegistryKey<PlacedFeature> TEARS_OF_HADES_ORE_PLACED_BURIED_KEY = registerKey("tears_of_hades_ore_placed_buried");


    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, TEARS_OF_HADES_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OrpheusConfiguredFeatures.TEARS_OF_HADES_ORE_SMALL_KEY),
                modifiersWithCount(7,
                        HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
        register(context, TEARS_OF_HADES_ORE_PLACED_LARGE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OrpheusConfiguredFeatures.TEARS_OF_HADES_ORE_LARGE_KEY),
                modifiersWithRarity(9,
                        HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
        register(context, TEARS_OF_HADES_ORE_PLACED_BURIED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OrpheusConfiguredFeatures.TEARS_OF_HADES_ORE_BURIED_KEY),
                modifiersWithCount(4,
                        HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Orpheus.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }

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
