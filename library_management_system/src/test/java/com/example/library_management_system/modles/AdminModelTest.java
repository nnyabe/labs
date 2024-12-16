package com.example.library_management_system.modles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AdminModelTest {
        private AdminModel adminModel;
        private final int id = 1;
        private final String username = "adminUser";
        private final String email = "admin@example.com";
        private final Date createdAt = new Date();
        private final Date updatedAt = new Date();
        private final Enums.Roles role = Enums.Roles.ADMIN;
        private final String password = "password123";

        @BeforeEach
        void setUp() {
            // Creating an instance of AdminModel for testing
            adminModel = new AdminModel(id, username, email, createdAt, updatedAt, role, password);
        }

        @Test
        void testConstructor() {
            // Verifying that the AdminModel object is initialized correctly
            assertNotNull(adminModel);
            assertEquals(id, adminModel.getId());
            assertEquals(username, adminModel.getUsername());
            assertEquals(email, adminModel.getEmail());
            assertEquals(role, adminModel.getRole());
            assertEquals(password, adminModel.getPassword());
        }

        @Test
        void testGetPassword() {
            // Verifying that the password getter works
            assertEquals(password, adminModel.getPassword());
        }

        @Test
        void testSetPassword() {
            // Verifying that the password setter works
            String newPassword = "newPassword123";
            adminModel.setPassword(newPassword);
            assertEquals(newPassword, adminModel.getPassword());
        }

        @Test
        void testGetRole() {
            // Verifying that the role getter works
            assertEquals(role, adminModel.getRole());
        }

        @Test
        void testSetRole() {
            // Verifying that the role setter works
            Enums.Roles newRole = Enums.Roles.ADMIN;
            adminModel.setRole(newRole);
            assertEquals(newRole, adminModel.getRole());
        }

        @Test
        void testSetUsername() {
            // Verifying that the setter for username works (inherited from UserModel)
            String newUsername = "newAdminUser";
            adminModel.setUsername(newUsername);
            assertEquals(newUsername, adminModel.getUsername());
        }

        @Test
        void testSetEmail() {
            // Verifying that the setter for email works (inherited from UserModel)
            String newEmail = "newAdmin@example.com";
            adminModel.setEmail(newEmail);
            assertEquals(newEmail, adminModel.getEmail());
        }

        @Test
        void testSetUpdatedAt() {
            // Verifying that the setter for updated_at works (inherited from UserModel)
            Date newUpdatedAt = new Date();
            adminModel.setUpdated_at(newUpdatedAt);
            assertEquals(newUpdatedAt, adminModel.getUpdated_at());
        }
    }
