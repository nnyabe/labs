package com.example.library_management_system.modles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
class BookModelTest {

        private BookModel bookModel;
        private final int id = 1;
        private final boolean availableState = true;
        private final String title = "Effective Java";
        private final String publisher = "Addison-Wesley";
        private final int totalCopies = 10;
        private final int copiesLeft = 5;
        private final String author = "Joshua Bloch";
        private final String isbn = "978-0134685991";
        private final int edition = 3;
        private final Date createdAt = new Date();
        private final Date updatedAt = new Date();

        @BeforeEach
        void setUp() {
            // Initialize BookModel object before each test
            bookModel = new BookModel(id, availableState, title, publisher, totalCopies,
                    copiesLeft, author, isbn, edition, createdAt, updatedAt);
        }

        @Test
        void testBookModelConstructor() {
            // Test that the constructor initializes the object correctly
            assertEquals(id, bookModel.getId());
            assertEquals(availableState, bookModel.isAvailableState());
            assertEquals(title, bookModel.getTitle());
            assertEquals(publisher, bookModel.getPublisher());
            assertEquals(totalCopies, bookModel.getTotalCopies());
            assertEquals(copiesLeft, bookModel.getCopiesLeft());
            assertEquals(author, bookModel.getAuthor());
            assertEquals(isbn, bookModel.getIsbn());
            assertEquals(edition, bookModel.getEdition());
            assertEquals(createdAt, bookModel.getCreatedAt());
            assertEquals(updatedAt, bookModel.getUpdatedAt());
        }

        @Test
        void testGettersAndSetters() {
            // Test setters and getters for each field
            bookModel.setAuthor("New Author");
            assertEquals("New Author", bookModel.getAuthor());

            bookModel.setIsbn("978-0135772341");
            assertEquals("978-0135772341", bookModel.getIsbn());

            bookModel.setEdition(4);
            assertEquals(4, bookModel.getEdition());
        }

        @Test
        void testToString() {
            // Test the toString method
            String expectedString = "\n{ id = 1, available_state = true, title = Effective Java, " +
                    "publisher = Addison-Wesley, total_copies = 10, copies_left = 5, " + "created_at = " + createdAt + ", "+ "updated_at = "+ updatedAt + ", "+
                    "author = Joshua Bloch, isbn = 978-0134685991, edition = 3 }\n";
            assertEquals(expectedString, bookModel.toString());
        }

        @Test
        void testDefaultConstructor() {
            // Test the default constructor that doesn't require ID or timestamps
            BookModel defaultBook = new BookModel(availableState, title, publisher, totalCopies,
                    copiesLeft, author, isbn, edition);

            assertNotNull(defaultBook);
            assertEquals(title, defaultBook.getTitle());
            assertEquals(author, defaultBook.getAuthor());
        }
    }
