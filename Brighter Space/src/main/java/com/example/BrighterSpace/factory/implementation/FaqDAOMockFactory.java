package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.mocks.implementation.FaqDAOMock;
import com.example.BrighterSpace.factory.AbstractFactoryDAOMock;

public class FaqDAOMockFactory extends AbstractFactoryDAOMock {
    @Override
    public FaqDAOMock create() {
        return new FaqDAOMock();
    }
}
