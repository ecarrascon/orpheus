package com.ecarrascon.orpheus.world.dimension;

import com.ecarrascon.orpheus.Orpheus;
import com.ecarrascon.orpheus.registry.BlocksRegistry;
import com.ecarrascon.orpheus.registry.ItemsRegistry;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {
    public static final RegistryKey<World> OLYMPUS_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY,
            new Identifier(Orpheus.MOD_ID, "olympus"));
    public static final RegistryKey<DimensionType> OLYMPUS_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            OLYMPUS_DIMENSION_KEY.getValue());

    public static void register() {
        Orpheus.LOGGER.debug("Registering Olympus dimension");

        CustomPortalBuilder.beginPortal()
                .frameBlock(BlocksRegistry.MYTHOS_PORTALITE_BLOCK.get())
                .destDimID(OLYMPUS_DIMENSION_KEY.getValue())
                .tintColor(204,255,243)
                .lightWithItem(ItemsRegistry.LYRE.get())
                .flatPortal()
                .registerPortal();
    }
}
