package com.ecarrascon.orpheus.world.gen;

import com.ecarrascon.orpheus.world.feature.OrpheusPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class OrpheusOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, OrpheusPlacedFeatures.TEARS_OF_HADES_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, OrpheusPlacedFeatures.TEARS_OF_HADES_ORE_PLACED_LARGE_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, OrpheusPlacedFeatures.TEARS_OF_HADES_ORE_PLACED_BURIED_KEY);

    }
}
