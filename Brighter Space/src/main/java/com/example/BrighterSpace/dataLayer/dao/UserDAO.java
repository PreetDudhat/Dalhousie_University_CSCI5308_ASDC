package com.example.BrighterSpace.dataLayer.dao;

import com.example.BrighterSpace.dataLayer.IUserDAO;
import com.example.BrighterSpace.model.User;
import org.springframework.context.annotation.Lazy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    @Lazy
    private final Connection connection = com.example.BrighterSpace.config.Connection.getInstance();

    public List<User> getAllUsers() throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("{CALL getAllUsers()}");
            ResultSet resultSet = statement.executeQuery();
            return makeUserListFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            connection.close();
        }
    }

    public User getUserByEmail(String email) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareCall("{CALL getUserByEmail(?)}");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("user_email"));
                user.setPassword(resultSet.getString("user_password"));
                user.setRole(resultSet.getString("user_role"));
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            connection.close();
        }
    }

    public Boolean addUser(User user) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareCall("{CALL addUser(?, ?, ?, ?)}");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            connection.close();
        }
    }

    public User updateUser(User user) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareCall("{CALL updateUser(?,?,?)}");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getRole());
            statement.executeQuery();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            connection.close();
        }
    }

    public List<User> getAllInstructorsWhoHasCourses() throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("{CALL getInstructorsWhoHasCourses()}");
            ResultSet resultSet = statement.executeQuery();
            return makeUserListFromResultSet(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            connection.close();
        }
    }

    private List<User> makeUserListFromResultSet(ResultSet resultSet) throws Exception{
        User user;
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("user_name"));
            user.setEmail(resultSet.getString("user_email"));
            user.setRole(resultSet.getString("user_role"));
            users.add(user);
        }
        return users;
    }
}
