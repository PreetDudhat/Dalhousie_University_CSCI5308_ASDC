package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.IAnnouncement;
import com.example.BrighterSpace.dataLayer.mocks.implementation.AnnouncementDAOMock;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;


@SpringBootTest(classes = {com.example.BrighterSpace.model.Announcement.class, AnnouncementDAOMock.class})
public class AnnouncementTest {

    @Test
    public void announcementTestFromAllArgsConstructor() {
        Announcement a1 = new Announcement(1, "No Class tomorrow", "There shall be no class tomorrow", 1);
        Assertions.assertEquals(a1.getId(), 1);
        Assertions.assertEquals(a1.getTitle(), "No Class tomorrow");
        Assertions.assertEquals(a1.getDescription(), "There shall be no class tomorrow");
    }

    @Test
    public void createAnnouncementTest() throws SQLException {
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IAnnouncement announcementOperations = new Announcement(factory.getAnnouncementMockFactory().create());
        Announcement announcement = new Announcement(1, "No Class tomorrow", "There shall be no class tomorrow");
        String output = announcementOperations.createAnnouncement(announcement, 1);
        Assertions.assertEquals(output, "Announcement Created!");
    }

    @Test
    public void getAnnouncementByIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IAnnouncement announcementOperations = new Announcement(factory.getAnnouncementMockFactory().create());
        Announcement a1 = announcementOperations.getAnnouncementById(1);
        Assertions.assertEquals(a1.getId(), 1);
        Assertions.assertEquals(a1.getTitle(), "No Class tomorrow");
        Assertions.assertEquals(a1.getDescription(), "There shall be no class tomorrow");
    }

    @Test
    public void updateAnnouncementByIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IAnnouncement announcementOperations = new Announcement(factory.getAnnouncementMockFactory().create());
        Announcement a1 = announcementOperations.getAnnouncementById(1);
        Assertions.assertEquals(a1.getId(), 1);
        Assertions.assertEquals(a1.getTitle(), "No Class tomorrow");
        Assertions.assertEquals(a1.getDescription(), "There shall be no class tomorrow");
    }

    @Test
    public void deleteAnnouncementByIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IAnnouncement announcementOperations = new Announcement(factory.getAnnouncementMockFactory().create());
        String a1 = announcementOperations.deleteAnnouncementById(1);
        Assertions.assertEquals(a1, "Announcement deleted");
    }

    @Test
    public void getAllAnnouncementsByCourseIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IAnnouncement announcementOperations = new Announcement(factory.getAnnouncementMockFactory().create());
        List<Announcement> announcementList = announcementOperations.getAllAnnouncementsByCourseId(1);
        Assertions.assertEquals(announcementList.get(0).getId(), 1);
        Assertions.assertEquals(announcementList.get(0).getTitle(), "No Class tomorrow");
        Assertions.assertEquals(announcementList.get(0).getDescription(), "There shall be no class tomorrow");
    }
}
