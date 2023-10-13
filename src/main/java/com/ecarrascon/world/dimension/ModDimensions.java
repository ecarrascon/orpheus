package com.ecarrascon.world.dimension;

import com.ecarrascon.OrpheusMod;
import com.ecarrascon.registry.ItemsRegistry;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {
    public static final RegistryKey<World> OLYMPUS_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY,
            new Identifier(OrpheusMod.MOD_ID, "olympus"));
    public static final RegistryKey<DimensionType> OLYMPUS_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            OLYMPUS_DIMENSION_KEY.getValue());

    public static void register() {
        OrpheusMod.LOGGER.debug("Registering Olympus dimension");

        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.WHITE_CONCRETE)
                .destDimID(OLYMPUS_DIMENSION_KEY.getValue())
                .tintColor(255,255,255)
                .lightWithItem(ItemsRegistry.ORPHEUS_LYRA.get())
                .flatPortal()
                .registerPortal();
    }
}
