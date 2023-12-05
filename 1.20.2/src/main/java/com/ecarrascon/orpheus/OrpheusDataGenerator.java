package com.ecarrascon.orpheus;

import com.ecarrascon.orpheus.world.feature.OrpheusConfiguredFeatures;
import com.ecarrascon.orpheus.world.feature.OrpheusPlacedFeatures;
import com.ecarrascon.orpheus.world.gen.OrpheusWorldGeneration;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
public class OrpheusDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(OrpheusWorldGeneration::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, OrpheusConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, OrpheusPlacedFeatures::bootstrap);
	}

}
