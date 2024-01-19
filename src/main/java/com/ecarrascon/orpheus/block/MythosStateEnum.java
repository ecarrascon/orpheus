package com.ecarrascon.orpheus.block;

import net.minecraft.util.StringIdentifiable;

public enum MythosStateEnum implements StringIdentifiable
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

    @Override
    public String asString() {
        return this.name;
    }
}

