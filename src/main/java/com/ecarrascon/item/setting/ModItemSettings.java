package com.ecarrascon.item.setting;

import com.ecarrascon.OrpheusMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Rarity;

public class ModItemSettings extends FabricItemSettings {

    public static FabricItemSettings base() {
        return new ModItemSettings();
    }

    public static FabricItemSettings noStackable() {
        return new ModItemSettings().maxCount(1);
    }

    public static FabricItemSettings epicFireProof() {
        return new ModItemSettings().maxCount(1).fireproof().rarity(Rarity.EPIC);
    }

    public ModItemSettings() {
        super();
        group(OrpheusMod.ITEM_GROUP);
    }

}
