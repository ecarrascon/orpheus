package com.ecarrascon.orpheus.registry;

import com.ecarrascon.orpheus.Orpheus;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum SoundsRegistry {

    ENTITY_VIPER_AMBIENT("entity.viper.ambient"),
    ENTITY_VIPER_DEATH("entity.viper.death"),
    ENTITY_VIPER_HURT("entity.viper.hurt");

    private final String pathName;
    private final SoundEvent soundEvent;

    SoundsRegistry(String pathName) {
        this.pathName = pathName;
        this.soundEvent = new SoundEvent(new Identifier(Orpheus.MOD_ID, this.pathName));
    }

    public static void registerAll() {
        for (SoundsRegistry value : values()) {
            Registry.register(Registry.SOUND_EVENT, new Identifier(Orpheus.MOD_ID, value.pathName), value.soundEvent);
        }
    }

    public SoundEvent get() {
        return soundEvent;
    }

}