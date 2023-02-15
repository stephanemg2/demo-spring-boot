package com.steph.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.core.env.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ConfigurationPropertiesReader {

    public static final String CONFIGURATION_PROPERTIES = "configuration.properties";

    @Autowired
    private Environment env;

    public Properties readProperties() {
        Properties props = asProperties(CONFIGURATION_PROPERTIES);
        return props;
    }

    private Properties asProperties(String fileName) {
        String PREFIX = "CONSTANTE";
        Predicate<PropertySource> isEnumerablePropertySource = propertySource -> propertySource instanceof EnumerablePropertySource;
        Properties props = new Properties();
        StreamSupport.stream(
                        ((AbstractEnvironment) env).getPropertySources().spliterator(), false)
                .filter(isEnumerablePropertySource)
                .map(propertySource -> (EnumerablePropertySource) propertySource)
                .map(enumerablePropertySource -> Arrays.stream(enumerablePropertySource.getPropertyNames())
                        .filter(p -> (enumerablePropertySource.getProperty(p) instanceof String))
                        .filter(p -> ((String) enumerablePropertySource.getProperty(p)).startsWith(PREFIX))
                        .collect(Collectors.toMap(Function.identity(), p -> props.setProperty(p, env.getProperty(p)))));
//    StreamSupport.stream(
//                    ((AbstractEnvironment) env).getPropertySources().spliterator(), false)
//            .filter(isEnumerablePropertySource)
//            .forEach(propertySource -> {
//              EnumerablePropertySource enumerablePropertySource = (EnumerablePropertySource) propertySource;
//              Map<String, Object> propertyOverrides = Arrays.stream(enumerablePropertySource.getPropertyNames())
//                      .filter(p -> (enumerablePropertySource.getProperty(p) instanceof String))
//                      .filter(p -> ((String) enumerablePropertySource.getProperty(p)).startsWith(PREFIX))
//                      .collect(Collectors.toMap(Function.identity(), p -> props.setProperty(p, env.getProperty(p))));
//            });
        return props;
    }
}