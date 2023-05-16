package com.example.BrighterSpace.factory.implementation;


import com.example.BrighterSpace.dataLayer.dao.FaqDAO;
import com.example.BrighterSpace.factory.AbstractFactoryDAO;

public class FaqDAOFactory extends AbstractFactoryDAO {
    @Override
    public FaqDAO create() {
        return new FaqDAO();
    }
}
