package com.example.library_management_system.modles;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PatronModelTest {

    private static PatronModel testPatron;

    @BeforeAll
    static void setUp() {
        // Define a reusable PatronModel instance
        int id = 1;
        String username = "john_doe";
        String email = "john@example.com";
        Date createdAt = new Date();
        Date updatedAt = new Date();

        testPatron = new PatronModel(id, username, email, createdAt, updatedAt);
    }

    @Test
    void testDefaultConstructor() {
        // Test the default constructor
        PatronModel patron = new PatronModel();
        assertNotNull(patron);
    }

    @Test
    void testConstructorWithoutIdAndTimestamps() {
        // Test constructor with username and email
        String username = "john_doe";
        String email = "john@example.com";
        PatronModel patron = new PatronModel(username, email);

        assertNotNull(patron);
        assertEquals(0, patron.getId());
        assertEquals(username, patron.getUsername());
        assertEquals(email, patron.getEmail());
        assertNull(patron.getCreated_at());
        assertNull(patron.getUpdated_at());
    }

    @Test
    void testFullConstructor() {
        // Verify the instance from @BeforeAll
        assertNotNull(testPatron);
        assertEquals(1, testPatron.getId());
        assertEquals("john_doe", testPatron.getUsername());
        assertEquals("john@example.com", testPatron.getEmail());
        assertNotNull(testPatron.getCreated_at());
        assertNotNull(testPatron.getUpdated_at());
    }

    @Test
    void getId() {
        assertEquals(1, testPatron.getId());
    }

    @Test
    void setId() {
        testPatron.setId(5);
        assertEquals(5, testPatron.getId());
    }

    @Test
    void getUsername() {
        // Test the getUsername method
        String username = "john_doe";
        PatronModel patron = new PatronModel(1, username, "john@example.com", new Date(), new Date());
        assertEquals(username, patron.getUsername());
    }

    @Test
    void setUsername() {
        testPatron.setUsername("jane_doe");
        assertEquals("jane_doe", testPatron.getUsername());
    }

    @Test
    void getEmail() {
        // Test the getEmail method
        String email = "john@example.com";
        PatronModel patron = new PatronModel(1, "john_doe", email, new Date(), new Date());
        assertEquals(email, patron.getEmail());
    }
    @Test
    void setEmail() {
        testPatron.setEmail("jane@example.com");
        assertEquals("jane@example.com", testPatron.getEmail());
    }

    @Test
    void getCreatedAt() {
        assertNotNull(testPatron.getCreated_at());
    }

    @Test
    void setCreatedAt() {
        Date newDate = new Date();
        testPatron.setCreated_at(newDate);
        assertEquals(newDate, testPatron.getCreated_at());
    }

    @Test
    void getUpdatedAt() {
        assertNotNull(testPatron.getUpdated_at());
    }

    @Test
    void setUpdatedAt() {
        Date newDate = new Date();
        testPatron.setUpdated_at(newDate);
        assertEquals(newDate, testPatron.getUpdated_at());
    }
}
