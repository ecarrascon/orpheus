package com.ecarrascon.orpheus.worldgen;

import com.ecarrascon.orpheus.Orpheus;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_TEARS_OF_HADES_ORE_SMALL = registerKey("add_tears_of_hades_ore_small");
    public static final ResourceKey<BiomeModifier> ADD_TEARS_OF_HADES_ORE_BURIED = registerKey("add_tears_of_hades_ore_buried");
    public static final ResourceKey<BiomeModifier> ADD_TEARS_OF_HADES_ORE_LARGE = registerKey("add_tears_of_hades_ore_large");



    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);


        context.register(ADD_TEARS_OF_HADES_ORE_LARGE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TEARS_OF_HADES_ORE_PLACED_LARGE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_TEARS_OF_HADES_ORE_SMALL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),

                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TEARS_OF_HADES_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_TEARS_OF_HADES_ORE_BURIED, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TEARS_OF_HADES_ORE_PLACED_BURIED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Orpheus.MOD_ID, name));
    }
}
