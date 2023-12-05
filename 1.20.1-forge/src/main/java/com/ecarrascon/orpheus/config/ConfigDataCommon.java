package com.ecarrascon.orpheus.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigDataCommon {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> ORPHEUS_LYRE_POWER;


    static {
        BUILDER.push("Configs for Orpheus Mod");

        ORPHEUS_LYRE_POWER = BUILDER.comment("If you input \"keep\" it will give you the KeepInventory effect. " +
                        "If you input \"protect\" it will grant you immunity to arrows. If you input the word \"both\", " +
                        "you will have both effects.")
                .define("Orpheus Lyre Power", "keep");


        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
