package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.dao.FeedbackDAO;
import com.example.BrighterSpace.factory.AbstractFactoryDAO;

public class FeedbackDAOFactory extends AbstractFactoryDAO {
    @Override
    public FeedbackDAO create() {
        return new FeedbackDAO();
    }
}
