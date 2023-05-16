package com.example.BrighterSpace.dataLayer;

import com.example.BrighterSpace.model.Submission;
import com.example.BrighterSpace.model.User;
import com.example.BrighterSpace.model.dto.SubmissionDTO;

import java.sql.SQLException;
import java.util.List;

public interface ISubmission {
    List<Submission> getAllSubmissionsById(int user_id,int assignment_id, int course_id) throws SQLException;

    String createSubmission(Submission submission, int user_id, int assignment_id,int course_id) throws SQLException;
    Submission updateSubmissionById(Submission submission) throws SQLException;
    Submission getSubmissionById(int submissionId) throws SQLException;

    List<SubmissionDTO> getAllSubmissionsByCourse(int courseId, int assignmentId) throws SQLException;

    List<User> getAllUsersFromSubmissions(int courseId, int assignmentId) throws SQLException;

    List<Submission> getAllUserSubmissionByCourse(int assignmentId, Integer id);
}
