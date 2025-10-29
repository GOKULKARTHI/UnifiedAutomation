package com.unifiedautomation.config;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

public class ConfigManager {
    private static ConfigManager instance;
    private Map<String, Object> config;

    private ConfigManager() {
        loadConfig();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadConfig() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config/application.yml")) {
            if (inputStream == null) {
                throw new RuntimeException("Config file not found: config/application.yml");
            }
            config = yaml.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    public String getString(String key) {
        return (String) config.get(key);
    }

    public boolean getBoolean(String key) {
        return (Boolean) config.get(key);
    }

    public int getInt(String key) {
        return (Integer) config.get(key);
    }
}
