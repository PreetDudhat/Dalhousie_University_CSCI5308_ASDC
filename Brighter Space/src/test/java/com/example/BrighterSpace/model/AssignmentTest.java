package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.IAssignment;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Date;
import java.sql.SQLException;

public class AssignmentTest{
    @Test
    public void getAllAssignmentsTestById() {
        Assignment a1 = new Assignment(1,"Test1","Lets Complete it",Date.valueOf("2022-09-09"),"91",1,1);
        Assertions.assertEquals(a1.getAssignmentId(), 1);
        Assertions.assertEquals(a1.getAssignmentName(), "Test1");
        Assertions.assertEquals(a1.getAssignmentDescription(), "Lets Complete it");
        Assertions.assertEquals(a1.getAssignmentDeadline(), Date.valueOf("2022-09-09"));
        Assertions.assertEquals(a1.getAssignmentTotalScore(), "91");
        Assertions.assertEquals(a1.getCourse_id(),1);
        Assertions.assertEquals(a1.getUser_id(),1);
    }
    @Test
    public void createAssignmentTest()throws SQLException {
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IAssignment assignmentOperations =new Assignment(factory.getAssignmentMockFactory().create());
        Assignment a1 = new Assignment(1,"Test1","Lets Complete it",Date.valueOf("2022-09-09"),"91",1,1);
        String output = assignmentOperations.createAssignment(a1,1,1);
        Assertions.assertEquals(output, "Assignment Created");

    }
    @Test
    public void getAssignmentByIdTest()throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IAssignment assignmentOperations =new Assignment(factory.getAssignmentMockFactory().create());
        Assignment a1 = assignmentOperations.getAssignmentById(1);
        Assertions.assertEquals(a1.getAssignmentId(), 1);
        Assertions.assertEquals(a1.getAssignmentName(), "Test1");
        Assertions.assertEquals(a1.getAssignmentDescription(), "Lets Complete it");
        Assertions.assertEquals(a1.getAssignmentDeadline(), Date.valueOf("2022-09-09"));
        Assertions.assertEquals(a1.getAssignmentTotalScore(), "91");
        Assertions.assertEquals(a1.getCourse_id(),1);
        Assertions.assertEquals(a1.getUser_id(),1);
    }
    @Test
    public void updateAssignmentByIdTest()throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IAssignment assignmentOperations =new Assignment(factory.getAssignmentMockFactory().create());
        Assignment a = assignmentOperations.getAssignmentById(1);
        Assignment a1 = assignmentOperations.updateAssignmentById(a);
        Assertions.assertEquals(a1.getAssignmentId(), 1);
        Assertions.assertEquals(a1.getAssignmentName(), "Test1");
        Assertions.assertEquals(a1.getAssignmentDescription(), "Lets Complete it");
        Assertions.assertEquals(a1.getAssignmentDeadline(), Date.valueOf("2022-09-09"));
        Assertions.assertEquals(a1.getAssignmentTotalScore(), "91");
        Assertions.assertEquals(a1.getCourse_id(),1);
        Assertions.assertEquals(a1.getUser_id(),1);
    }
}
