package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.dao.AnnouncementDAO;
import com.example.BrighterSpace.factory.AbstractFactoryDAO;

public class AnnouncementDAOFactory extends AbstractFactoryDAO {
    @Override
    public AnnouncementDAO create() {
        return new AnnouncementDAO();
    }
}
