package com.ecarrascon.orpheus.registry;

import com.ecarrascon.orpheus.Orpheus;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Orpheus.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ORPHEUS_TAB = CREATIVE_MODE_TAB.register(Orpheus.MOD_ID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.orpheus.main"))
                    .icon(() -> new ItemStack(ItemsRegistry.ORPHEUS_LYRE.get()))
                    .displayItems((parameters, output) -> ItemsRegistry.ORPHEUS_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
                    .build());

}
