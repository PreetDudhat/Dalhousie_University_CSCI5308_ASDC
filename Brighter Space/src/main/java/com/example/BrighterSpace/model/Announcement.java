package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.IAnnouncement;
import com.example.BrighterSpace.dataLayer.dao.AnnouncementDAO;
import com.example.BrighterSpace.dataLayer.mocks.IAnnouncementDAO;

import java.sql.SQLException;
import java.util.List;

public class Announcement implements IAnnouncement {
    private int id;
    private String title;
    private String description;
    private Integer courseId;
    private IAnnouncementDAO announcementDAO;

    public Announcement(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Announcement(int id, String title, String description, int courseId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.courseId = courseId;
    }

    public Announcement(IAnnouncementDAO announcementDAO) {
        this.announcementDAO = announcementDAO;
    }
    public Announcement() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IAnnouncementDAO getAnnouncementDOA() {
        return announcementDAO;
    }

    public void setAnnouncementDOA(AnnouncementDAO announcementDAO) {
        this.announcementDAO = announcementDAO;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public List<Announcement> getAllAnnouncementsByCourseId(int courseId) throws SQLException {
        return announcementDAO.getAllAnnouncementsByCourseId(courseId);
    }

    @Override
    public String createAnnouncement(Announcement announcement, int courseId) throws SQLException {
        return announcementDAO.createAnnouncement(announcement, courseId);
    }

    @Override
    public Announcement getAnnouncementById(int id) throws SQLException {
        return announcementDAO.getAnnouncementById(id);
    }

    @Override
    public Announcement updateAnnouncementById(Announcement announcement) throws SQLException {
        return announcementDAO.updateAnnouncementById(announcement);
    }

    @Override
    public String deleteAnnouncementById(int id) throws SQLException {
        return announcementDAO.deleteAnnouncementById(id);
    }

}
