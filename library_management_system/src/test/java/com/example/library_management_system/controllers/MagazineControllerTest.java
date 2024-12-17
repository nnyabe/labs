package com.example.library_management_system.controllers;

import com.example.library_management_system.controllers.MagazineController;
import com.example.library_management_system.modles.AdminModel;
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

    @Mock
    private MagazineModel magazineModel;

    @Mock
    private BaseModelController baseModelController;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        magazineController = new MagazineController();
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        mockConnection = mock(Connection.class);
        magazineModel = mock(MagazineModel.class);
        baseModelController = mock(BaseModelController.class);
    }

//    @Test
//    public void testCreateOne_Success() throws SQLException {
//        // Mocking the MagazineModel
//        MagazineModel magazine = mock(MagazineModel.class);
//        when(magazine.isAvailableState()).thenReturn(true);
//        when(magazine.getTitle()).thenReturn("Test Magazine");
//        when(magazine.getPublisher()).thenReturn("Test Publisher");
//        when(magazine.getTotalCopies()).thenReturn(10);
//        when(magazine.getCopiesLeft()).thenReturn(5);
////        when(magazine.getEditor()).thenReturn("Test Editor");
//        when(magazine.getIssn()).thenReturn("1234-5678");
//        when(magazine.getVolume()).thenReturn(1);
//
//        // Mock the call to the parent createOne method
//        when(baseModelController.createOne(magazine)).thenReturn(true);
//
//        // Call the method under test
//        boolean result = magazineController.createOne(magazine);
//
//        // Verify the interaction
//        verify(baseModelController).createOne(magazine);
//
//        // Assert the result
//        assertTrue(result);
//    }
//
//    @Test
//    public void testCreateOne_Failure() throws SQLException {
//        // Simulate the parent class method throwing a SQLException
//        when(magazineController.createOne(magazineModel)).thenThrow(SQLException.class);
//
//        // Call the method under test and expect it to handle the exception
//        assertThrows(SQLException.class, () -> {
//            magazineController.createOne(magazineModel);
//        });
//
//        // Verify that the parent class method was called
//        verify(magazineController).createOne(magazineModel);
//    }
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

    @Test
    public void testGetTableName() {
        // Call the method to get the table name
        String result = magazineController.getTableName();
        assertEquals("magazines", result, "The table name should be 'magazines'");
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
        assertEquals(4, magazines.size());
        assertEquals("Popular Science", magazines.getFirst().getTitle());
    }


    @Test
    void testDeleteById() throws SQLException {
        // Arrange
        int magazineId = 11;
        String deleteQuery = "DELETE FROM magazines WHERE id = ?";

        // Mocking the PreparedStatement
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(deleteQuery)).thenReturn(mockPreparedStatement);

        // Simulate the deletion
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simulate 1 row affected

        // Create the controller and perform the operation
        MagazineController controller = new MagazineController();
        boolean result = controller.deleteById(magazineId);

        assertFalse(result);
    }
    @Test
    void testGetById() throws SQLException {
        when(mockConnection.prepareStatement("SELECT * FROM magazines WHERE id = ?")).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);


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


        MagazineModel magazine = magazineController.getById(6);
        // Assert the result
        assertNotNull(magazine);
        assertEquals(6, magazine.getId());
        assertEquals("Popular Science", magazine.getTitle());
        assertEquals("Bonnier", magazine.getPublisher());
        assertEquals("Michael Williams", magazine.getEditor());
        assertTrue(magazine.isAvailableState());
        assertEquals("0032-4558", magazine.getIssn());
        assertEquals(7, magazine.getTotalCopies());
        assertEquals(7, magazine.getCopiesLeft());
        assertEquals(5, magazine.getVolume());
        assertEquals(Date.valueOf("2024-12-16"), magazine.getCreatedAt());
        assertNull(magazine.getUpdatedAt());
    }
    @Test
    void testGetCreateQuery() {
        String expectedQuery = "INSERT INTO books (available_state, title, publisher, total_copies, copies_left, editor, issn, volume) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        assertEquals(expectedQuery, magazineController.getCreateQuery());
    }
    @Test
    void testGetUpdateQuery() {
        String expectedQuery ="UPDATE magazines SET available_state = ?, title = ?, publisher = ?, total_copies = ? " +
                "copies_left = ?, editor = ? , issn = ? , volume = ? " +
                "WHERE id = ?";
        assertEquals(expectedQuery, magazineController.getUpdateQuery());
    }

    @Test
    public void testSetUpdateParameters() throws SQLException {
        // Set up the magazine mock to return values
        when(magazineModel.isAvailableState()).thenReturn(true);
        when(magazineModel.getTitle()).thenReturn("Test Magazine");
        when(magazineModel.getPublisher()).thenReturn("Test Publisher");
        when(magazineModel.getTotalCopies()).thenReturn(100);
        when(magazineModel.getCopiesLeft()).thenReturn(50);
        when(magazineModel.getEditor()).thenReturn("Test Editor");
        when(magazineModel.getIssn()).thenReturn("1234-5678");
        when(magazineModel.getVolume()).thenReturn(10);
        when(magazineModel.getId()).thenReturn(1);

        // Call the method to test
        magazineController.setUpdateParameters(mockPreparedStatement, magazineModel);

        // Verify the correct methods were called on the PreparedStatement
        verify(mockPreparedStatement).setBoolean(1, true); // isAvailableState
        verify(mockPreparedStatement).setString(2, "Test Magazine"); // getTitle
        verify(mockPreparedStatement).setString(3, "Test Publisher"); // getPublisher
        verify(mockPreparedStatement).setInt(4, 100); // getTotalCopies
        verify(mockPreparedStatement).setInt(5, 50); // getCopiesLeft
        verify(mockPreparedStatement).setString(6, "Test Editor"); // getEditor
        verify(mockPreparedStatement).setString(7, "1234-5678"); // getIssn
        verify(mockPreparedStatement).setInt(8, 10); // getVolume
        verify(mockPreparedStatement).setInt(9, 1); // getId
    }

    @Test
    public void testSetCreateParameters() throws SQLException {
        // Set up the mock magazine to return values
        when(magazineModel.isAvailableState()).thenReturn(true);
        when(magazineModel.getTitle()).thenReturn("Test Magazine");
        when(magazineModel.getPublisher()).thenReturn("Test Publisher");
        when(magazineModel.getTotalCopies()).thenReturn(100);
        when(magazineModel.getCopiesLeft()).thenReturn(50);
        when(magazineModel.getEditor()).thenReturn("Test Editor");
        when(magazineModel.getIssn()).thenReturn("1234-5678");
        when(magazineModel.getVolume()).thenReturn(10);

        // Call the method under test
        magazineController.setCreateParameters(mockPreparedStatement, magazineModel);

        // Verify that the correct methods were called on the PreparedStatement
        verify(mockPreparedStatement).setBoolean(1, true); // isAvailableState
        verify(mockPreparedStatement).setString(2, "Test Magazine"); // getTitle
        verify(mockPreparedStatement).setString(3, "Test Publisher"); // getPublisher
        verify(mockPreparedStatement).setInt(4, 100); // getTotalCopies
        verify(mockPreparedStatement).setInt(5, 50); // getCopiesLeft
        verify(mockPreparedStatement).setString(6, "Test Editor"); // getEditor
        verify(mockPreparedStatement).setString(7, "1234-5678"); // getIssn
        verify(mockPreparedStatement).setInt(8, 10); // getVolume
    }

}
