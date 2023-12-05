package com.ecarrascon.orpheus.registry;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

public class LootsRegistry {
    private static final Identifier TURTLE_LOOT_TABLE_ID = EntityType.TURTLE.getLootTableId();
    private static final Identifier COW_LOOT_TABLE_ID = EntityType.COW.getLootTableId();


    // Turtle drops(33%) scute
    public static void registerAll() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && TURTLE_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.3f))
                        .with(ItemEntry.builder(Items.SCUTE));

                tableBuilder.pool(poolBuilder);
            }
        });
        // Cow drops(33%) cow gut
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && COW_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.3f))
                        .with(ItemEntry.builder(ItemsRegistry.COW_GUT.get()));

                tableBuilder.pool(poolBuilder);
            }
        });

    }
}
