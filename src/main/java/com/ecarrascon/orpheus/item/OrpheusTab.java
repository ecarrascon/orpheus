package com.ecarrascon.orpheus.item;

import com.ecarrascon.orpheus.Orpheus;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class OrpheusTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Orpheus.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ORPHEUS_TAB = CREATIVE_MODE_TAB.register(Orpheus.MOD_ID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.orpheus.main"))
                    .icon(() -> new ItemStack(OrpheusItems.PLAIN_STRING.get()))
                    .displayItems((parameters, output) -> OrpheusItems.ORPHEUS_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
                    .build());

}
