package com.example.BrighterSpace.dataLayer;

import com.example.BrighterSpace.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {

    List<User> getAllUsers() throws SQLException;

    User getUserByEmail(String email) throws SQLException;

    Boolean addUser(User user) throws SQLException;

    User updateUser(User user) throws SQLException;

    List<User> getAllInstructorsWhoHasCourses() throws SQLException;
}
