package com.steph.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HibernatePropertiesReaderTest {

    @Autowired
    private HibernatePropertiesReader hibernatePropertiesReader;

    @Test
    void someMethod() {
        Properties props = hibernatePropertiesReader.readProperties();
        assertNotNull(props);
    }
}