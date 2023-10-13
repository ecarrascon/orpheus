package com.ecarrascon.orpheus.registry;

import com.ecarrascon.orpheus.OrpheusMod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;
import com.ecarrascon.orpheus.item.setting.ItemSettingsHelper;


public enum ItemsRegistry {

    // Block items
    PEGASUS_FEATHERS_BLOCK("pegasus_feathers_block", () -> new BlockItem(BlocksRegistry.PEGASUS_FEATHERS_BLOCK.get(), ItemSettingsHelper.baseSettings())),

    ORPHEUS_LYRA("orpheus_lyra", () -> new Item(ItemSettingsHelper.epicFireProofSettings())),
    CALLIOPE_POEM("calliope_poem", () -> new Item(ItemSettingsHelper.epicFireProofSettings())),
    SHEEP_BOWEL("sheep_bowel", () -> new Item(ItemSettingsHelper.baseSettings())),
    CLEAN_SHEEP_BOWEL("clean_sheep_bowel", () -> new Item(ItemSettingsHelper.baseSettings())),
    PLAIN_STRING("plain_string", () -> new Item(ItemSettingsHelper.baseSettings())),
    LYRA("lyra", () -> new Item(ItemSettingsHelper.noStackableSettings()));


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
