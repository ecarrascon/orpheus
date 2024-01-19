package com.ecarrascon.orpheus;

import com.ecarrascon.orpheus.event.ArrowImmuneEvent;
import com.ecarrascon.orpheus.registry.BlocksRegistry;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.registry.LootsRegistry;
import com.ecarrascon.orpheus.villager.Villager;
import com.ecarrascon.orpheus.world.gen.OrpheusOreGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Orpheus implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Orpheus");

	public static final String MOD_ID = "orpheus";

	public static ConfigData CONFIG_VALUES = new ConfigData();

	public static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, "main"));


	@Override
	public void onInitialize() {
		CONFIG_VALUES = ConfigData.init();
		Registry.register(Registries.ITEM_GROUP, ITEM_GROUP, FabricItemGroup.builder()
				.displayName(Text.translatable("itemGroup.orpheus.main"))
				.icon(() -> new ItemStack(ItemsRegistry.ORPHEUS_LYRE.get()))
				.build());

		BlocksRegistry.registerAll();
		ItemsRegistry.registerAll();
		OrpheusOreGeneration.generateOres();
		LootsRegistry.registerAll();
		Villager.registerVillager();
		Villager.registerVillagerTradeOffer();
		Villager.registerPhilosopherHouses();

		ServerLivingEntityEvents.ALLOW_DAMAGE.register(new ArrowImmuneEvent());

		LOGGER.info("Done");
	}


}