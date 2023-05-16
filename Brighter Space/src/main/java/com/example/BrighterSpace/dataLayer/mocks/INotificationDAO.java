package com.example.BrighterSpace.dataLayer.mocks;

import com.example.BrighterSpace.model.Notification;

import java.sql.SQLException;
import java.util.List;

public interface INotificationDAO {
    List<Notification> getAllNotifications() throws SQLException;
    List<Notification> getAllNotificationsByCourseId(int courseId) throws SQLException;
    Notification getNotificationById(int id) throws SQLException;
    String createNotification(String text, int courseId, int userId) throws SQLException;

    String deleteAllNotificationsByCourseId(int courseId) throws SQLException;

    String deleteAllNotificationsByUserId(int userId) throws SQLException;

    List<Notification> getAllNotificationsByUserId(int id) throws SQLException;
}
