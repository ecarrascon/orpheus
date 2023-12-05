package com.ecarrascon.orpheus;

import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;

public class ConfigData {
    private static final File CONFIG_DATA_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "orpheus-config.json");

    private String orpheusLyrePower = "keep";

    public ConfigData() {
    }

    public static ConfigData init() {
        ConfigData configuration = new ConfigData();
        if (!CONFIG_DATA_FILE.exists()) {
            save(configuration);
        }

        try (Reader reader = Files.newBufferedReader(CONFIG_DATA_FILE.toPath())) {
            configuration = new GsonBuilder().setPrettyPrinting().create().fromJson(reader, ConfigData.class);
        } catch (IOException e) {
            Orpheus.LOGGER.error("Failed to load the configuration file. Possible issues with the file format or content. Using default configuration. Details: {}", e.getMessage(), e);
        }

        return configuration;
    }

    public static void save(ConfigData config) {
        try (Writer writer = Files.newBufferedWriter(CONFIG_DATA_FILE.toPath())) {
            new GsonBuilder().setPrettyPrinting().create().toJson(config, writer);
        } catch (IOException e) {
            Orpheus.LOGGER.error("Error while trying to save configuration file. Details: {}", e.getMessage(), e);
        }
    }

    public String getOrpheusLyrePower() {
        return orpheusLyrePower;
    }


}
