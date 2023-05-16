package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.dao.NotificationDAO;
import com.example.BrighterSpace.factory.AbstractFactoryDAO;

public class NotificationDAOFactory extends AbstractFactoryDAO {
    @Override
    public NotificationDAO create() {
        return new NotificationDAO();
    }
}
