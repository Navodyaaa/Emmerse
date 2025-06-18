package com.emmerseweb.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyResolverUtil {
    public static final String APPLICATION_PROPERTY = "src/main/resources/config.properties";
    private static PropertyResolverUtil propertyResolverUtil;
    private final Properties properties;

    // Constructor to load properties file
    private PropertyResolverUtil(String filePath) {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + filePath);
        }
    }

    // Method to get property value
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Method to get property value with a default
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static PropertyResolverUtil getApplicationProperties() {
        if (Objects.isNull(propertyResolverUtil)) {
            propertyResolverUtil = new PropertyResolverUtil(APPLICATION_PROPERTY);
        }
        return propertyResolverUtil;
    }
}