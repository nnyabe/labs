package com.example.library_management_system.controllers;

import com.example.library_management_system.modles.MagazineModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller class for managing magazines in the library system.
 * This class handles CRUD operations for magazine records in the database.
 */
public class MagazineController extends BaseModelController<MagazineModel> {


    @Override
    protected MagazineModel mapRowToModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String editor = resultSet.getString("editor");
        String issn = resultSet.getString("issn");
        boolean availabilityStatus = resultSet.getBoolean("available_state");
        String publisher = resultSet.getString("publisher");
        int totalCopies = resultSet.getInt("total_copies");
        int copiesLeft = resultSet.getInt("copies_left");
        int volume = resultSet.getInt("volume");
        Date createdAt = resultSet.getDate("created_at");
        Date updatedAt = resultSet.getDate("updated_at");

        return new MagazineModel(id, availabilityStatus, title, publisher, totalCopies,
                copiesLeft, editor, issn, volume, createdAt, updatedAt);
    }


    @Override
    protected String getTableName() {
        return "magazines";
    }


    @Override
    protected String getUpdateQuery() {
        return "UPDATE magazines SET available_state = ?, title = ?, publisher = ?, total_copies = ? " +
                "copies_left = ?, editor = ? , issn = ? , volume = ? " +
                "WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, MagazineModel magazine)
            throws SQLException {
        preparedStatement.setBoolean(1, magazine.isAvailableState());
        preparedStatement.setString(2, magazine.getTitle());
        preparedStatement.setString(3, magazine.getPublisher());
        preparedStatement.setInt(4, magazine.getTotalCopies());
        preparedStatement.setInt(5, magazine.getCopiesLeft());
        preparedStatement.setString(6, magazine.getEditor());
        preparedStatement.setString(7, magazine.getIssn());
        preparedStatement.setInt(8, magazine.getVolume());
        preparedStatement.setInt(9, magazine.getId());
    }


    @Override
    protected String getCreateQuery() {
        return "INSERT INTO books (available_state, title, publisher, total_copies, copies_left, editor, issn, volume)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }


    @Override
    protected void setCreateParameters(PreparedStatement preparedStatement, MagazineModel magazine)
            throws SQLException {
        preparedStatement.setBoolean(1, magazine.isAvailableState());
        preparedStatement.setString(2, magazine.getTitle());
        preparedStatement.setString(3, magazine.getPublisher());
        preparedStatement.setInt(4, magazine.getTotalCopies());
        preparedStatement.setInt(5, magazine.getCopiesLeft());
        preparedStatement.setString(6, magazine.getEditor());
        preparedStatement.setString(7, magazine.getIssn());
        preparedStatement.setInt(8, magazine.getVolume());
    }


    @Override
    public List<MagazineModel> getAll() throws SQLException {
        return super.getAll();
    }


    @Override
    public MagazineModel getById(int id) throws SQLException {
        return super.getById(id);
    }

    @Override
    public boolean updateById(MagazineModel magazine) throws SQLException {
        return super.updateById(magazine);
    }


    @Override
    public boolean createOne(MagazineModel magazine) throws SQLException {
        return super.createOne(magazine);
    }


    @Override
    public boolean deleteById(int id) throws SQLException {
        return super.deleteById(id);
    }
}
