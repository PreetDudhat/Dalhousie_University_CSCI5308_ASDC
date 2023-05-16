package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.mocks.implementation.CourseDAOMock;
import com.example.BrighterSpace.factory.AbstractFactoryDAOMock;

public class CourseDAOMockFactory extends AbstractFactoryDAOMock {
    @Override
    public CourseDAOMock create() {
        return new CourseDAOMock();
    }
}
