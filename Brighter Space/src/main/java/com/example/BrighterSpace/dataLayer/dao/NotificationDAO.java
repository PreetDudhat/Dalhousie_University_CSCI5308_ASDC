package com.example.BrighterSpace.dataLayer.dao;

import com.example.BrighterSpace.dataLayer.mocks.INotificationDAO;
import com.example.BrighterSpace.model.Notification;
import org.springframework.context.annotation.Lazy;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationDAO implements INotificationDAO {
    @Lazy
    private final Connection conn = com.example.BrighterSpace.config.Connection.getInstance();

    public List<Notification> getAllNotifications() throws SQLException {
        List<Notification> notifications = new ArrayList<>();
        String query = "{CALL GetAllNotifications()}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Notification notification = new Notification(resultSet.getInt("id"),
                        resultSet.getString("text"), resultSet.getInt("course_Id"), resultSet.getTimestamp("time"),
                        resultSet.getInt("user_id"));
                notifications.add(notification);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
        return notifications;
    }

    public Notification getNotificationById(int id) throws SQLException {
        String query = "{CALL GetNotificationById(?)}";
        Notification notification = null;
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                notification = new Notification(resultSet.getInt("id"),
                        resultSet.getString("text"), resultSet.getInt("course_Id"),
                        resultSet.getTimestamp("time"), resultSet.getInt("user_id"));
            }
            return notification;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public String createNotification(String text, int course_id, int user_id) throws SQLException {
        String query = "{CALL createNotification(?, ?, ?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setString(1, text);
            statement.setInt(2, course_id);
            statement.setInt(3, user_id);
            statement.executeQuery();
            return "Notification created";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public List<Notification> getAllNotificationsByCourseId(int courseId) throws SQLException {
        List<Notification> notifications = new ArrayList<>();
        String query = "{CALL GetAllNotificationsByCourseId(?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Notification notification = new Notification(resultSet.getInt("id"),
                        resultSet.getString("text"), resultSet.getInt("course_id"),
                        resultSet.getTimestamp("time"), resultSet.getInt("user_id"));
                notifications.add(notification);
            }
            Collections.reverse(notifications);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
        return notifications;
    }

    public String deleteAllNotificationsByCourseId(int courseId) throws SQLException {
        String query = "{CALL DeleteAllNotificationsByCourseCode(?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, courseId);
            statement.execute();
            return "Record deleted";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public String deleteAllNotificationsByUserId(int userId) throws SQLException {
        String query = "{CALL DeleteAllNotificationsByUserId(?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, userId);
            statement.execute();
            return "Record deleted";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public List<Notification> getAllNotificationsByUserId(int userId) throws SQLException {
        List<Notification> notifications = new ArrayList<>();
        String query = "{CALL GetAllNotificationsByUserId(?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Notification notification = new Notification(resultSet.getInt("id"),
                        resultSet.getString("text"), resultSet.getInt("course_Id"),
                        resultSet.getTimestamp("time"), resultSet.getInt("user_id"));
                notifications.add(notification);
            }
            Collections.reverse(notifications);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
        return notifications;
    }

    public String createNotificationByUserId(String text, int userId, int courseId) throws SQLException {
        String query = "{CALL createNotificationByUserId(?, ?, ?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setString(1, text);
            statement.setInt(2, courseId);
            statement.setInt(3, userId);
            statement.executeQuery();
            return "Notification created";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }
}
