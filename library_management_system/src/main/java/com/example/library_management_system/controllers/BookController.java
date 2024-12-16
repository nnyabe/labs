package com.example.library_management_system.controllers;

import com.example.library_management_system.modles.BookModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller class for managing books in the library system.
 * This class handles CRUD operations for book records in the database.
 */
public class BookController extends BaseModelController<BookModel> {


    @Override
    protected BookModel mapRowToModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String isbn = resultSet.getString("isbn");
        boolean availabilityStatus = resultSet.getBoolean("available_state");
        String publisher = resultSet.getString("publisher");
        int totalCopies = resultSet.getInt("total_copies");
        int copiesLeft = resultSet.getInt("copies_left");
        int edition = resultSet.getInt("edition");
        Date createdAt = resultSet.getDate("created_at");
        Date updatedAt = resultSet.getDate("updated_at");

        return new BookModel(id, availabilityStatus, title, publisher,
                totalCopies, copiesLeft,
                author, isbn, edition, createdAt, updatedAt);
    }


    @Override
    protected String getTableName() {
        return "books";
    }


    @Override
    protected String getUpdateQuery() {
        return "UPDATE books SET available_state = ?, title = ?, publisher = ?, total_copies = ? " +
                "copies_left = ?, author = ? , isbn = ? , edition = ? " +
                "WHERE id = ?";
    }


    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, BookModel book) throws SQLException {
        preparedStatement.setBoolean(1, book.isAvailableState());
        preparedStatement.setString(2, book.getTitle());
        preparedStatement.setString(3, book.getPublisher());
        preparedStatement.setInt(4, book.getTotalCopies());
        preparedStatement.setInt(5, book.getCopiesLeft());
        preparedStatement.setString(6, book.getAuthor());
        preparedStatement.setString(7, book.getIsbn());
        preparedStatement.setInt(8, book.getEdition());
        preparedStatement.setInt(9, book.getId());
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO books (available_state, title, publisher, total_copies, copies_left, author, isbn, edition)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }


    @Override
    protected void setCreateParameters(PreparedStatement preparedStatement, BookModel book) throws SQLException {
        preparedStatement.setBoolean(1, book.isAvailableState());
        preparedStatement.setString(2, book.getTitle());
        preparedStatement.setString(3, book.getPublisher());
        preparedStatement.setInt(4, book.getTotalCopies());
        preparedStatement.setInt(5, book.getCopiesLeft());
        preparedStatement.setString(6, book.getAuthor());
        preparedStatement.setString(7, book.getIsbn());
        preparedStatement.setInt(8, book.getEdition());
    }


    @Override
    public List<BookModel> getAll() throws SQLException {
        return super.getAll();
    }


    @Override
    public BookModel getById(int id) throws SQLException {
        return super.getById(id);
    }


    @Override
    public boolean deleteById(int id) throws SQLException {
        return super.deleteById(id);
    }


    @Override
    public boolean updateById(BookModel book) throws SQLException {
        return super.updateById(book);
    }

    @Override
    public boolean createOne(BookModel book) throws SQLException {
        return super.createOne(book);
    }
}
