package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.dao.CourseDAO;
import com.example.BrighterSpace.factory.AbstractFactoryDAO;

public class CourseDAOFactory extends AbstractFactoryDAO {
    @Override
    public CourseDAO create() {
        return new CourseDAO();
    }
}
