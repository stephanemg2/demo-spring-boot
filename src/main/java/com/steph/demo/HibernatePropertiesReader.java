package com.steph.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;
@Component
public class HibernatePropertiesReader {

  public static final String HIBERNATE_PROPERTIES = "hibernate.properties";

  @Autowired
  private Environment env;

  public Properties readProperties() {
    Properties props =  asProperties(HIBERNATE_PROPERTIES);
    return props;
  }

  private Properties asProperties(String fileName) {
    Predicate<PropertySource> isConfigurationPropertySource = ps -> ps instanceof ConfigurationPropertySource;
    return StreamSupport.stream(
      ((AbstractEnvironment) env).getPropertySources().spliterator(), false)
      .filter(isConfigurationPropertySource)
      .map(ps -> (ResourcePropertySource) ps)
      .filter(rps -> rps.getName().contains(fileName))
      .collect(
        Properties::new,
        (props, rps) -> props.putAll(rps.getSource()),
        Properties::putAll);
  }
}