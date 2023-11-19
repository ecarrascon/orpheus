package com.ecarrascon.orpheus;

import com.ecarrascon.orpheus.entity.OrpheusEntities;
import com.ecarrascon.orpheus.entity.custom.ViperEntity;
import com.ecarrascon.orpheus.registry.BlocksRegistry;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.registry.LootsRegistry;
import com.ecarrascon.orpheus.registry.SoundsRegistry;
import com.ecarrascon.orpheus.villager.Villager;
import com.ecarrascon.orpheus.world.gen.OrpheusOreGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Orpheus implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Orpheus Mod");

	public static final String MOD_ID = "orpheus";

	public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(MOD_ID, "main"))
			.icon(() -> new ItemStack(ItemsRegistry.ORPHEUS_LYRE.get())).build();


	@Override
	public void onInitialize() {
		BlocksRegistry.registerAll();
		ItemsRegistry.registerAll();
		OrpheusOreGeneration.generateOres();
		LootsRegistry.registerAll();
		SoundsRegistry.registerAll();
		Villager.registerVillager();
		Villager.registerVillagerTradeOffer();
		Villager.registerPhilosopherHouses();




		FabricDefaultAttributeRegistry.register(OrpheusEntities.VIPER, ViperEntity.createViperAttributes());
		LOGGER.info("Done");
	}


}