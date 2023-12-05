package com.ecarrascon.orpheus.world.feature;

import com.ecarrascon.orpheus.registry.BlocksRegistry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class OrpheusConfiguredFeatures {
    public static final List<OreFeatureConfig.Target> OVERWORLD_TEARS_OF_HADES_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    BlocksRegistry.TEARS_OF_HADES_ORE.get().getDefaultState()),

            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    BlocksRegistry.DEEPSLATE_TEARS_OF_HADES_ORE.get().getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> TEARS_OF_HADES_ORE_SMALL =
            ConfiguredFeatures.register("tears_of_hades_ore_small", Feature.ORE, new OreFeatureConfig(OVERWORLD_TEARS_OF_HADES_ORES, 4, 0.4f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> TEARS_OF_HADES_ORE_LARGE =
            ConfiguredFeatures.register("tears_of_hades_ore_large", Feature.ORE, new OreFeatureConfig(OVERWORLD_TEARS_OF_HADES_ORES, 12, 0.4f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> TEARS_OF_HADES_ORE_BURIED =
            ConfiguredFeatures.register("tears_of_hades_ore_buried", Feature.ORE, new OreFeatureConfig(OVERWORLD_TEARS_OF_HADES_ORES, 8, 1f));


    public static void registerConfiguredFeatures() {
    }
}
