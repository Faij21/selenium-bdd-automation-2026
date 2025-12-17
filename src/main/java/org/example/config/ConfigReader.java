package org.example.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.example.utils.Constants.*;


public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private Properties properties;
    private String environment;

    public ConfigReader() {
        String env = System.getProperty("env", QA_ENVIRONMENT);
        this.environment = env;
        loadProperties();
    }

    private void loadProperties() {
        properties = new Properties();
        String configFile = "src/main/resources/" + environment + ".properties";
        
        try (FileInputStream fis = new FileInputStream(configFile)) {
            properties.load(fis);
            logger.info("Configuration loaded successfully from: {}", configFile);
        } catch (IOException e) {
            logger.error("Failed to load configuration file: {}", configFile, e);
            throw new RuntimeException("Configuration file not found: " + configFile);
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property '{}' not found in configuration", key);
        }
        return value;
    }

    public String getBrowser() {
        return getProperty("browser");
    }

    public String getUrl() {
        return getProperty("url");
    }

    public String getHeadless() {
        return getProperty("headless");
    }

    public int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    public int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }

    public int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout"));
    }

    public String getEnvironment() {
        return environment;
    }
}
