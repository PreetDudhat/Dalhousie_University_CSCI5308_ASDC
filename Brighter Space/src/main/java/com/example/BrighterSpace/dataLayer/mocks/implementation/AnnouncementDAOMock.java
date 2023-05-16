package com.example.BrighterSpace.dataLayer.mocks.implementation;

import com.example.BrighterSpace.dataLayer.mocks.IAnnouncementDAO;
import com.example.BrighterSpace.model.Announcement;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementDAOMock implements IAnnouncementDAO {


    @Override
    public List<Announcement> getAllAnnouncementsByCourseId(int courseId) {
        Announcement a1 = new Announcement(1, "No Class tomorrow", "There shall be no class tomorrow", 1);
        List<Announcement> list = new ArrayList<>();
        list.add(a1);
        return list;
    }

    @Override
    public String createAnnouncement(Announcement announcement, int courseId) {
        return "Announcement Created!";
    }

    @Override
    public Announcement getAnnouncementById(int announcementId) {
        Announcement a1 = new Announcement(1, "No Class tomorrow", "There shall be no class tomorrow", 1);
        return a1;
    }

    @Override
    public Announcement updateAnnouncementById(Announcement announcement) {
        Announcement a1 = new Announcement(1, "No Class tomorrow", "There shall be no class tomorrow", 2);
        return a1;
    }

    @Override
    public String deleteAnnouncementById(int announcementId) {
        return "Announcement deleted";
    }

}
