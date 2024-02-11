package com.ecarrascon.orpheus.registry;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.item.*;
import com.ecarrascon.orpheus.item.setting.ItemSettingsHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;


public enum ItemsRegistry {

    // Block items
    PEGASUS_FEATHERS_BLOCK("pegasus_feathers_block", () -> new
            BlockItem(BlocksRegistry.PEGASUS_FEATHERS_BLOCK.get(), ItemSettingsHelper.baseSettings())),
    MYTHOS_BLOCK("mythos_block", () -> new
            BlockItem(BlocksRegistry.MYTHOS_BLOCK.get(), ItemSettingsHelper.baseSettings())),
    EPIPHANY_TABLE("epiphany_table", () -> new BlockItem(BlocksRegistry.EPIPHANY_TABLE.get(), ItemSettingsHelper.baseSettings())),
    MOLY_HERB("moly_herb", () -> new BlockItem(BlocksRegistry.MOLY_HERB.get(), ItemSettingsHelper.baseSettings())),
    TEARS_OF_HADES_BLOCK("tears_of_hades_block", () -> new BlockItem(BlocksRegistry.TEARS_OF_HADES_BLOCK.get(), ItemSettingsHelper.baseSettings())),
    TEARS_OF_HADES_ORE("tears_of_hades_ore", () -> new BlockItem(BlocksRegistry.TEARS_OF_HADES_ORE.get(), ItemSettingsHelper.baseSettings())),
    DEEPSLATE_TEARS_OF_HADES_ORE("deepslate_tears_of_hades_ore", () -> new BlockItem(BlocksRegistry.DEEPSLATE_TEARS_OF_HADES_ORE.get(), ItemSettingsHelper.baseSettings())),


    // Items
    TEARS_OF_HADES("tears_of_hades", () -> new Item(ItemSettingsHelper.baseSettings())),
    TEARS_BOW("tears_bow", () -> new TearsBow(ItemSettingsHelper.noStackableSettings().maxDamage(192))),

    ORPHEUS_LYRE("orpheus_lyre", () -> new Item(ItemSettingsHelper.epicFireProofSettings())),
    CALLIOPE_POEM_FRAGMENT("calliope_poem_fragment", () -> new Item(ItemSettingsHelper.baseSettings())),
    CALLIOPES_LOVE("calliopes_love", () -> new Item(ItemSettingsHelper.epicFireProofSettings())),
    APOLLOS_SON("apollos_son", () -> new Item(ItemSettingsHelper.epicFireProofSettings())),
    HOMERS_THE_ILIAD_SCROLL_FRAGMENT("homers_the_iliad_scroll_fragment", () -> new Item(ItemSettingsHelper.baseSettings())),
    HOMERS_THE_ODYSSEY_SCROLL_FRAGMENT("homers_the_odyssey_scroll_fragment", () -> new Item(ItemSettingsHelper.baseSettings())),
    PLATOS_REPUBLIC_SCROLL_FRAGMENT("platos_republic_scroll_fragment", () -> new Item(ItemSettingsHelper.baseSettings())),
    ARISTOTLES_NICOMACHEAN_ETHICS_SCROLL_FRAGMENT("aristotles_nicomachean_ethics_scroll_fragment", () -> new
            Item(ItemSettingsHelper.baseSettings())),
    PEGASUS_FEATHER("pegasus_feather", () -> new Item(ItemSettingsHelper.baseSettings())),
    THUCYDIDES_PELOPONNESIAN_WAR_SCROLL_FRAGMENT("thucydides_peloponnesian_war_scroll_fragment", () -> new
            Item(ItemSettingsHelper.baseSettings())),
    HERACLITIAN_FLUX_FRAGMENT("heraclitian_flux_fragment", () -> new Item(ItemSettingsHelper.baseSettings())),
    STOIC_MEDITATIVE_STONE("stoic_meditative_stone", () -> new Item(ItemSettingsHelper.baseSettings())),
    PANDORAS_PITHOS("pandoras_pithos", () -> new PandorasPithos(ItemSettingsHelper.baseSettings())),
    PALLADIUM_WOODEN_FRAGMENT("palladium_wooden_fragment", () -> new Item(ItemSettingsHelper.baseSettings())),
    BROTOI_PALLADIUM("brotoi_palladium", () -> new BrotoiPalladium(ItemSettingsHelper.noStackableSettings())),

    COW_GUT("cow_gut", () -> new Item(ItemSettingsHelper.baseSettings())),
    CLEAN_COW_GUT("clean_cow_gut", () -> new Item(ItemSettingsHelper.baseSettings())),
    COOKED_COW_GUT("cooked_cow_gut", () -> new Item(ItemSettingsHelper.baseSettings()
            .food(new FoodComponent.Builder().hunger(8).saturationModifier(0.9f).meat().build()))),
    PLAIN_STRING("plain_string", () -> new Item(ItemSettingsHelper.baseSettings())),
    NECTAR_SEED("nectar_seed", () -> new AliasedBlockItem(BlocksRegistry.NECTAR_CROP.get(), ItemSettingsHelper.baseSettings())),
    BROTOI_NECTAR("brotoi_nectar", () -> new HoneyBottleItem(ItemSettingsHelper.baseSettings()
            .food(new FoodComponent.Builder().hunger(5).saturationModifier(19.4f).build()))),
    HEPHAESTUS_ARMOR_FRAGMENT("hephaestus_armor_fragment", () -> new Item(ItemSettingsHelper.baseSettings())),
    HEPHAESTUS_HELMET("hephaestus_helmet", () -> new ArmorItem(OrpheusArmorMaterials.HEPHAESTUS, EquipmentSlot.HEAD,
            ItemSettingsHelper.baseSettings())),
    HEPHAESTUS_CHESTPLATE("hephaestus_chestplate", () -> new ArmorItem(OrpheusArmorMaterials.HEPHAESTUS, EquipmentSlot.CHEST,
            ItemSettingsHelper.baseSettings())),
    HEPHAESTUS_LEGGINGS("hephaestus_leggings", () -> new ArmorItem(OrpheusArmorMaterials.HEPHAESTUS, EquipmentSlot.LEGS,
            ItemSettingsHelper.baseSettings())),
    HEPHAESTUS_BOOTS("hephaestus_boots", () -> new FullSetEffectHephaestusItem(OrpheusArmorMaterials.HEPHAESTUS, EquipmentSlot.FEET,
            ItemSettingsHelper.baseSettings())),
    HERACLITIAN_FLUX_POTION("heraclitian_flux_potion", () -> new RandomPotionEffectItem(ItemSettingsHelper.baseSettings())),
    HELLENIC_CODEX("hellenic_codex", () -> new Item(ItemSettingsHelper.baseSettings())),

    LYRE("lyre", () -> new Item(ItemSettingsHelper.noStackableSettings()));

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
            Registry.register(Registry.ITEM, new Identifier(Orpheus.MOD_ID, value.pathName), value.get());
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
