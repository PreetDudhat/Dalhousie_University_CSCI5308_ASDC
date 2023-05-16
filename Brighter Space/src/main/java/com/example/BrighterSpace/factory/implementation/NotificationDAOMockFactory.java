package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.mocks.implementation.NotificationDAOMock;
import com.example.BrighterSpace.factory.AbstractFactoryDAOMock;

public class NotificationDAOMockFactory extends AbstractFactoryDAOMock {
    @Override
    public NotificationDAOMock create() {
        return new NotificationDAOMock();
    }
}
