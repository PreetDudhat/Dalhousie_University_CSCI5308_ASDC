package com.example.BrighterSpace.dataLayer.dao;

import com.example.BrighterSpace.dataLayer.mocks.IAnnouncementDAO;
import com.example.BrighterSpace.model.Announcement;
import org.springframework.context.annotation.Lazy;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDAO implements IAnnouncementDAO {
    @Lazy
    private final Connection conn = com.example.BrighterSpace.config.Connection.getInstance();

    @Override
    public String createAnnouncement(Announcement announcement, int courseId) throws SQLException {
        String query = "{CALL createAnnouncement(?, ?, ?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setString(1, announcement.getTitle());
            statement.setString(2, announcement.getDescription());
            statement.setInt(3, courseId);
            statement.executeQuery();
            return "Announcement added";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    @Override
    public Announcement getAnnouncementById(int announcementId) throws SQLException {
        String query = "{CALL GetAnnouncementById(?)}";
        Announcement announcement = null;
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, announcementId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                announcement = new Announcement(resultSet.getInt("id"),
                        resultSet.getString("title"), resultSet.getString("description"),
                        resultSet.getInt("course_Id"));
            }
            return announcement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    @Override
    public Announcement updateAnnouncementById(Announcement announcement) throws SQLException {
        String query = "{CALL UpdateAnnouncement(?, ?, ?, ?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, announcement.getId());
            statement.setString(2, announcement.getTitle());
            statement.setString(3, announcement.getDescription());
            statement.setInt(4, announcement.getCourseId());
            statement.executeUpdate();
            return announcement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    @Override
    public String deleteAnnouncementById(int announcementId) throws SQLException {
        String query = "{CALL DeleteAnnouncementById(?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, announcementId);
            statement.execute();
            return "Record deleted";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    @Override
    public List<Announcement> getAllAnnouncementsByCourseId(int courseId) throws SQLException {
        List<Announcement> announcements = new ArrayList<>();
        String query = "{CALL GetAllAnnouncementsByCourseId(?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Announcement announcement = new Announcement(resultSet.getInt("id"),
                        resultSet.getString("title"), resultSet.getString("description"),
                        resultSet.getInt("course_id"));
                announcements.add(announcement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            conn.close();
        }
        return announcements;
    }

}
