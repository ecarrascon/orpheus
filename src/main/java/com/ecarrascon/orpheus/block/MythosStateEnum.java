package com.ecarrascon.orpheus.block;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum MythosStateEnum implements StringRepresentable
{
    NETHER("nether"),
    NETHER_ACTIVE("nether_active"),
    OVERWORLD("overworld"),
    OVERWORLD_ACTIVE("overworld_active");

    private final String name;

    MythosStateEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }
}

