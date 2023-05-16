package com.example.BrighterSpace.dataLayer.dao;

import com.example.BrighterSpace.dataLayer.mocks.IAssignmentDAO;
import com.example.BrighterSpace.model.Assignment;
import org.springframework.context.annotation.Lazy;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AssignmentDAO implements IAssignmentDAO {
    @Lazy
    private final Connection conn = com.example.BrighterSpace.config.Connection.getInstance();

    public String createAssignment (Assignment assignment, int course_id, int user_id) throws SQLException {
        String query = "{CALL CreateAssignment(?, ?, ?, ?, ?, ?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setString(1, assignment.getAssignmentName());
            statement.setString(2, assignment.getAssignmentDescription());
            statement.setDate(3, assignment.getAssignmentDeadline());
            statement.setFloat(4, Float.parseFloat(assignment.getAssignmentTotalScore()));
            statement.setInt(5, course_id);
            statement.setInt(6, user_id);
            statement.executeQuery();
            return "Assignment added";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public Assignment getAssignmentById(int assignmentId) throws SQLException {
        String query = "{CALL GetAssignmentById(?)}";
        Assignment assignment = null;
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, assignmentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                assignment = new Assignment(resultSet.getInt("assignmentId"),
                        resultSet.getString("assignmentName"),
                        resultSet.getString("assignmentDescription"),
                        resultSet.getDate("assignmentDeadline"),
                        String.valueOf(resultSet.getFloat("assignmentTotalScore")),
                        resultSet.getInt("course_id"),
                        resultSet.getInt("user_id")
                );
            }
            return assignment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public Assignment updateAssignmentById(Assignment assignment) throws SQLException {
        String query = "{CALL UpdateAssignment(?, ?, ?, ?, ?, ?, ?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, assignment.getAssignmentId());
            statement.setString(2, assignment.getAssignmentName());
            statement.setString(3, assignment.getAssignmentDescription());
            statement.setDate(4, assignment.getAssignmentDeadline());
            statement.setFloat(5, Float.parseFloat(assignment.getAssignmentTotalScore()));
            statement.setInt(6, assignment.getCourse_id());
            statement.setInt(7, assignment.getUser_id());
            statement.executeUpdate();
            return assignment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    @Override
    public String deleteAssignmentById(int assignmentId) throws SQLException {
        String query = "{CALL deleteAssignmentById(?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, assignmentId);
            statement.execute();
            return "Record deleted";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public List<Assignment> getAllAssignmentsById(int course_id) throws SQLException {
        List<Assignment> assignments = new ArrayList<>();
        String query = "{CALL GetAllAssignmentsByCourseId(?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, course_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Assignment assignment = new Assignment(resultSet.getInt("assignmentId"),
                        resultSet.getString("assignmentName"),
                        resultSet.getString("assignmentDescription"),
                        resultSet.getDate("assignmentDeadline"),
                        String.valueOf(resultSet.getFloat("assignmentTotalScore")),
                        resultSet.getInt("course_id"),
                        resultSet.getInt("user_id")
                );

                assignments.add(assignment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
        return assignments;
    }
}
