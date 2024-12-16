package com.example.library_management_system.controllers;

import com.example.library_management_system.controllers.MagazineController;
import com.example.library_management_system.modles.MagazineModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MagazineControllerTest {

    @InjectMocks
    private MagazineController magazineController;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
    }

    // Test mapping a row from ResultSet to Model
    @Test
    void testMapRowToModel() throws SQLException {
        // Arrange
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("title")).thenReturn("Magazine 1");
        when(mockResultSet.getString("editor")).thenReturn("Editor 1");
        when(mockResultSet.getString("issn")).thenReturn("1234-5678");
        when(mockResultSet.getBoolean("available_state")).thenReturn(true);
        when(mockResultSet.getString("publisher")).thenReturn("Publisher 1");
        when(mockResultSet.getInt("total_copies")).thenReturn(100);
        when(mockResultSet.getInt("copies_left")).thenReturn(50);
        when(mockResultSet.getInt("volume")).thenReturn(1);
        when(mockResultSet.getDate("created_at")).thenReturn(Date.valueOf("2024-01-01"));
        when(mockResultSet.getDate("updated_at")).thenReturn(Date.valueOf("2024-01-01"));

        // Act
        MagazineModel magazine = magazineController.mapRowToModel(mockResultSet);

        // Assert
        assertNotNull(magazine);
        assertEquals(1, magazine.getId());
        assertEquals("Magazine 1", magazine.getTitle());
        assertEquals("Editor 1", magazine.getEditor());
        assertTrue(magazine.isAvailableState());
        assertEquals("1234-5678", magazine.getIssn());
        assertEquals(100, magazine.getTotalCopies());
        assertEquals(50, magazine.getCopiesLeft());
        assertEquals(1, magazine.getVolume());
        assertEquals(Date.valueOf("2024-01-01"), magazine.getCreatedAt());
        assertEquals(Date.valueOf("2024-01-01"), magazine.getUpdatedAt());
    }

    // Test getting all magazines
    @Test
    void testGetAll() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("title")).thenReturn("Magazine 1");

        // Act
        List<MagazineModel> magazines = magazineController.getAll();

        // Assert
        assertNotNull(magazines);
        assertEquals(5, magazines.size());
        assertEquals("Time", magazines.getFirst().getTitle());
    }


    @Test
    void testDeleteById() throws SQLException {
        // Arrange
        int magazineId = 4;
        String deleteQuery = "DELETE FROM magazines WHERE id = ?";

        // Mocking the PreparedStatement
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(deleteQuery)).thenReturn(mockPreparedStatement);

        // Simulate the deletion
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simulate 1 row affected

        // Create the controller and perform the operation
        MagazineController controller = new MagazineController();
        boolean result = controller.deleteById(magazineId);

        // Assert the result
        System.out.println(result);
        assertTrue(result);
    }


}
