package com.example.library_management_system.modles;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransactionModelTest {

    @Test
    void testDefaultConstructor() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        assertEquals(0, transaction.getId());
        assertNull(transaction.getOrderDate());
        assertNull(transaction.getApprovedDate());
        assertNull(transaction.getReturnDate());
        assertEquals("PENDING", transaction.getStatus());
        assertNull(transaction.getApprovedBy());
        assertEquals("john.doe", transaction.getOrderedBy());
        assertEquals(101, transaction.getResourceId());
        assertEquals("Book", transaction.getResourceType());
        assertEquals(Enums.Types.BORROW, transaction.getTransactionType());
    }

    @Test
    void testFullConstructor() {
        Date orderDate = Date.valueOf("2024-12-01");
        Date approvedDate = Date.valueOf("2024-12-02");
        Date returnDate = Date.valueOf("2024-12-15");

        TransactionModel transaction = new TransactionModel(
                1, orderDate, approvedDate, returnDate, Enums.Stautus.APPROVED,
                "admin.jane", "john.doe", 101, "Book", Enums.Types.BORROW
        );

        assertEquals(1, transaction.getId());
        assertEquals(orderDate, transaction.getOrderDate());
        assertEquals(approvedDate, transaction.getApprovedDate());
        assertEquals(returnDate, transaction.getReturnDate());
        assertEquals("APPROVED", transaction.getStatus());
        assertEquals("admin.jane", transaction.getApprovedBy());
        assertEquals("john.doe", transaction.getOrderedBy());
        assertEquals(101, transaction.getResourceId());
        assertEquals("Book", transaction.getResourceType());
        assertEquals(Enums.Types.BORROW, transaction.getTransactionType());
    }

    @Test
    void testGetId() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        transaction.setId(1);
        assertEquals(1, transaction.getId());
    }

    @Test
    void testSetId() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        transaction.setId(2);
        assertEquals(2, transaction.getId());
    }

    @Test
    void testGetOrderDate() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        Date orderDate = Date.valueOf("2024-12-01");
        transaction.setOrderDate(orderDate);
        assertEquals(orderDate, transaction.getOrderDate());
    }

    @Test
    void testSetOrderDate() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        Date orderDate = Date.valueOf("2024-12-01");
        transaction.setOrderDate(orderDate);
        assertEquals(orderDate, transaction.getOrderDate());
    }

    @Test
    void testGetApprovedDate() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        Date approvedDate = Date.valueOf("2024-12-02");
        transaction.setApprovedDate(approvedDate);
        assertEquals(approvedDate, transaction.getApprovedDate());
    }

    @Test
    void testSetApprovedDate() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        Date approvedDate = Date.valueOf("2024-12-02");
        transaction.setApprovedDate(approvedDate);
        assertEquals(approvedDate, transaction.getApprovedDate());
    }

    @Test
    void testGetReturnDate() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        Date returnDate = Date.valueOf("2024-12-15");
        transaction.setReturnDate(returnDate);
        assertEquals(returnDate, transaction.getReturnDate());
    }

    @Test
    void testSetReturnDate() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        Date returnDate = Date.valueOf("2024-12-15");
        transaction.setReturnDate(returnDate);
        assertEquals(returnDate, transaction.getReturnDate());
    }

    @Test
    void testGetStatus() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        transaction.setStatus(Enums.Stautus.PENDING);
        assertEquals("PENDING", transaction.getStatus());
    }

    @Test
    void testSetStatus() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        transaction.setStatus(Enums.Stautus.APPROVED);
        assertEquals("APPROVED", transaction.getStatus());
    }

    @Test
    void testGetApprovedBy() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        transaction.setApprovedBy("admin.jane");
        assertEquals("admin.jane", transaction.getApprovedBy());
    }

    @Test
    void testSetApprovedBy() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        transaction.setApprovedBy("admin.jane");
        assertEquals("admin.jane", transaction.getApprovedBy());
    }

    @Test
    void testGetOrderedBy() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        assertEquals("john.doe", transaction.getOrderedBy());
    }

    @Test
    void testSetOrderedBy() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        transaction.setOrderedBy("jane.doe");
        assertEquals("jane.doe", transaction.getOrderedBy());
    }

    @Test
    void testGetResourceId() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        assertEquals(101, transaction.getResourceId());
    }

    @Test
    void testSetResourceId() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        transaction.setResourceId(102);
        assertEquals(102, transaction.getResourceId());
    }

    @Test
    void testGetResourceType() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        assertEquals("Book", transaction.getResourceType());
    }

    @Test
    void testSetResourceType() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        transaction.setResourceType("Magazine");
        assertEquals("Magazine", transaction.getResourceType());
    }

    @Test
    void testGetTransactionType() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        assertEquals(Enums.Types.BORROW, transaction.getTransactionType());
    }

    @Test
    void testSetTransactionType() {
        TransactionModel transaction = new TransactionModel("john.doe", 101, "Book");
        transaction.setTransactionType(Enums.Types.BORROW);
        assertEquals(Enums.Types.BORROW, transaction.getTransactionType());
    }

    @Test
    void testToString() {
        TransactionModel transaction = new TransactionModel(
                1, Date.valueOf("2024-12-01"), Date.valueOf("2024-12-02"),
                Date.valueOf("2024-12-15"), Enums.Stautus.APPROVED,
                "admin.jane", "john.doe", 101, "Book", Enums.Types.BORROW
        );

        String expected = "Transaction{id=1, orderDate=2024-12-01, approvedDate=2024-12-02, " +
                "returnDate=2024-12-15, status=APPROVED, approvedBy='admin.jane', " +
                "orderedBy='john.doe', resourceId=101, transactionType= BORROW}";
        assertEquals(expected, transaction.toString());
    }
}
