package com.example.library_management_system.controllers;

import com.example.library_management_system.modles.BookModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookControllerTest {


    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;

    private BookController bookController;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        bookController = new BookController();
    }

    @Test
    void testGetAll() throws SQLException {
        // Arrange
        String query = "SELECT * FROM books";
        when(connection.createStatement()).thenReturn(mock(Statement.class));
        when(connection.createStatement().executeQuery(query)).thenReturn(resultSet);

        // Mock ResultSet behavior
        when(resultSet.next()).thenReturn(true).thenReturn(false); // Simulate one row of data
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("title")).thenReturn("Test Book");
        when(resultSet.getString("author")).thenReturn("Test Author");
        when(resultSet.getString("isbn")).thenReturn("123456789");
        when(resultSet.getBoolean("available_state")).thenReturn(true);
        when(resultSet.getString("publisher")).thenReturn("Test Publisher");
        when(resultSet.getInt("total_copies")).thenReturn(10);
        when(resultSet.getInt("copies_left")).thenReturn(5);
        when(resultSet.getInt("edition")).thenReturn(1);
        when(resultSet.getDate("created_at")).thenReturn(Date.valueOf("2023-01-01"));
        when(resultSet.getDate("updated_at")).thenReturn(Date.valueOf("2023-01-01"));

        // Act: Call the method under test
        List<BookModel> result = bookController.getAll();

        // Assert
        assertEquals(15, result.size());
        assertEquals(1, result.getFirst().getId());
        assertEquals("Updated Book", result.getFirst().getTitle());
        assertEquals("Updated Author", result.getFirst().getAuthor());

    }

    @Test
    void testGetById() throws SQLException {
        // Arrange
        String query = "SELECT * FROM books WHERE id = ?";
        when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Mock ResultSet behavior
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("title")).thenReturn("Test Book");
        when(resultSet.getString("author")).thenReturn("Test Author");
        when(resultSet.getString("isbn")).thenReturn("123456789");
        when(resultSet.getBoolean("available_state")).thenReturn(true);

        // Act: Call the method under test
        BookModel result = bookController.getById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(1, result.getId());
        assertEquals("Updated Book", result.getTitle());
        assertEquals("Updated Author", result.getAuthor());
    }

//    @Test
//    void testDeleteById() throws SQLException {
//        // Arrange
//        String query = "DELETE FROM books WHERE id = ?";
//        when(connection.prepareStatement(query)).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(1);
//
//        // Act: Call the method under test
//        boolean result = bookController.deleteById(21);
//
//        // Assert
//        assertFalse(result);
//
//    }

//    @Test
//    void testUpdateById() throws SQLException {
//        // Arrange
//        String query = "UPDATE books SET available_state = ?, title = ?, publisher = ?, total_copies = ?, " +
//                "copies_left = ?, author = ?, isbn = ?, edition = ?WHERE id = ?";
//        when(connection.prepareStatement(query)).thenReturn(preparedStatement);
//
//        // Act: Call the method under test
//        BookModel book = new BookModel(19, true, "Updated Book", "Updated Publisher", 10, 5,
//                "Updated Author", "9876504321", 2, Date.valueOf("2023-01-01"),
//                Date.valueOf("2023-01-01"));
//        boolean result = bookController.updateById(book);
//
//        // Assert
//        assertTrue(result);
//    }

//    @Test
//    void testCreateOne() throws SQLException {
//        // Arrange
//        String query = "INSERT INTO books (available_state, title, publisher, total_copies, copies_left, author, isbn, edition) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        when(connection.prepareStatement(query)).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(1);
//
//        // Act: Call the method under test
//        BookModel book = new BookModel(0, true, "New Book", "New Publisher", 5, 5,
//                "New Author", "12031213123", 1, Date.valueOf("2023-01-01"),
//                Date.valueOf("2023-01-01"));
//        boolean result = bookController.createOne(book);
//
//        // Assert
//        assertTrue(result);
//
//    }


}
