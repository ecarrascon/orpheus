package com.ecarrascon.orpheus.registry;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.item.*;
import com.ecarrascon.orpheus.item.setting.ItemSettingsHelper;
import com.google.common.collect.Sets;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ItemsRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Orpheus.MOD_ID);
    public static LinkedHashSet<RegistryObject<Item>> ORPHEUS_TAB_ITEMS = Sets.newLinkedHashSet();


    // Block items
    public static final RegistryObject<Item> EPIPHANY_TABLE = registerWithTab("epiphany_table",
            () -> new BlockItem(BlocksRegistry.EPIPHANY_TABLE.get(), ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> MOLY_HERB = registerWithTab("moly_herb",
            () -> new BlockItem(BlocksRegistry.MOLY_HERB.get(), ItemSettingsHelper.stackableItem()));

    public static final RegistryObject<Item> MYTHOS_BLOCK = registerWithTab("mythos_block",
            () -> new BlockItem(BlocksRegistry.MYTHOS_BLOCK.get(), ItemSettingsHelper.stackableItem()));

    public static final RegistryObject<Item> PEGASUS_FEATHERS_BLOCK = registerWithTab("pegasus_feathers_block",
            () -> new BlockItem(BlocksRegistry.PEGASUS_FEATHERS_BLOCK.get(), ItemSettingsHelper.stackableItem()));

    public static final RegistryObject<Item> TEARS_OF_HADES_BLOCK = registerWithTab("tears_of_hades_block",
            () -> new BlockItem(BlocksRegistry.TEARS_OF_HADES_BLOCK.get(), ItemSettingsHelper.stackableItem()));

    public static final RegistryObject<Item> TEARS_OF_HADES_ORE = registerWithTab("tears_of_hades_ore",
            () -> new BlockItem(BlocksRegistry.TEARS_OF_HADES_ORE.get(), ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> DEEPSLATE_TEARS_OF_HADES_ORE = registerWithTab("deepslate_tears_of_hades_ore",
            () -> new BlockItem(BlocksRegistry.DEEPSLATE_TEARS_OF_HADES_ORE.get(), ItemSettingsHelper.stackableItem()));


    // Items
    public static final RegistryObject<Item> NECTAR_SEED = registerWithTab("nectar_seed",
            () -> new ItemNameBlockItem(BlocksRegistry.NECTAR_CROP.get(), ItemSettingsHelper.stackableItem()));

    public static final RegistryObject<Item> BROTOI_NECTAR = registerWithTab("brotoi_nectar",
            () -> new HoneyBottleItem(ItemSettingsHelper.noStackableItem()
                    .food(new FoodProperties.Builder().nutrition(5).saturationMod(19.4f).build())));
    public static final RegistryObject<Item> BROTOI_PALLADIUM = registerWithTab("brotoi_palladium",
            () -> new BrotoiPalladiumItem(ItemSettingsHelper.noStackableItem()));
    public static final RegistryObject<Item> COOKED_COW_GUT = registerWithTab("cooked_cow_gut",
            () -> new Item(ItemSettingsHelper.noStackableItem()
                    .food(new FoodProperties.Builder().nutrition(8).saturationMod(0.9f).meat().build())));
    public static final RegistryObject<Item> PLAIN_STRING = registerWithTab("plain_string",
            () -> new Item(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> TEARS_OF_HADES = registerWithTab("tears_of_hades",
            () -> new Item(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> ORPHEUS_LYRE = registerWithTab("orpheus_lyre",
            () -> new Item(ItemSettingsHelper.epicItem()));
    public static final RegistryObject<Item> CALLIOPE_POEM_FRAGMENT = registerWithTab("calliope_poem_fragment",
            () -> new Item(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> CALLIOPES_LOVE = registerWithTab("calliopes_love",
            () -> new Item(ItemSettingsHelper.epicItem()));
    public static final RegistryObject<Item> APOLLOS_SON = registerWithTab("apollos_son",
            () -> new Item(ItemSettingsHelper.epicItem()));
    public static final RegistryObject<Item> HOMERS_THE_ILIAD_SCROLL_FRAGMENT = registerWithTab("homers_the_iliad_scroll_fragment",
            () -> new Item(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> HOMERS_THE_ODYSSEY_SCROLL_FRAGMENT = registerWithTab("homers_the_odyssey_scroll_fragment",
            () -> new Item(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> PLATOS_REPUBLIC_SCROLL_FRAGMENT = registerWithTab("platos_republic_scroll_fragment",
            () -> new Item(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> ARISTOTLES_NICOMACHEAN_ETHICS_SCROLL_FRAGMENT = registerWithTab("aristotles_nicomachean_ethics_scroll_fragment",
            () -> new Item(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> PEGASUS_FEATHER = registerWithTab("pegasus_feather",
            () -> new Item(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> THUCYDIDES_PELOPONNESIAN_WAR_SCROLL_FRAGMENT = registerWithTab("thucydides_peloponnesian_war_scroll_fragment",
            () -> new Item(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> HERACLITIAN_FLUX_FRAGMENT = registerWithTab("heraclitian_flux_fragment",
            () -> new Item(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> STOIC_MEDITATIVE_STONE = registerWithTab("stoic_meditative_stone",
            () -> new Item(ItemSettingsHelper.stackableItem()));

    public static final RegistryObject<Item> PALLADIUM_WOODEN_FRAGMENT = registerWithTab("palladium_wooden_fragment",
            () -> new Item(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> COW_GUT = registerWithTab("cow_gut",
            () -> new Item(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> CLEAN_COW_GUT = registerWithTab("clean_cow_gut",
            () -> new Item(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> HEPHAESTUS_ARMOR_FRAGMENT = registerWithTab("hephaestus_armor_fragment",
            () -> new Item(ItemSettingsHelper.stackableItem()));

    public static final RegistryObject<Item> HEPHAESTUS_HELMET = registerWithTab("hephaestus_helmet",
            () -> new ArmorItem(OrpheusArmorMaterials.HEPHAESTUS, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> HEPHAESTUS_CHESTPLATE = registerWithTab("hephaestus_chestplate",
            () -> new HephaestusArmorItem(OrpheusArmorMaterials.HEPHAESTUS, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> HEPHAESTUS_LEGGINGS = registerWithTab("hephaestus_leggings",
            () -> new ArmorItem(OrpheusArmorMaterials.HEPHAESTUS, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> HEPHAESTUS_BOOTS = registerWithTab("hephaestus_boots",
            () -> new ArmorItem(OrpheusArmorMaterials.HEPHAESTUS, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> HELLENIC_CODEX = registerWithTab("hellenic_codex",
            () -> new Item(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> PANDORAS_PITHOS = registerWithTab("pandoras_pithos",
            () -> new PandorasPithosItem(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> TEARS_BOW = registerWithTab("tears_bow",
            () -> new TearsBowItem(ItemSettingsHelper.noStackableItem().durability(192)));
    public static final RegistryObject<Item> HERACLITIAN_FLUX_POTION = registerWithTab("heraclitian_flux_potion",
            () -> new RandomPotionEffectItem(ItemSettingsHelper.stackableItem()));
    public static final RegistryObject<Item> LYRE = registerWithTab("lyre",
            () -> new Item(ItemSettingsHelper.noStackableItem()));


    public static RegistryObject<Item> registerWithTab(final String name, final Supplier<Item> supplier) {
        RegistryObject<Item> item = ITEMS.register(name, supplier);
        ORPHEUS_TAB_ITEMS.add(item);
        return item;
    }

}
