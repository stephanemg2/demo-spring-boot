package com.steph.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConfigurationPropertiesReaderTest {

    @Autowired
    private ConfigurationPropertiesReader configurationPropertiesReader;

    @Test
    void someMethod() {
        Properties props = configurationPropertiesReader.readProperties();
        assertNotNull(props);
    }
}