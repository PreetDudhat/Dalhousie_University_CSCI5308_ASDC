package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.dao.EventDAO;
import com.example.BrighterSpace.factory.AbstractFactoryDAO;

public class EventDAOFactory extends AbstractFactoryDAO {
    @Override
    public EventDAO create() {
        return new EventDAO();
    }
}
