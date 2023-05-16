package com.example.BrighterSpace.dataLayer.mocks.implementation;

import com.example.BrighterSpace.dataLayer.mocks.INotificationDAO;
import com.example.BrighterSpace.model.Notification;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAOMock implements INotificationDAO {
    @Override
    public List<Notification> getAllNotifications() {
        List<Notification> list = new ArrayList<>();
        String d = "2022-02-02 02:02:02";
        try {
            Notification n1 = new Notification(1, "notify_1", 1, Timestamp.valueOf(d), 1);
            list.add(n1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Notification> getAllNotificationsByCourseId(int courseId) {
        List<Notification> list = new ArrayList<>();
        String d = "2022-02-02 02:02:02";
        try {
            Notification n1 = new Notification(1, "notify_1", courseId, Timestamp.valueOf(d), 1);
            list.add(n1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Notification getNotificationById(int id) {
        String d = "2022-02-02 02:02:02";
        try {

            Notification n1 = new Notification(id, "notify_1", 1, Timestamp.valueOf(d), 1);
            return n1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String createNotification(String text, int courseId, int userId) {
        return "Notification Created";
    }

    @Override
    public String deleteAllNotificationsByCourseId(int courseId) {
        return "Notification Deleted";
    }

    @Override
    public String deleteAllNotificationsByUserId(int userId) {
        return "Notification Deleted";
    }

    @Override
    public List<Notification> getAllNotificationsByUserId(int userId) {
        List<Notification> list = new ArrayList<>();
        String d = "2022-02-02 02:02:02";
            Notification n1 = new Notification(1, "notify_1", 1, Timestamp.valueOf(d), userId);
            list.add(n1);
        return list;
    }
}
