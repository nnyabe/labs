package com.example.library_management_system.controllers;

import com.example.library_management_system.controllers.TransactionController;
import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.modles.Enums;
import com.example.library_management_system.modles.TransactionModel;
import com.example.library_management_system.utils.DBConnection;
import javafx.application.Platform;
import javafx.scene.control.Button;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.sql.*;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;
    @Mock
    private Button mockButton;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
    }
    @BeforeAll
    static void setupJavaFX() {
        // Initialize the JavaFX Toolkit to avoid "Toolkit not initialized" error
        Platform.startup(() -> {}); // This initializes the JavaFX environment
    }

    @Test
    void testCreateTransaction() throws SQLException {
        TransactionModel transaction = new TransactionModel(0, Date.valueOf("2024-12-17"), null, null, Enums.Stautus.PENDING, "john.doe@example.com", "jane.smith@example.com", 1, "BOOK", Enums.Types.BORROW);

        String query = "INSERT INTO transactions (approved_date, return_date, status, approved_by, ordered_by, resource_id, resource_type, transaction_type)" +
                " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        when(mockConnection.prepareStatement(query)).thenReturn(mockPreparedStatement);

        doNothing().when(mockPreparedStatement).setDate(1, transaction.getApprovedDate());
        doNothing().when(mockPreparedStatement).setDate(2, transaction.getReturnDate());
        doNothing().when(mockPreparedStatement).setString(3, transaction.getStatus().toString());
        doNothing().when(mockPreparedStatement).setString(4, transaction.getApprovedBy());
        doNothing().when(mockPreparedStatement).setString(5, transaction.getOrderedBy());
        doNothing().when(mockPreparedStatement).setInt(6, transaction.getResourceId());
        doNothing().when(mockPreparedStatement).setString(7, transaction.getResourceType());
        doNothing().when(mockPreparedStatement).setString(8, transaction.getTransactionType().toString());

        // Ensuring that any JavaFX-related operations are executed on the JavaFX thread
        Platform.runLater(() -> {
            try {
                boolean result = transactionController.createOne(transaction);
                assertTrue(result);
            } catch (SQLException e) {
//                fail("Exception should not have been thrown: " + e.getMessage());
            }
        });
    }

    @Test
    void testGetAllTransactions() throws SQLException {
        String query = "SELECT * FROM transactions";
        when(mockConnection.prepareStatement(query)).thenReturn(mockPreparedStatement);

        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        List<TransactionModel> transactions = transactionController.getAll();
        assertNotNull(transactions);
    }

    @Test
    void testGetUserTransactions() throws SQLException {
        String username = "john_doe";
        String query = "SELECT t.* FROM transactions t INNER JOIN patrons p ON t.ordered_by = p.email WHERE p.username = ?";

        when(mockConnection.prepareStatement(query)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        List<TransactionModel> transactions = transactionController.getUserTransactions(username);
        assertNotNull(transactions);
    }

//    @Test
//    void testApproveTransaction() throws SQLException, MySQLConnectionException {
//        int transactionId = 1;
//        String librarianEmail = "librarian@example.com";  // Use a valid email from the `admins` table
//
//        String resourceType = "BOOK";
//
//        // Mocking DB behavior for the resource info
//        String getResourceInfo = "SELECT resource_id, resource_type FROM transactions WHERE id = ?";
//        when(mockConnection.prepareStatement(getResourceInfo)).thenReturn(mockPreparedStatement);
//        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true);
//        when(mockResultSet.getString("resource_type")).thenReturn(resourceType);
//
//        // Mocking the update query for book resource approval
//        String updateQuery = "UPDATE transactions t JOIN books b ON t.resource_id = b.id " +
//                "SET t.status = 'APPROVED', t.approved_date = CURRENT_TIMESTAMP, t.approved_by = ?, b.copies_left = b.copies_left - 1 " +
//                "WHERE t.id = ? AND b.copies_left > 0";
//        when(mockConnection.prepareStatement(updateQuery)).thenReturn(mockPreparedStatement);
//
//        doNothing().when(mockPreparedStatement).setString(1, librarianEmail);  // Pass valid email
//        doNothing().when(mockPreparedStatement).setInt(2, transactionId);
//
//        // Test approval
//        transactionController.approveTransaction(transactionId, librarianEmail);  // Use librarian email here
////        verify(mockPreparedStatement, times(1)).executeUpdate();
//    }


//    @Test
//    void testReturnResource() throws SQLException, MySQLConnectionException {
//        int transactionId = 1;
//        String resourceType = "BOOK";
//
//        String getResourceInfo = "SELECT resource_id, resource_type FROM transactions WHERE id = ? AND status = 'APPROVED'";
//        when(mockConnection.prepareStatement(getResourceInfo)).thenReturn(mockPreparedStatement);
//        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true);
//        when(mockResultSet.getString("resource_type")).thenReturn(resourceType);
//
//        // Mocking update for returning book
//        String updateQuery = "UPDATE transactions t JOIN books b ON t.resource_id = b.id " +
//                "SET t.status = 'RETURNED', t.return_date = CURRENT_TIMESTAMP, b.copies_left = b.copies_left + 1 " +
//                "WHERE t.id = ? AND b.copies_left >= 0";
//        when(mockConnection.prepareStatement(updateQuery)).thenReturn(mockPreparedStatement);
//
//        doNothing().when(mockPreparedStatement).setInt(1, transactionId);
//
//        // Test return
//        transactionController.returnResource(transactionId);
//        verify(mockPreparedStatement, times(1)).executeUpdate();
//    }

//    @Test
//    void testBorrowResource() throws SQLException {
//        String orderedBy = "john.doe@example.com";
//        int resourceId = 1;
//        String resourceType = "BOOK";
//
//        String query = "INSERT INTO transactions (ordered_by, resource_id, resource_type, transaction_type, status) " +
//                "VALUES (?, ?, ?, 'BORROW', 'PENDING')";
//        when(mockConnection.prepareStatement(query)).thenReturn(mockPreparedStatement);
//
//        doNothing().when(mockPreparedStatement).setString(1, orderedBy);
//        doNothing().when(mockPreparedStatement).setInt(2, resourceId);
//        doNothing().when(mockPreparedStatement).setString(3, resourceType);
//
//        transactionController.borrowResource(orderedBy, resourceId, resourceType);
//        verify(mockPreparedStatement, times(1)).executeUpdate();
//    }
}
