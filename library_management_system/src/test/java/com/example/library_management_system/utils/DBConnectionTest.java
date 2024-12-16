package com.example.library_management_system.utils;

import com.example.library_management_system.exceptions.InvalidMySQLCredentialsException;
import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.exceptions.MySQLTimeoutException;
//import jdk.internal.org.objectweb.asm.ClassReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class DBConnectionTest {
//    @Mock
//    private Connection mockConnection;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testCreateConnection_Success() throws SQLException, MySQLConnectionException {
        // Mock the DriverManager to return a mock Connection

        Connection mockConnection = mock(Connection.class);

        try(MockedStatic<DriverManager> mockedDriver = mockStatic(DriverManager.class);) {
            mockedDriver.when(()->DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);
        }
        // Call the method
        Connection connection = DBConnection.createConnection();
        assertNotNull(connection);
        mockConnection.close();

    }
    @Test
    void testCreateConnection_InvalidCredentials() throws SQLException {
        // Simulating an invalid credential exception
        SQLException exception = new SQLException("Access denied for user");
        try(MockedStatic<DriverManager> mockedDriver = mockStatic(DriverManager.class);) {
            mockedDriver.when(()->DriverManager.getConnection(anyString(), anyString(), anyString())).thenThrow(exception);
        }
        assertThrows(InvalidMySQLCredentialsException.class, DBConnection::createConnection);
    }
//
//    @Test
//    public void testCreateConnection_Timeout() throws SQLException {
//        // Simulate a timeout exception
//        when(DriverManager.getConnection(anyString(), anyString(), anyString()))
//                .thenThrow(new SQLException("Communications link failure"));
//
//        // Call the method and assert the exception
//        assertThrows(MySQLTimeoutException.class, () -> {
//            DBConnection.createConnection();
//        });
//    }
//
//    @Test
//    public void testRunSQLInitialization_Success() {
//        // Simulating the creation of the SQL file and connection
//        String sqlFilePath = "src/test/resources/test_init.sql";
//        // Mock the method to execute SQL commands from the file
//        DBConnection.runSQLInitialization(sqlFilePath);
//
//        assertTrue(true); // Just a placeholder, you would add specific logic here.
//    }
}