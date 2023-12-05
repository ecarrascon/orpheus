package com.ecarrascon.orpheus.world.feature;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.BlocksRegistry;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class OrpheusConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> TEARS_OF_HADES_ORE_SMALL_KEY = registerKey("tears_of_hades_ore_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TEARS_OF_HADES_ORE_LARGE_KEY = registerKey("tears_of_hades_ore_large");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TEARS_OF_HADES_ORE_BURIED_KEY = registerKey("tears_of_hades_ore_buried");


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldTearsOfHadesOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, BlocksRegistry.TEARS_OF_HADES_ORE.get().getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, BlocksRegistry.DEEPSLATE_TEARS_OF_HADES_ORE.get().getDefaultState()));


        register(context, TEARS_OF_HADES_ORE_SMALL_KEY, Feature.ORE, new OreFeatureConfig(overworldTearsOfHadesOres, 4, 0.5f));
        register(context, TEARS_OF_HADES_ORE_LARGE_KEY, Feature.ORE, new OreFeatureConfig(overworldTearsOfHadesOres, 12, 0.7f));
        register(context, TEARS_OF_HADES_ORE_BURIED_KEY, Feature.ORE, new OreFeatureConfig(overworldTearsOfHadesOres, 8, 1f));

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Orpheus.MOD_ID, name));
    }
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}