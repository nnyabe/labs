package com.example.library_management_system.controllers;

import com.example.library_management_system.modles.AdminModel;
import com.example.library_management_system.modles.Enums;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller class for handling admin-related operations such as creating, updating, retrieving, and deleting admin records in the database.
 * This class extends the BaseModelController to provide CRUD operations for the AdminModel.
 */
public class AdminController extends BaseModelController<AdminModel> {

    /**
     * Maps a row from the ResultSet to an AdminModel object.
     *
     * @param resultSet the ResultSet containing the data to be mapped
     * @return an AdminModel object populated with the data from the ResultSet
     * @throws SQLException if there is an error accessing the data from the ResultSet
     */
    @Override
    protected AdminModel mapRowToModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        Enums.Roles role = Enums.Roles.valueOf(resultSet.getString("role"));
        Date createdAt = resultSet.getDate("created_at");
        Date updatedAt = resultSet.getDate("updated_at");
        return new AdminModel(id, username, email, createdAt, updatedAt, role, password);
    }

    /**
     * Returns the name of the table this controller is interacting with.
     *
     * @return the name of the table ("admins")
     */
    @Override
    protected String getTableName() {
        return "admins";
    }

    /**
     * Returns the SQL query string to update an admin's details in the database.
     *
     * @return the SQL query string for updating an admin
     */
    @Override
    protected String getUpdateQuery() {
        return "UPDATE admins SET  username = ?, email = ?, role = ? , password = ? " +
                "WHERE id = ?";
    }

    /**
     * Returns the SQL query string to insert a new admin into the database.
     *
     * @return the SQL query string for creating a new admin
     */
    @Override
    protected String getCreateQuery() {
        return "INSERT INTO patrons (username, email, role, password)" +
                " VALUES ( ?, ?, ?, ?)";
    }

    /**
     * Sets the parameters for the SQL update query to update an admin's details.
     *
     * @param preparedStatement the PreparedStatement object to set the parameters on
     * @param admin the AdminModel object containing the updated admin data
     * @throws SQLException if there is an error setting the parameters on the PreparedStatement
     */
    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, AdminModel admin)
            throws SQLException {
        preparedStatement.setString(1, admin.getUsername());
        preparedStatement.setString(2, admin.getEmail());
        preparedStatement.setString(3, admin.getRole().name());
        preparedStatement.setString(4, admin.getPassword());
        preparedStatement.setInt(5, admin.getId());
    }

    /**
     * Sets the parameters for the SQL create query to insert a new admin.
     *
     * @param preparedStatement the PreparedStatement object to set the parameters on
     * @param admin the AdminModel object containing the admin data to be inserted
     * @throws SQLException if there is an error setting the parameters on the PreparedStatement
     */
    @Override
    protected void setCreateParameters(PreparedStatement preparedStatement, AdminModel admin)
            throws SQLException {
        preparedStatement.setString(1, admin.getUsername());
        preparedStatement.setString(2, admin.getEmail());
        preparedStatement.setString(3, admin.getRole().name());
        preparedStatement.setString(4, admin.getPassword());
    }

    /**
     * Creates a new admin record in the database.
     *
     * @param admin the AdminModel object containing the admin data to be inserted
     * @return true if the admin was successfully created, false otherwise
     * @throws SQLException if there is an error executing the SQL query
     */
    @Override
    public boolean createOne(AdminModel admin) throws SQLException {
        return super.createOne(admin);
    }

    /**
     * Retrieves all admin records from the database.
     *
     * @return a list of AdminModel objects representing all the admins in the database
     * @throws SQLException if there is an error executing the SQL query
     */
    @Override
    public List<AdminModel> getAll() throws SQLException {
        return super.getAll();
    }

    /**
     * Retrieves an admin record by its ID from the database.
     *
     * @param id the ID of the admin to retrieve
     * @return an AdminModel object representing the admin, or null if no admin is found
     * @throws SQLException if there is an error executing the SQL query
     */
    @Override
    public AdminModel getById(int id) throws SQLException {
        return super.getById(id);
    }

    /**
     * Deletes an admin record by its ID from the database.
     *
     * @param id the ID of the admin to delete
     * @return true if the admin was successfully deleted, false otherwise
     * @throws SQLException if there is an error executing the SQL query
     */
    @Override
    public boolean deleteById(int id) throws SQLException {
        return super.deleteById(id);
    }

    /**
     * Updates an existing admin record in the database.
     *
     * @param admin the AdminModel object containing the updated admin data
     * @return true if the admin was successfully updated, false otherwise
     * @throws SQLException if there is an error executing the SQL query
     */
    @Override
    public boolean updateById(AdminModel admin) throws SQLException {
        return super.updateById(admin);
    }
}
