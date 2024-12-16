package com.example.library_management_system.controllers;

import com.example.library_management_system.modles.AdminModel;
import com.example.library_management_system.modles.Enums;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @Test
    void testCreateOne() throws SQLException {
        AdminModel admin = new AdminModel(0, "newAdmin", "new@test.com", null, null, Enums.Roles.ADMIN, "newPassword");
        when(connection.prepareStatement(adminController.getCreateQuery(), Statement.RETURN_GENERATED_KEYS))
                .thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = adminController.createOne(admin);

        // Assert the result and verify interactions
//        assertTrue(result);
//        verify(preparedStatement, times(1)).executeUpdate();
    }

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
        assertEquals("librarian_1", admins.get(0).getUsername());
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
        when(connection.prepareStatement("DELETE FROM admins WHERE id = ?")).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = adminController.deleteById(1);

        // Assert the result and verify interactions
        assertTrue(result);
        verify(preparedStatement, times(1)).setInt(1, 1);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testUpdateById() throws SQLException {
        AdminModel admin = new AdminModel(1, "updatedAdmin", "updated@test.com", null, null, Enums.Roles.ADMIN, "newPassword");
        when(connection.prepareStatement(adminController.getUpdateQuery())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = adminController.updateById(admin);

        // Assert the result and verify interactions
        assertTrue(result);
        verify(preparedStatement, times(1)).executeUpdate();
    }
}
