package com.example.BrighterSpace.dataLayer;

import com.example.BrighterSpace.model.Assignment;

import java.sql.SQLException;
import java.util.List;

public interface IAssignment {
    List<Assignment> getAllAssignmentsById(int course_id) throws SQLException;
    String createAssignment(Assignment assignment, int course_id, int user_id) throws SQLException;
    Assignment getAssignmentById(int assignmentId) throws SQLException;
    Assignment updateAssignmentById(Assignment assignment) throws SQLException;

    String deleteAssignmentById(int id) throws SQLException;
}
