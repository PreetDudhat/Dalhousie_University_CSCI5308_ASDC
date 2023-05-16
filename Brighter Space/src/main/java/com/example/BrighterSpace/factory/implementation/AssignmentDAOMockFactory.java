package com.example.BrighterSpace.factory.implementation;

import com.example.BrighterSpace.dataLayer.mocks.implementation.AssignmentDAOMock;
import com.example.BrighterSpace.factory.AbstractFactoryDAOMock;
public class AssignmentDAOMockFactory extends AbstractFactoryDAOMock {
    @Override

    public AssignmentDAOMock create() {
        return new AssignmentDAOMock();
    }
}
