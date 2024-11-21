package com.marek.application;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles({"int", "local"})
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest {
    private static final PostgreSQLContainer DATABASE = new PostgreSQLContainer<>("postgres:11.1")
            .withInitScript("init.sql");
    private static final GenericContainer<?> CACHE = new GenericContainer<>(DockerImageName.parse("eqalpha/keydb:x86_64_v5.3.3"))
            .withExposedPorts(6379);


    static {
        CACHE.start();
        DATABASE.start();
    }

    @DynamicPropertySource
    static void registerProperties(org.springframework.test.context.DynamicPropertyRegistry registry) {
        DATABASE.start();
        CACHE.start();

        registry.add("spring.datasource.url", DATABASE::getJdbcUrl);
        registry.add("spring.datasource.username", DATABASE::getUsername);
        registry.add("spring.datasource.password", DATABASE::getPassword);
        registry.add("spring.redis.host", CACHE::getHost);
        registry.add("spring.redis.port", () -> CACHE.getMappedPort(6379));
    }
}
