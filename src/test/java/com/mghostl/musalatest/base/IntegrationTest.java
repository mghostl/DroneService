package com.mghostl.musalatest.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public abstract class IntegrationTest implements BaseTest {
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = PostgresContainer.getInstance();

    @Autowired
    List<JpaRepository<?, ?>> repositories;

    @AfterEach
    public void clean() {
        repositories.forEach(JpaRepository::deleteAllInBatch);
    }

}
