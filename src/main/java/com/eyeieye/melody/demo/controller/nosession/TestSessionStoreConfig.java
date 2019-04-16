package com.eyeieye.melody.demo.controller.nosession;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class TestSessionStoreConfig {
    @Bean
    public TestSessionStore getTestSessionStore(){

        TestSessionStore testSessionStore = new TestSessionStore();
        Set<String> stringSet = new HashSet<>();
        stringSet.add("_test");
        testSessionStore.setAttributeNames(stringSet);
        return testSessionStore;

    }
}
