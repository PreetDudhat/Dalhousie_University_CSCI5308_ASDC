package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.mocks.implementation.AnnouncementDAOMock;
import com.example.BrighterSpace.factory.AbstractFactoryDAOMock;

public class AnnouncementDAOMockFactory extends AbstractFactoryDAOMock {
    @Override
    public AnnouncementDAOMock create() {
        return new AnnouncementDAOMock();
    }
}
