package com.example.library_management_system.modles;

import java.util.Date;

/**
 * Represents a book resource in the library management system.
 * This class extends the ResourcesModel to include additional properties such as author, ISBN, and edition.
 */
public class BookModel extends ResourcesModel {

    private String author;
    private String isbn;
    private int edition;

    public BookModel(boolean availableState, String title,
                     String publisher, int totalCopies, int copiesLeft,
                     String author, String isbn, int edition) {
        this(0, availableState, title, publisher, totalCopies, copiesLeft,
                author, isbn, edition, new Date(), new Date());
    }

    public BookModel(int id, boolean availableState, String title,
                     String publisher, int totalCopies, int copiesLeft,
                     String author, String isbn, int edition, Date createdAt, Date updatedAt) {
        super(id, availableState, title, publisher, totalCopies, copiesLeft, createdAt, updatedAt);
        this.author = author;
        this.isbn = isbn;
        this.edition = edition;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return super.toString() + ", author = " + author
                + ", isbn = " + isbn
                + ", edition = " + edition + " }\n";
    }
}
