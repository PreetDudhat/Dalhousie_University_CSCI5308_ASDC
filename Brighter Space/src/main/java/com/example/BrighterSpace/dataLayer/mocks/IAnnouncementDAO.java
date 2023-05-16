package com.example.BrighterSpace.dataLayer.mocks;

import com.example.BrighterSpace.model.Announcement;

import java.sql.SQLException;
import java.util.List;

public interface IAnnouncementDAO {
    List<Announcement> getAllAnnouncementsByCourseId(int courseId) throws SQLException;
    String createAnnouncement(Announcement announcement, int courseId) throws SQLException;
    Announcement getAnnouncementById(int announcementId) throws SQLException;
    Announcement updateAnnouncementById(Announcement announcement) throws SQLException;
    String deleteAnnouncementById(int announcementId) throws SQLException;
}
