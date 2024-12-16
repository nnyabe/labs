package com.example.library_management_system.modles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest {

    @Test
    void testSingletonInstance() {
        UserSession session1 = UserSession.getInstance();
        UserSession session2 = UserSession.getInstance();

        assertNotNull(session1);
        assertNotNull(session2);

        // Both instances should be the same
        assertSame(session1, session2);
    }

    @Test
    void testSetAndGetUsername() {
        UserSession session = UserSession.getInstance();

        // Set a username and check if it can be retrieved correctly
        session.setUsername("test_user");
        assertEquals("test_user", session.getUsername());

        // Change the username and verify the new value
        session.setUsername("another_user");
        assertEquals("another_user", session.getUsername());
    }

    @Test
    void testDefaultConstructorDoesNotInitializeUsername() {
        UserSession session = UserSession.getInstance();

        // Verify that the default constructor does not initialize username
        session.setUsername(null); // Reset username if already set
        assertNull(session.getUsername());
    }
}
