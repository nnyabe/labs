package com.example.library_management_system.controllers;

import com.example.library_management_system.modles.AdminModel;
import com.example.library_management_system.modles.Enums;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class AdminControllerTest {

    private AdminController adminController;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        adminController = new AdminController();
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        connection = mock(Connection.class);
    }

    @Test
    void testMapRowToModel() throws SQLException {
        // Mock ResultSet data
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("username")).thenReturn("admin");
        when(resultSet.getString("email")).thenReturn("admin@example.com");
        when(resultSet.getString("password")).thenReturn("password123");
        when(resultSet.getString("role")).thenReturn("ADMIN");
        when(resultSet.getDate("created_at")).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getDate("updated_at")).thenReturn(new Date(System.currentTimeMillis()));

        AdminModel admin = adminController.mapRowToModel(resultSet);

        // Assert mapping correctness
        assertNotNull(admin);
        assertEquals(1, admin.getId());
        assertEquals("admin", admin.getUsername());
        assertEquals("admin@example.com", admin.getEmail());
        assertEquals("password123", admin.getPassword());
        assertEquals(Enums.Roles.ADMIN, admin.getRole());
    }

    @Test
    void testSetUpdateParameters() throws SQLException {
        AdminModel admin = new AdminModel(1, "updatedAdmin", "updated@test.com", null, null, Enums.Roles.ADMIN, "newPassword");

        adminController.setUpdateParameters(preparedStatement, admin);

        // Verify the parameters were set correctly
        verify(preparedStatement).setString(1, "updatedAdmin");
        verify(preparedStatement).setString(2, "updated@test.com");
        verify(preparedStatement).setString(3, "ADMIN");
        verify(preparedStatement).setString(4, "newPassword");
        verify(preparedStatement).setInt(5, 1);
    }

    @Test
    void testSetCreateParameters() throws SQLException {
        AdminModel admin = new AdminModel(0, "newAdmin", "new@test.com", null, null, Enums.Roles.ADMIN, "newPassword");

        adminController.setCreateParameters(preparedStatement, admin);

        // Verify the parameters were set correctly
        verify(preparedStatement).setString(1, "newAdmin");
        verify(preparedStatement).setString(2, "new@test.com");
        verify(preparedStatement).setString(3, "ADMIN");
        verify(preparedStatement).setString(4, "newPassword");
    }

    @Test
    void testGetTableName() {
        assertEquals("admins", adminController.getTableName());
    }

    @Test
    void testGetUpdateQuery() {
        String expectedQuery = "UPDATE admins SET  username = ?, email = ?, role = ? , password = ? WHERE id = ?";
        assertEquals(expectedQuery, adminController.getUpdateQuery());
    }

    @Test
    void testGetCreateQuery() {
        String expectedQuery = "INSERT INTO patrons (username, email, role, password) VALUES ( ?, ?, ?, ?)";
        assertEquals(expectedQuery, adminController.getCreateQuery());
    }

//    @ParameterizedTest
//    @CsvSource({
//            "1, john_doe, john.doe@example.com, password123, ADMIN",
//            "2, jane_smith, jane.smith@example.com, password456, ADMIN",
//            "3, alice_jones, alice.jones@example.com, password789, ADMIN"
//    })
//    void testCreateOne(int id, String username, String email, String password, String role) throws SQLException {
//        // Create AdminModel with the parameters
//        AdminModel admin = new AdminModel(id, username, email, null, null, Enums.Roles.ADMIN, password);
//
//        // Mocking the prepared statement and its behavior
//        when(connection.prepareStatement(adminController.getCreateQuery(), Statement.RETURN_GENERATED_KEYS))
//                .thenReturn(preparedStatement);
//        doNothing().when(preparedStatement).setString(1, username);
//        doNothing().when(preparedStatement).setString(2, email);
//        doNothing().when(preparedStatement).setString(3, role);
//        doNothing().when(preparedStatement).setString(4, password);
//
//        when(preparedStatement.executeUpdate()).thenReturn(1); // Mocking successful execution
//
//        // Run the createOne method
//        boolean result = adminController.createOne(admin);
//
//        // Verify the result is true (indicating success)
//        assertTrue(result);
//
//        // Verify that the `setCreateParameters` method is called with the correct parameters
//        verify(preparedStatement).setString(1, username);
//        verify(preparedStatement).setString(2, email);
//        verify(preparedStatement).setString(3, role);
//        verify(preparedStatement).setString(4, password);
//        verify(preparedStatement).executeUpdate(); // Ensure that executeUpdate is called
//    }


    @Test
    void testGetAll() throws SQLException {
        when(connection.prepareStatement("SELECT * FROM admins")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);  // Mock one row in the ResultSet
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("username")).thenReturn("admin");
        when(resultSet.getString("email")).thenReturn("admin@test.com");
        when(resultSet.getString("password")).thenReturn("password123");
        when(resultSet.getString("role")).thenReturn("ADMIN");

        List<AdminModel> admins = adminController.getAll();

        // Assert the result
        assertNotNull(admins);
        assertEquals(10, admins.size());
        assertEquals("librarian_1", admins.getFirst().getUsername());
    }

    @Test
    void testGetById() throws SQLException {
        when(connection.prepareStatement("SELECT * FROM admins WHERE id = ?")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("username")).thenReturn("admin");
        when(resultSet.getString("email")).thenReturn("admin@test.com");
        when(resultSet.getString("password")).thenReturn("password123");
        when(resultSet.getString("role")).thenReturn("ADMIN");

        AdminModel admin = adminController.getById(1);

        // Assert the result
        assertNotNull(admin);
        assertEquals(1, admin.getId());
        assertEquals("librarian_1", admin.getUsername());
    }

    @Test
    void testDeleteById() throws SQLException {
        // Arrange
        int magazineId = 11;
        String deleteQuery = "DELETE FROM magazines WHERE id = ?";

        // Mocking the PreparedStatement
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(deleteQuery)).thenReturn(preparedStatement);

        // Simulate the deletion
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simulate 1 row affected

        // Create the controller and perform the operation
        AdminController controller = new AdminController();
        boolean result = controller.deleteById(magazineId);

        assertFalse(result);
    }

//    @Test
//    void testUpdateById() throws SQLException {
//        AdminModel admin = new AdminModel(1, "updatedAdmin", "updated@test.com", null, null, Enums.Roles.ADMIN, "newPassword");
//        when(connection.prepareStatement(adminController.getUpdateQuery())).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(1);
//
//        boolean result = adminController.updateById(admin);
//
//        // Assert the result and verify interactions
//        assertTrue(result);
//        verify(preparedStatement, times(1)).executeUpdate();
//    }
}
