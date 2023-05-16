package com.example.BrighterSpace.dataLayer.dao;

import com.example.BrighterSpace.model.Submission;
import com.example.BrighterSpace.model.User;
import com.example.BrighterSpace.model.dto.SubmissionDTO;
import org.springframework.context.annotation.Lazy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubmissionDAO {
    @Lazy
    private final Connection connection = com.example.BrighterSpace.config.Connection.getInstance();

    public String createSubmission(Submission submission,
                                   int user_id, int assignment_id, int course_id) throws SQLException {
        String query = "{CALL CreateSubmission(?, ?, ?, ?, ?, ?)}";
        try {
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1, submission.getSubmissionLink());
            statement.setFloat(2, 0);
            statement.setBoolean(3, false);
            statement.setInt(4, user_id);
            statement.setInt(5, assignment_id);
            statement.setInt(6, course_id);
            statement.executeQuery();
            return "Submission added";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }

    public Submission updateSubmissionById(Submission submission) throws SQLException{
        String query = "{CALL updateSubmissionById(?, ?, ?, ?, ?, ?)}";
        try {
            CallableStatement statement = connection.prepareCall(query);
            statement.setInt(1, submission.getSubmissionId());
            statement.setString(2, submission.getSubmissionLink());
            statement.setFloat(3, Float.parseFloat(submission.getReceivedScore()));
            statement.setBoolean(4, true);
            statement.setInt(5, submission.getAssignment_id());
            statement.setInt(6, submission.getCourse_id());
            statement.executeUpdate();
            return submission;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }

    public Submission getSubmissionById(int submissionId) throws SQLException {
        String query = "{CALL GetSubmissionById(?)}";
        Submission submission = null;
        try {
            CallableStatement statement = connection.prepareCall(query);
            statement.setInt(1, submissionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                submission = new Submission(resultSet.getInt("submissionId"),
                        resultSet.getString("submissionLink"),
                        String.valueOf(resultSet.getFloat("receivedScore")),
                        resultSet.getBoolean("isMarked"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("assignment_id"),
                        resultSet.getInt("course_id")

                );
            }
            return submission;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }

    public List<Submission> getAllSubmissionsById(int user_id, int assignment_id, int course_id) throws SQLException {
        List<Submission> submissions = new ArrayList<>();
        String query = "{CALL GetAllSubmissionsById(?,?,?)}";
        try {
            CallableStatement statement = connection.prepareCall(query);
            statement.setInt(1, user_id);
            statement.setInt(2, assignment_id);
            statement.setInt(3, course_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Submission submission = new Submission(resultSet.getInt("submissionId"),
                        resultSet.getString("submissionLink"),
                        String.valueOf(resultSet.getFloat("receivedScore")),
                        resultSet.getBoolean("isMarked"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("assignment_id"),
                        resultSet.getInt("course_id")
                );

                submissions.add(submission);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        return submissions;
    }

    public List<SubmissionDTO> getAllSubmissionsByCourse(int courseId, int assignmentId) throws SQLException {
        List<SubmissionDTO> submissions = new ArrayList<>();
        String query = "{CALL GetAllSubmissionsWithUserNames(?)}";
        try {
            CallableStatement statement = connection.prepareCall(query);
            statement.setInt(1, assignmentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SubmissionDTO submission = new SubmissionDTO(resultSet.getInt("submissionId"),
                        resultSet.getString("submissionLink"),
                        resultSet.getFloat("receivedScore"),
                        resultSet.getBoolean("isMarked"),
                        resultSet.getInt("assignment_id"),
                        resultSet.getString("user_name")
                );
                submissions.add(submission);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        return submissions;
    }

    public List<User> getAllUsersFromSubmissions(int courseId, int assignmentId) throws SQLException {
        String query = "{CALL GetAllSubmissionsUser(?, ?)}";
        try {
            CallableStatement statement = connection.prepareCall(query);
            statement.setInt(1, courseId);
            statement.setInt(2, assignmentId);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("user_email"));
                user.setPassword(resultSet.getString("user_password"));
                user.setRole(resultSet.getString("user_role"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }

    public List<Submission> getAllUserSubmissionByCourse(int assignmentId, Integer userId) {
        try {
            PreparedStatement statement = connection.prepareStatement("{CALL getAllUserSubmissionByAssignmentId(?,?)}");
            statement.setInt(1, assignmentId);
            statement.setInt(2, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Submission> submissions = new ArrayList<>();
            while (resultSet.next()) {
                Submission submission = new Submission();
                submission.setSubmissionId(resultSet.getInt(1));
                submission.setSubmissionLink(resultSet.getString(2));
                submission.setReceivedScore(String.valueOf(resultSet.getDouble(3)));
                submission.setMarked(resultSet.getBoolean(4));
                submission.setUser_id(resultSet.getInt(5));
                submission.setAssignment_id(resultSet.getInt(6));
                submission.setCourse_id(resultSet.getInt(7));
                submissions.add(submission);
            }
            return submissions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
