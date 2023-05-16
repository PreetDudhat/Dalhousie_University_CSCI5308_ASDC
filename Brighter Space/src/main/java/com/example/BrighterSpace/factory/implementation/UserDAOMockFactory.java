package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.mocks.implementation.UserDAOMock;
import com.example.BrighterSpace.factory.AbstractFactoryDAOMock;

public class UserDAOMockFactory extends AbstractFactoryDAOMock {

    @Override
    public UserDAOMock create() {
        return new UserDAOMock();
    }
}
