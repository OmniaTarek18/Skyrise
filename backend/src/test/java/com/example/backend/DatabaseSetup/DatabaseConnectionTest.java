package com.example.backend.DatabaseSetup;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDatabaseConnection() {
        assertNotNull(dataSource, "DataSource should not be null, check if database configuration is correct");

        // Try to establish a connection to the database
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "Should be able to connect to the database");
            assertTrue(connection.isValid(1), "Database connection should be valid");
        } catch (SQLException e) {
            // If there's an error, fail the test and print the error
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
}
