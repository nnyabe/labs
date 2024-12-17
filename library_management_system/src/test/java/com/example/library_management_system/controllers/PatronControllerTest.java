package com.example.library_management_system.controllers;

import com.example.library_management_system.modles.PatronModel;
import com.example.library_management_system.utils.DBConnection;
import org.junit.jupiter.api.Assertions;
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
    private PatronModel patronModel;

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
        assertEquals(20, patrons.size());
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

        doThrow(new SQLIntegrityConstraintViolationException("Cannot delete or update a parent row: a foreign key constraint fails"))
                .when(preparedStatement).executeUpdate();

        SQLException thrown = assertThrows(SQLException.class, () -> patronController.deleteById(1));
        assertTrue(thrown.getMessage().contains("foreign key constraint fails"));
    }


    @Test
    public void testGetCreateQuery_ReturnsCorrectSQL() {
        // Arrange: Expected SQL query string
        String expectedQuery = "INSERT INTO patrons (username, email) VALUES ( ?, ?)";

        // Act: Call getCreateQuery
        String actualQuery = patronController.getCreateQuery();

        // Assert: Verify the returned query is the same as the expected one
        assertEquals(expectedQuery, actualQuery, "The SQL query should match the expected query.");
    }

    @Test
    public void testGetUpdateQuery_ReturnsCorrectSQL() {
        // Arrange: Expected SQL query string
        String expectedQuery = "UPDATE patrons SET  username = ?, email = ?WHERE id = ?";

        // Act: Call getUpdateQuery
        String actualQuery = patronController.getUpdateQuery();

        // Assert: Verify the returned query is the same as the expected one
        assertEquals(expectedQuery, actualQuery, "The SQL query should match the expected query.");
    }

    @Test
    void testDeleteById() throws SQLException {

        int patronId = 11;
        String deleteQuery = "DELETE FROM patrons WHERE id = ?";

        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(deleteQuery)).thenReturn(mockPreparedStatement);

        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simulate 1 row affected

        PatronController controller = new PatronController();
        boolean result = controller.deleteById(patronId);

        assertFalse(result);
    }

    @Test
    public void testSetUpdateParameters() throws SQLException {
        // Arrange: Create a patron model with sample data
        int patronId = 1;
        String patronUsername = "john_doe";
        String patronEmail = "john.doe@example.com";

        when(patronModel.getUsername()).thenReturn(patronUsername);
        when(patronModel.getEmail()).thenReturn(patronEmail);
        when(patronModel.getId()).thenReturn(patronId);

        // Act: Call the setUpdateParameters method
        patronController.setUpdateParameters(preparedStatement, patronModel);

        // Assert: Verify that the correct methods were called on the PreparedStatement
        verify(preparedStatement, times(1)).setString(1, patronUsername);
        verify(preparedStatement, times(1)).setString(2, patronEmail);
        verify(preparedStatement, times(1)).setInt(3, patronId);
    }
    @Test
    public void testSetCreateParametersWithNullValues() throws SQLException {
        // Arrange: Create a patron model with null values
        String patronUsername = null;
        String patronEmail = null;

        when(patronModel.getUsername()).thenReturn(patronUsername);
        when(patronModel.getEmail()).thenReturn(patronEmail);

        // Act: Call the setCreateParameters method
        patronController.setCreateParameters(preparedStatement, patronModel);

        // Assert: Verify that the correct methods were called on the PreparedStatement with null values
        verify(preparedStatement, times(1)).setString(1, patronUsername);  // Null value
        verify(preparedStatement, times(1)).setString(2, patronEmail);     // Null value
    }

    @Test
    public void testSetCreateParametersThrowsSQLException() throws SQLException {
        // Arrange: Create a patron model with sample data
        String patronUsername = "john_doe";
        String patronEmail = "john.doe@example.com";

        when(patronModel.getUsername()).thenReturn(patronUsername);
        when(patronModel.getEmail()).thenReturn(patronEmail);

        // Simulate SQLException when setString is called
        doThrow(new SQLException("Database error")).when(preparedStatement).setString(anyInt(), anyString());

        // Act & Assert: Verify that an exception is thrown when setCreateParameters is called
        Assertions.assertThrows(SQLException.class, () -> {
            patronController.setCreateParameters(preparedStatement, patronModel);
        });
    }
}
