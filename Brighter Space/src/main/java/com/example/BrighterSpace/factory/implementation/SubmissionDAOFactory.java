package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.dao.SubmissionDAO;
import com.example.BrighterSpace.factory.AbstractFactoryDAO;

public class SubmissionDAOFactory extends AbstractFactoryDAO {
    @Override
    public SubmissionDAO create() {
        return new SubmissionDAO();
    }
}
