package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.dao.UserDAO;
import com.example.BrighterSpace.factory.AbstractFactoryDAO;

public class UserDAOFactory extends AbstractFactoryDAO {
    @Override
    public UserDAO create() {
        return new UserDAO();
    }
}
