package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.dao.AssignmentDAO;
import com.example.BrighterSpace.factory.AbstractFactoryDAO;

public class AssignmentDAOFactory extends AbstractFactoryDAO {
    @Override
    public AssignmentDAO create() {
        return new AssignmentDAO();
    }
}
