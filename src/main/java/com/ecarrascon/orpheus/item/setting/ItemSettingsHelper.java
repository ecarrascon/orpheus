package com.ecarrascon.orpheus.item.setting;

import com.ecarrascon.orpheus.OrpheusMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Rarity;

public class ItemSettingsHelper {

    public static FabricItemSettings baseSettings() {
        return new FabricItemSettings().group(OrpheusMod.ITEM_GROUP);
    }

    public static FabricItemSettings noStackableSettings() {
        return baseSettings().maxCount(1);
    }

    public static FabricItemSettings epicFireProofSettings() {
        return noStackableSettings().fireproof().rarity(Rarity.EPIC);
    }
}
