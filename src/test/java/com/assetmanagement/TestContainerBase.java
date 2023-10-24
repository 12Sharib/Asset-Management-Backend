package com.assetmanagement;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;

public class TestContainerBase implements BeforeAllCallback, AfterAllCallback {
    @Container
    private static final CustomMySqlContainer sqlContainer = new CustomMySqlContainer();


    @DynamicPropertySource
    static void overrideProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlContainer::getUsername);
        registry.add("spring.datasource.password", sqlContainer::getPassword);
    }
    @Override
    public void beforeAll(ExtensionContext extensionContext){
        sqlContainer.withReuse(true);
        sqlContainer.start();
    }

    @Override
    public void afterAll(ExtensionContext context) {
        sqlContainer.stop();
        sqlContainer.close();
    }
}
