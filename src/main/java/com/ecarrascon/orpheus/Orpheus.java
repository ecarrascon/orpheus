package com.ecarrascon.orpheus;

import com.ecarrascon.orpheus.registry.BlocksRegistry;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.villager.Villager;
import com.ecarrascon.orpheus.world.dimension.ModDimensions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Orpheus implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Orpheus Mod");

	public static final String MOD_ID = "orpheus";

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "main"),
			() -> new ItemStack(ItemsRegistry.ORPHEUS_LYRE.get()));


	private static final Identifier TURTLE_LOOT_TABLE_ID = EntityType.TURTLE.getLootTableId();

	@Override
	public void onInitialize() {
		ItemsRegistry.registerAll();
		BlocksRegistry.registerAll();
		Villager.registerVillager();
		Villager.registerVillagerTradeOffer();
		ModDimensions.register();
		lootTablesIni();


		LOGGER.info("Done");
	}


	protected void lootTablesIni() {

		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (source.isBuiltin() && TURTLE_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.conditionally(RandomChanceLootCondition.builder(0.3f))
						.with(ItemEntry.builder(Items.SCUTE));

				tableBuilder.pool(poolBuilder);
			}
		});

	}
}