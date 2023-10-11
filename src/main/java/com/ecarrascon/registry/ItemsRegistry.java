package com.ecarrascon.registry;

import com.ecarrascon.OrpheusMod;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

import static com.ecarrascon.item.setting.ModItemSettings.*;

public enum ItemsRegistry {

    ORPHEUS_LYRA("orpheus_lyra", () -> new Item(epicFireProof())),
    CALLIOPE_POEM("calliope_poem", () -> new Item(epicFireProof())),

    LYRA("lyra", () -> new Item(noStackable()));

    private final String pathName;
    private final Supplier<Item> itemSupplier;
    private final Integer burnTime;
    private Item item;

    ItemsRegistry(String pathName, Supplier<Item> itemSupplier) {
        this(pathName, itemSupplier, null);
    }

    ItemsRegistry(String pathName, Supplier<Item> itemSupplier, Integer burnTime) {
        this.pathName = pathName;
        this.itemSupplier = itemSupplier;
        this.burnTime = burnTime;
    }

    public static void registerAll() {
        for (ItemsRegistry value : values()) {
            Registry.register(Registry.ITEM, new Identifier(OrpheusMod.MOD_ID, value.pathName), value.get());
        }
    }

    public Item get() {
        if (item == null) {
            item = itemSupplier.get();
        }
        return item;
    }

    public String getId() {
        return Registry.ITEM.getId(get()).toString();
    }
}
