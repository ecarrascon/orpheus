package com.ecarrascon.orpheus;

import com.ecarrascon.orpheus.event.ArrowImmuneEvent;
import com.ecarrascon.orpheus.registry.BlocksRegistry;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.registry.LootsRegistry;
import com.ecarrascon.orpheus.villager.Villager;
import com.ecarrascon.orpheus.world.feature.OrpheusConfiguredFeatures;
import com.ecarrascon.orpheus.world.gen.OrpheusOreGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Orpheus implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Orpheus Mod");

    public static ConfigData CONFIG_VALUES = new ConfigData();


    public static final String MOD_ID = "orpheus";

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "main"),
            () -> new ItemStack(ItemsRegistry.ORPHEUS_LYRE.get()));


    @Override
    public void onInitialize() {
        CONFIG_VALUES = ConfigData.init();
        OrpheusConfiguredFeatures.registerConfiguredFeatures();
        BlocksRegistry.registerAll();
        ItemsRegistry.registerAll();
        LootsRegistry.registerAll();
        OrpheusOreGeneration.generateOres();
        Villager.registerVillager();
        Villager.registerVillagerTradeOffer();
        Villager.registerPhilosopherHouses();

        ServerLivingEntityEvents.ALLOW_DAMAGE.register(new ArrowImmuneEvent());


        LOGGER.info("Done");
    }


}