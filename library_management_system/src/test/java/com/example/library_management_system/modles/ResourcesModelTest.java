package com.example.library_management_system.modles;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ResourcesModelTest {

    private static ResourcesModel testResource;

    @BeforeAll
    static void setUp() {
        // Create a shared instance of ResourcesModel for tests that do not create their own instance
        testResource = new ResourcesModel(1, true, "Sample Title", "Sample Publisher",
                10, 5, new Date(), new Date()) {};
    }

    @Test
    void testConstructorWithoutId() {
        ResourcesModel resource = new ResourcesModel(true, "Title Without ID", "Publisher Without ID",
                20, 15, "Sample Resource") {};

        assertNotNull(resource);
        assertEquals(0, resource.getId());
        assertEquals("Title Without ID", resource.getTitle());
        assertEquals("Publisher Without ID", resource.getPublisher());
        assertEquals(20, resource.getTotalCopies());
        assertEquals(15, resource.getCopiesLeft());
        assertTrue(resource.isAvailableState());
        assertNotNull(resource.getCreatedAt());
        assertNotNull(resource.getUpdatedAt());
    }

    @Test
    void testFullConstructor() {
        ResourcesModel resource = new ResourcesModel(2, true, "Sample Title", "Sample Publisher",
                10, 5, new Date(), new Date()) {};

        assertNotNull(resource);
        assertEquals(2, resource.getId());
        assertEquals("Sample Title", resource.getTitle());
        assertEquals("Sample Publisher", resource.getPublisher());
        assertEquals(10, resource.getTotalCopies());
        assertEquals(5, resource.getCopiesLeft());
        assertTrue(resource.isAvailableState());
        assertNotNull(resource.getCreatedAt());
        assertNotNull(resource.getUpdatedAt());
    }


    @Test
    void testGetId() {
        assertEquals(1, testResource.getId());
    }

    @Test
    void testSetId() {
        testResource.setId(2);
        assertEquals(2, testResource.getId());
    }

    @Test
    void testAvailableState() {
        ResourcesModel resource = new ResourcesModel(false, "Another Title", "Another Publisher",
                15, 7, "Sample Resource") {};
        assertFalse(resource.isAvailableState());
        resource.setAvailableState(true);
        assertTrue(resource.isAvailableState());
    }

    @Test
    void testSetAvailableState() {
        testResource.setAvailableState(false);
        assertFalse(testResource.isAvailableState());
    }

    @Test
    void testGetTitle() {
        assertEquals("Sample Title", testResource.getTitle());
    }

    @Test
    void testSetTitle() {
        testResource.setTitle("New Title");
        assertEquals("New Title", testResource.getTitle());
    }

    @Test
    void testGetPublisher() {
        assertEquals("Sample Publisher", testResource.getPublisher());
    }

    @Test
    void testSetPublisher() {
        testResource.setPublisher("New Publisher");
        assertEquals("New Publisher", testResource.getPublisher());
    }

    @Test
    void testGetTotalCopies() {
        ResourcesModel resource = new ResourcesModel(true, "Resource Title", "Resource Publisher",
                25, 20, "Sample Resource") {};
        assertEquals(25, resource.getTotalCopies());
        resource.setTotalCopies(30);
        assertEquals(30, resource.getTotalCopies());
    }

    @Test
    void testSetTotalCopies() {
        testResource.setTotalCopies(20);
        assertEquals(20, testResource.getTotalCopies());
    }

    @Test
    void testGetCopiesLeft() {
        assertEquals(5, testResource.getCopiesLeft());
    }

    @Test
    void testSetCopiesLeft() {
        testResource.setCopiesLeft(8);
        assertEquals(8, testResource.getCopiesLeft());
    }

    @Test
    void testGetCreatedAt() {
        assertNotNull(testResource.getCreatedAt());
    }

    @Test
    void testSetCreatedAt() {
        Date newDate = new Date();
        testResource.setCreatedAt(newDate);
        assertEquals(newDate, testResource.getCreatedAt());
    }

    @Test
    void testGetUpdatedAt() {
        assertNotNull(testResource.getUpdatedAt());
    }

    @Test
    void testSetUpdatedAt() {
        Date newDate = new Date();
        testResource.setUpdatedAt(newDate);
        assertEquals(newDate, testResource.getUpdatedAt());
    }

    @Test
    void testToString() {
        ResourcesModel resource = new ResourcesModel(true, "ToString Title", "ToString Publisher",
                12, 6, "Sample Resource") {};
        String toString = resource.toString();
        assertTrue(toString.contains("id = 0")); // Default ID in this constructor
        assertTrue(toString.contains("available_state = true"));
        assertTrue(toString.contains("title = ToString Title"));
        assertTrue(toString.contains("publisher = ToString Publisher"));
        assertTrue(toString.contains("total_copies = 12"));
        assertTrue(toString.contains("copies_left = 6"));
    }
}
