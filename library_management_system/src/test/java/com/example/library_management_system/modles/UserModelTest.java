package com.example.library_management_system.modles;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {

    @Test
    void testDefaultConstructor() {
        UserModel user = new UserModel() {};
        assertNotNull(user);
        assertEquals(0, user.getId());
        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertNull(user.getCreated_at());
        assertNull(user.getUpdated_at());
    }

    @Test
    void testParameterizedConstructor() {
        Date createdAt = new Date();
        Date updatedAt = new Date();

        UserModel user = new UserModel(1, "john_doe", "john.doe@example.com", createdAt, updatedAt) {};

        assertEquals(1, user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals(createdAt, user.getCreated_at());
        assertEquals(updatedAt, user.getUpdated_at());
    }

    @Test
    void testGetId() {
        UserModel user = new UserModel() {};
        user.setId(123);
        assertEquals(123, user.getId());
    }

    @Test
    void testSetId() {
        UserModel user = new UserModel() {};
        user.setId(456);
        assertEquals(456, user.getId());
    }

    @Test
    void testGetUsername() {
        UserModel user = new UserModel() {};
        user.setUsername("alice");
        assertEquals("alice", user.getUsername());
    }

    @Test
    void testSetUsername() {
        UserModel user = new UserModel() {};
        user.setUsername("bob");
        assertEquals("bob", user.getUsername());
    }

    @Test
    void testGetEmail() {
        UserModel user = new UserModel() {};
        user.setEmail("example@example.com");
        assertEquals("example@example.com", user.getEmail());
    }

    @Test
    void testSetEmail() {
        UserModel user = new UserModel() {};
        user.setEmail("new_email@example.com");
        assertEquals("new_email@example.com", user.getEmail());
    }

    @Test
    void testGetCreatedAt() {
        UserModel user = new UserModel() {};
        Date createdAt = new Date();
        user.setCreated_at(createdAt);
        assertEquals(createdAt, user.getCreated_at());
    }

    @Test
    void testSetCreatedAt() {
        UserModel user = new UserModel() {};
        Date createdAt = new Date();
        user.setCreated_at(createdAt);
        assertEquals(createdAt, user.getCreated_at());
    }

    @Test
    void testGetUpdatedAt() {
        UserModel user = new UserModel() {};
        Date updatedAt = new Date();
        user.setUpdated_at(updatedAt);
        assertEquals(updatedAt, user.getUpdated_at());
    }

    @Test
    void testSetUpdatedAt() {
        UserModel user = new UserModel() {};
        Date updatedAt = new Date();
        user.setUpdated_at(updatedAt);
        assertEquals(updatedAt, user.getUpdated_at());
    }
}
