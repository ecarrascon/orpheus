package com.ecarrascon.orpheus.registry;

import com.ecarrascon.orpheus.Orpheus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundsRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Orpheus.MOD_ID);

    public static final RegistryObject<SoundEvent> ENTITY_VIPER_AMBIENT = registerSoundEvents("entity.viper.ambient");
    public static final RegistryObject<SoundEvent> ENTITY_VIPER_DEATH = registerSoundEvents("entity.viper.death");
    public static final RegistryObject<SoundEvent> ENTITY_VIPER_HURT = registerSoundEvents("entity.viper.hurt");





    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Orpheus.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
