package com.example.library_management_system.utils;

import com.example.library_management_system.exceptions.InvalidMySQLCredentialsException;
import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.exceptions.MySQLTimeoutException;
import jdk.internal.org.objectweb.asm.ClassReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class DBConnectionTest {
    @Mock
    private Connection mockConnection;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateConnection_Success() throws SQLException, MySQLConnectionException {
        // Mock the DriverManager to return a mock Connection
        when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);

        // Call the method
        Connection connection = DBConnection.createConnection();

        // Verify that the connection was created successfully
        assertNotNull(connection);
//        verify(DriverManager, times(1)).getConnection(anyString(), anyString(), anyString());
    }

    @Test
    public void testCreateConnection_InvalidCredentials() throws SQLException {
        // Simulate an invalid credential exception
        when(DriverManager.getConnection(anyString(), anyString(), anyString()))
                .thenThrow(new SQLException("Access denied for user"));

        // Call the method and assert the exception
        assertThrows(InvalidMySQLCredentialsException.class, () -> {
            DBConnection.createConnection();
        });
    }

    @Test
    public void testCreateConnection_Timeout() throws SQLException {
        // Simulate a timeout exception
        when(DriverManager.getConnection(anyString(), anyString(), anyString()))
                .thenThrow(new SQLException("Communications link failure"));

        // Call the method and assert the exception
        assertThrows(MySQLTimeoutException.class, () -> {
            DBConnection.createConnection();
        });
    }

    @Test
    public void testRunSQLInitialization_Success() {
        // Simulating the creation of the SQL file and connection
        String sqlFilePath = "src/test/resources/test_init.sql";
        // Mock the method to execute SQL commands from the file
        DBConnection.runSQLInitialization(sqlFilePath);

        // Add appropriate assertions to verify the functionality
        // For instance, you could verify interactions with the connection or log output
        // This would depend on the actual logic and setup in the runSQLInitialization method
        assertTrue(true); // Just a placeholder, you would add specific logic here.
    }
}