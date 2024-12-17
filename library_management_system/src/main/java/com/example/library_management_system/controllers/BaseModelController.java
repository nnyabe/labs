package com.example.library_management_system.controllers;

import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base controller class providing CRUD operations for model entities.
 * Subclasses should implement abstract methods to handle specific model logic.
 *
 * @param <T> the type of the model class this controller handles
 */
public abstract class BaseModelController<T> {

    protected abstract T mapRowToModel(ResultSet resultSet) throws SQLException;


    public List<T> getAll() throws SQLException {
        List<T> results = new ArrayList<>();
        String query = "SELECT * FROM " + getTableName();

        System.out.println("No problem at this point");
        try (Connection connection = DBConnection.createConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                T newEntity = mapRowToModel(result);
                results.add(newEntity);
            }
        } catch (MySQLConnectionException e) {
            throw new SQLException("Error fetching data: " + e.getMessage());
        }

        return results;
    }

    public T getById(int id) throws SQLException {
        String query = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        T model = null;

        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    model = mapRowToModel(result);
                }
            }
        } catch (MySQLConnectionException e) {
            throw new SQLException("Error fetching data by ID: " + e.getMessage());
        }

        return model;
    }

    public boolean deleteById(int id) throws SQLException {
        String query = "DELETE FROM " + getTableName() + " WHERE id = ?";
        int rowsAffected = 0;

        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            rowsAffected = preparedStatement.executeUpdate();
        } catch (MySQLConnectionException e) {
            throw new SQLException("Error deleting record: " + e.getMessage());
        }

        return rowsAffected > 0;
    }

    public boolean updateById(T model) throws SQLException {
        String query = getUpdateQuery();
        int rowsAffected = 0;

        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setUpdateParameters(preparedStatement, model);  // Set the parameters using the model
            rowsAffected = preparedStatement.executeUpdate();
        } catch (MySQLConnectionException e) {
            throw new SQLException("Error updating record: " + e.getMessage());
        }

        return rowsAffected > 0;
    }

    public boolean createOne(T model) throws SQLException {
        String query = getCreateQuery();
        int rowAffected;
        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setCreateParameters(preparedStatement, model);
            rowAffected = preparedStatement.executeUpdate();
        } catch (MySQLConnectionException e) {
            throw new SQLException("Error Occured Creating new record: " + e.getMessage());
        }

        return rowAffected > 0;
    }

    protected abstract String getTableName();

    protected abstract String getUpdateQuery();

    protected abstract String getCreateQuery();


    protected abstract void setUpdateParameters(PreparedStatement preparedStatement, T model)
            throws SQLException;


    protected abstract void setCreateParameters(PreparedStatement preparedStatement, T model)
            throws SQLException;
}
