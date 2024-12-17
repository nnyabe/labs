package com.example.library_management_system.controllers;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.library_management_system.controllers.BaseModelController;
import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.modles.BookModel;
import com.example.library_management_system.utils.DBConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;
//import java.util.Date;

public class BaseModelControllerTest {

    private BaseModelController<BookModel> controller;

    @Mock private Connection connection;
    @Mock private Statement statement;
    @Mock private PreparedStatement preparedStatement;
    @Mock private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException, MySQLConnectionException {
        MockitoAnnotations.openMocks(this);
        controller = new BookController();
//        when(DBConnection.createConnection()).thenReturn(connection);
    }

    @Test
    void testGetAll() throws SQLException {
        String query = "SELECT * FROM books";
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(query)).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // simulate one row

        // Set up mock ResultSet to return expected values
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getBoolean("available_state")).thenReturn(true);
        when(resultSet.getString("title")).thenReturn("Java Programming");
        when(resultSet.getString("publisher")).thenReturn("Pearson");
        when(resultSet.getInt("total_copies")).thenReturn(10);
        when(resultSet.getInt("copies_left")).thenReturn(8);
        when(resultSet.getString("author")).thenReturn("John Doe");
        when(resultSet.getString("isbn")).thenReturn("978-1234567890");
        when(resultSet.getInt("edition")).thenReturn(3);
        when(resultSet.getDate("created_at")).thenReturn( new Date( new java.util.Date().getTime()));
        when(resultSet.getDate("updated_at")).thenReturn(new Date(new java.util.Date().getTime()));

        List<BookModel> result = controller.getAll();
        assertNotNull(result);
        assertEquals(13, result.size());
        BookModel book = result.getFirst();
        assertEquals(1, book.getId());
        assertEquals("The Great Gatsby", book.getTitle());
        assertEquals("F. Scott Fitzgerald", book.getAuthor());
        assertEquals("9780743273565", book.getIsbn());
        assertEquals(1, book.getEdition());
        assertTrue(book.isAvailableState());
    }

    @Test
    void testGetById() throws SQLException {
        String query = "SELECT * FROM books WHERE id = ?";
        when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);

        // Mock the result set
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getBoolean("available_state")).thenReturn(true);
        when(resultSet.getString("title")).thenReturn("Java Programming");
        when(resultSet.getString("publisher")).thenReturn("Pearson");
        when(resultSet.getInt("total_copies")).thenReturn(10);
        when(resultSet.getInt("copies_left")).thenReturn(8);
        when(resultSet.getString("author")).thenReturn("John Doe");
        when(resultSet.getString("isbn")).thenReturn("978-1234567890");
        when(resultSet.getInt("edition")).thenReturn(3);
        when(resultSet.getDate("created_at")).thenReturn((java.sql.Date) new Date(12));
        when(resultSet.getDate("updated_at")).thenReturn((java.sql.Date) new Date(12));

        BookModel result = controller.getById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("The Great Gatsby", result.getTitle());
        assertEquals("F. Scott Fitzgerald", result.getAuthor());
        assertEquals("9780743273565", result.getIsbn());
        assertEquals(1, result.getEdition());
        assertTrue(result.isAvailableState());
    }


//
//    @Test
//    void testCreateOne() throws SQLException {
//        String query = "INSERT INTO books (available_state, title, publisher, total_copies, copies_left, author, isbn, edition, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        when(connection.prepareStatement(query)).thenReturn(preparedStatement);
//        when(preparedStatement.executeUpdate()).thenReturn(1); // simulate successful insert
//
//        BookModel book = new BookModel(false, "New Book", "O'Reilly", 20, 15, "Tom Smith", "97112233445", 1);
//        boolean result = controller.createOne(book);
//        assertTrue(result);
//    }
}
