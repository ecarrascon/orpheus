package com.ecarrascon.orpheus.item;

import com.ecarrascon.orpheus.Orpheus;
import com.google.common.collect.Sets;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class OrpheusItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Orpheus.MOD_ID);
    public static LinkedHashSet<RegistryObject<Item>> ORPHEUS_TAB_ITEMS = Sets.newLinkedHashSet();


    public static final RegistryObject<Item> PLAIN_STRING = registerWithTab("plain_string", () -> new Item(new Item.Properties()));

    public static RegistryObject<Item> registerWithTab(final String name, final Supplier<Item> supplier) {
        RegistryObject<Item> item = ITEMS.register(name, supplier);
        ORPHEUS_TAB_ITEMS.add(item);
        return item;
    }

}
