package com.example.BrighterSpace.dataLayer.mocks.implementation;

import com.example.BrighterSpace.dataLayer.mocks.IAssignmentDAO;
import com.example.BrighterSpace.model.Assignment;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAOMock implements IAssignmentDAO {
    @Override
    public List<Assignment> getAllAssignmentsById(int courseId){
        List<Assignment> list = new ArrayList<>();
        Assignment a1 = new Assignment(1,"Test1","Lets Complete it",Date.valueOf("2022-09-09"),"91",1,1);
        list.add(a1);
        return list;
    }
    @Override
    public String createAssignment(Assignment assignment, int courseId, int user_id) {
        return "Assignment Created";
    }

    @Override
    public Assignment getAssignmentById(int assignmentId) {
        Assignment a1 = new Assignment(1,"Test1","Lets Complete it",Date.valueOf("2022-09-09"),"91",1,1);
        return a1;
    }

    @Override
    public Assignment updateAssignmentById(Assignment assignment) {
        Assignment a1 = new Assignment(1,"Test1","Lets Complete it",Date.valueOf("2022-09-09"),"91",1,1);
        return a1;
    }

    @Override
    public String deleteAssignmentById(int assignmentId) {
        return null;
    }
}
