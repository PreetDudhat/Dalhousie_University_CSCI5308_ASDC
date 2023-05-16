package com.example.BrighterSpace.dataLayer.mocks;

import com.example.BrighterSpace.model.Assignment;

import java.sql.SQLException;
import java.util.List;


public interface IAssignmentDAO {
    List<Assignment> getAllAssignmentsById(int courseId) throws SQLException;
    String createAssignment(Assignment assignment, int courseId, int user_id) throws SQLException;

    Assignment getAssignmentById(int assignmentId) throws SQLException;
    Assignment updateAssignmentById(Assignment assignment) throws SQLException;
    String deleteAssignmentById(int assignmentId) throws SQLException;
}

