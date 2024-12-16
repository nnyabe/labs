package com.example.library_management_system.interfaces.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface defining CRUD operations for model entities.
 * Implementations should handle specific model logic.
 *
 * @param <T> the type of the model class this controller handles
 */
public interface BaseInterface<T> {

    List<T> getAll() throws SQLException;

    T getById(int id) throws SQLException;

    boolean deleteById(int id) throws SQLException;

    boolean updateById(T model) throws SQLException;

    boolean createOne(T model) throws SQLException;

    T mapRowToModel(ResultSet resultSet) throws SQLException;

    String getTableName();

    String getUpdateQuery();

    String getCreateQuery();

    void setUpdateParameters(PreparedStatement preparedStatement, T model) throws SQLException;

    void setCreateParameters(PreparedStatement preparedStatement, T model) throws SQLException;
}
