package com.example.library_management_system.modles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class MagazineModelTest {

    private MagazineModel magazineModel;

    private final int id = 1;
    private final boolean availableState = true;
    private final String title = "Java Magazine";
    private final String publisher = "Oracle Press";
    private final int totalCopies = 20;
    private final int copiesLeft = 10;
    private final String editor = "John Doe";
    private final String issn = "1234-5678";
    private final int volume = 5;
    private final Date createdAt = new Date();
    private final Date updatedAt = new Date();

    @BeforeEach
    void setUp() {
        // Initialize MagazineModel object before each test
        magazineModel = new MagazineModel(id, availableState, title, publisher, totalCopies,
                copiesLeft, editor, issn, volume, createdAt, updatedAt);
    }

    @Test
    void testMagazineModelConstructor() {
        // Test that the constructor initializes the object correctly
        assertEquals(id, magazineModel.getId());
        assertEquals(availableState, magazineModel.isAvailableState());
        assertEquals(title, magazineModel.getTitle());
        assertEquals(publisher, magazineModel.getPublisher());
        assertEquals(totalCopies, magazineModel.getTotalCopies());
        assertEquals(copiesLeft, magazineModel.getCopiesLeft());
        assertEquals(editor, magazineModel.getEditor());
        assertEquals(issn, magazineModel.getIssn());
        assertEquals(volume, magazineModel.getVolume());
        assertEquals(createdAt, magazineModel.getCreatedAt());
        assertEquals(updatedAt, magazineModel.getUpdatedAt());
    }

    @Test
    void testDefaultConstructor() {
        // Test the default constructor that doesn't require ID or timestamps
        MagazineModel defaultMagazine = new MagazineModel(availableState, title, publisher, totalCopies,
                copiesLeft, editor, issn, volume);

        assertNotNull(defaultMagazine);
        assertEquals(title, defaultMagazine.getTitle());
        assertEquals(editor, defaultMagazine.getEditor());
    }

    @Test
    void getEditor() {
        // Test the getEditor method
        assertEquals(editor, magazineModel.getEditor());
    }

    @Test
    void setEditor() {
        // Test the setEditor method
        String newEditor = "Jane Smith";
        magazineModel.setEditor(newEditor);
        assertEquals(newEditor, magazineModel.getEditor());
    }

    @Test
    void getIssn() {
        // Test the getIssn method
        assertEquals(issn, magazineModel.getIssn());
    }

    @Test
    void setIssn() {
        // Test the setIssn method
        String newIssn = "9876-5432";
        magazineModel.setIssn(newIssn);
        assertEquals(newIssn, magazineModel.getIssn());
    }

    @Test
    void getVolume() {
        // Test the getVolume method
        assertEquals(volume, magazineModel.getVolume());
    }

    @Test
    void setVolume() {
        // Test the setVolume method
        int newVolume = 6;
        magazineModel.setVolume(newVolume);
        assertEquals(newVolume, magazineModel.getVolume());
    }

    @Test
    void testToString() {
        // Test the toString method
        String expectedString = "\n{ id = 1, available_state = true, title = Java Magazine, " +
                "publisher = Oracle Press, total_copies = 20, copies_left = 10, " + "created_at = " + createdAt + ", "+ "updated_at = "+ updatedAt + ", "+
                "editor = John Doe, issn = 1234-5678, volume = 5 }\n";
        assertEquals(expectedString, magazineModel.toString());
    }
}
