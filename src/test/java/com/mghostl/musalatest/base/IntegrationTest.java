package com.mghostl.musalatest.base;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class IntegrationTest implements BaseTest {
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = PostgresContainer.getInstance();

}
