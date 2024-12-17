package com.example.library_management_system.controllers;

import com.example.library_management_system.modles.PatronModel;
import com.example.library_management_system.utils.DBConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PatronControllerTest {

    @InjectMocks
    private PatronController patronController;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
    }

//    @ParameterizedTest
//    @CsvSource({
//            "1, john_oie, john11cz@example.com",
//            "2, jane_dpe, janawe1@example.com",
//            "3, alice_sm_it, alicewr.1smith@example.com"
//    })
//    public void testCreatePatron(int id, String username, String email) throws SQLException {
//        // Create the PatronModel with the provided parameters
//        PatronModel patron = new PatronModel(id, username, email, null, null);
//
//        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(1);
//
//        boolean result = patronController.createOne(patron);
//
//        assertTrue(result);
//
//    }
//


    @Test
    public void testGetAllPatrons() throws SQLException {
        // Mocking the ResultSet for getAll() method
        when(connection.createStatement()).thenReturn(mock(Statement.class));
        when(connection.createStatement().executeQuery(anyString())).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("username")).thenReturn("john_doe");
        when(resultSet.getString("email")).thenReturn("john@example.com");

        List<PatronModel> patrons = patronController.getAll();

        assertNotNull(patrons);
        assertEquals(21, patrons.size());
        assertEquals("john_doe", patrons.getFirst().getUsername());
    }

    @Test
    public void testGetPatronById() throws SQLException {

        PatronModel patron = new PatronModel(1, "john_doe", "john@example.com", null, null);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("username")).thenReturn("john_doe");
        when(resultSet.getString("email")).thenReturn("john@example.com");

        PatronModel result = patronController.getById(1);

        assertNotNull(result);
        assertEquals("john_doe", result.getUsername());
    }

//    @Test
//    public void testUpdatePatron() throws SQLException {
//        // Create a PatronModel instance to test with
//        PatronModel patron = new PatronModel(1, "john_doe", "john.doe@example.com", null, null);
//
//        // Mock the connection and preparedStatement behavior
//        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(1);  // Simulate a successful update
//
//        // Mock the behavior of setting the parameters
//        doNothing().when(preparedStatement).setString(1, patron.getUsername());  // Username at position 1
//        doNothing().when(preparedStatement).setString(2, patron.getEmail());     // Email at position 2
//        doNothing().when(preparedStatement).setInt(3, patron.getId());           // Patron ID at position 3
//
//        // Call the updateById method to test the functionality
//        boolean result = patronController.updateById(patron);
//
//        // Assert that the update was successful
//        assertTrue(result);
//
//
//    }
    @Test
    public void testDeletePatronWithForeignKeyViolation() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setInt(1, 1);  // Set ID to 1

        // Simulate the foreign key constraint violation by throwing the exception
        doThrow(new SQLIntegrityConstraintViolationException("Cannot delete or update a parent row: a foreign key constraint fails"))
                .when(preparedStatement).executeUpdate();

        // Act and Assert: Ensure the exception is thrown and handle it accordingly
        SQLException thrown = assertThrows(SQLException.class, () -> patronController.deleteById(1));
        assertTrue(thrown.getMessage().contains("foreign key constraint fails"));
    }


}
