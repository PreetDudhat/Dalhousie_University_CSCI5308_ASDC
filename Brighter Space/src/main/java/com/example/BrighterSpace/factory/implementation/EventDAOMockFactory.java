package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.mocks.implementation.EventDAOMock;
import com.example.BrighterSpace.factory.AbstractFactoryDAOMock;

public class EventDAOMockFactory extends AbstractFactoryDAOMock {
    @Override
    public EventDAOMock create() {
        return new EventDAOMock();
    }
}
