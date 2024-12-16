package com.example.library_management_system.controllers;

import com.example.library_management_system.modles.PatronModel;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller class for managing patrons in the library system.
 * This class handles CRUD operations for patron records in the database.
 */
public class PatronController extends BaseModelController<PatronModel> {


    @Override
    protected PatronModel mapRowToModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        Date createdAt = resultSet.getDate("created_at");
        Date updatedAt = resultSet.getDate("updated_at");
        return new PatronModel(id, username, email, createdAt, updatedAt);
    }


    @Override
    protected String getTableName() {
        return "patrons";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE patrons SET  username = ?, email = ?" +
                "WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, PatronModel patron)
            throws SQLException {
        preparedStatement.setString(1, patron.getUsername());
        preparedStatement.setString(2, patron.getEmail());
        preparedStatement.setInt(3, patron.getId());
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO patrons (username, email)" +
                " VALUES ( ?, ?)";
    }


    @Override
    protected void setCreateParameters(PreparedStatement preparedStatement, PatronModel patron)
            throws SQLException {
        preparedStatement.setString(1, patron.getUsername());
        preparedStatement.setString(2, patron.getEmail());
    }


    @Override
    public boolean createOne(PatronModel patron) throws SQLException {
        return super.createOne(patron);
    }

    @Override
    public List<PatronModel> getAll() throws SQLException {
        return super.getAll();
    }


    @Override
    public PatronModel getById(int id) throws SQLException {
        return super.getById(id);
    }


    @Override
    public boolean deleteById(int id) throws SQLException {
        return super.deleteById(id);
    }


    @Override
    public boolean updateById(PatronModel patron) throws SQLException {
        return super.updateById(patron);
    }
}
