package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.mocks.implementation.FeedbackDAOMock;
import com.example.BrighterSpace.factory.AbstractFactoryDAOMock;

public class FeedbackDAOMockFactory extends AbstractFactoryDAOMock {
    @Override
    public FeedbackDAOMock create() {
        return new FeedbackDAOMock();
    }
}
