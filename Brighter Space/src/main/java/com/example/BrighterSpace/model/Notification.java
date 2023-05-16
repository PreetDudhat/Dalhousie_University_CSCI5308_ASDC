package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.INotification;
import com.example.BrighterSpace.dataLayer.mocks.INotificationDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Notification implements INotification {
    private int id;
    private String text;
    private Date time;
    private Integer course_id;
    private Integer user_id;

    private INotificationDAO notificationDAO;

    public Notification(int id, String text, Integer course_id, Date time, Integer user_id) {
        this.id = id;
        this.text = text;
        this.course_id = course_id;
        this.time = time;
        this.user_id = user_id;
    }

    public Notification() {

    }

    public Notification(INotificationDAO doa) {
        this.notificationDAO = doa;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }


    @Override
    public List<Notification> getAllNotifications() throws SQLException {
        return notificationDAO.getAllNotifications();
    }

    @Override
    public List<Notification> getAllNotificationsByCourseId(int courseId) throws SQLException {
        return notificationDAO.getAllNotificationsByCourseId(courseId);
    }

    @Override
    public Notification getNotificationById(int id) throws SQLException {
        return notificationDAO.getNotificationById(id);
    }

    @Override
    public String createNotification(String text, int courseId, int userId) throws SQLException {
        return notificationDAO.createNotification(text, courseId, userId);
    }

    @Override
    public String deleteAllNotificationsByCourseId(int courseId) throws SQLException {
        return notificationDAO.deleteAllNotificationsByCourseId(courseId);
    }

    @Override
    public String deleteAllNotificationsByUserId(int userId) throws SQLException {
        return notificationDAO.deleteAllNotificationsByUserId(userId);
    }

    @Override
    public List<Notification> getAllNotificationsByUserId(int userId) throws SQLException {
        return notificationDAO.getAllNotificationsByUserId(userId);
    }
}
