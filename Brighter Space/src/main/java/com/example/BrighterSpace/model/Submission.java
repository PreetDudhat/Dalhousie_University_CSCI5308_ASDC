package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.ISubmission;
import com.example.BrighterSpace.dataLayer.dao.SubmissionDAO;
import com.example.BrighterSpace.model.dto.SubmissionDTO;

import java.sql.SQLException;
import java.util.List;

public class Submission implements ISubmission {

    private int submissionId;
    private String submissionLink;
    private String receivedScore;
    private boolean isMarked;
    private SubmissionDAO submissionDAO;
    private Integer user_id;
    private Integer assignment_id;
    private Integer course_id;

    public Submission(int submissionId, String submissionLink,
                      String receivedScore, boolean isMarked, int user_id, int assignment_id, int course_id) {
        this.submissionId = submissionId;
        this.submissionLink = submissionLink;
        this.receivedScore = receivedScore;
        this.isMarked = isMarked;
        this.user_id = user_id;
        this.assignment_id = assignment_id;
        this.course_id = course_id;
    }

    public Submission(String submissionLink, String receivedScore, boolean isMarked, int user_id, int assignment_id, int course_id) {
        this.submissionLink = submissionLink;
        this.receivedScore = receivedScore;
        this.isMarked = isMarked;
        this.user_id = user_id;
        this.assignment_id = assignment_id;
        this.course_id = course_id;
    }



    public Submission(SubmissionDAO doa) {
        this.submissionDAO = doa;
    }

    public Submission() {

    }

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public String getSubmissionLink() {
        return submissionLink;
    }

    public void setSubmissionLink(String submissionLink) {
        this.submissionLink = submissionLink;
    }

    public String getReceivedScore() {
        return receivedScore;
    }

    public void setReceivedScore(String receivedScore) {
        this.receivedScore = receivedScore;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Integer getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(int assignment_id) {
        this.assignment_id = assignment_id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public List<Submission> getAllSubmissionsById(int user_id, int assignment_id, int course_id) throws SQLException {
        return submissionDAO.getAllSubmissionsById(user_id, assignment_id, course_id);
    }

    public Submission getSubmissionById(int submissionId) throws SQLException {
        return submissionDAO.getSubmissionById(submissionId);
    }

    @Override
    public List<SubmissionDTO> getAllSubmissionsByCourse(int courseId, int assignmentId) throws SQLException {
        return submissionDAO.getAllSubmissionsByCourse(courseId, assignmentId);
    }

    @Override
    public List<User> getAllUsersFromSubmissions(int courseId, int assignmentId) throws SQLException {
        return submissionDAO.getAllUsersFromSubmissions(courseId, assignmentId);
    }

    public String createSubmission(Submission submission, int user_id, int assignment_id, int course_id) throws SQLException {
        return submissionDAO.createSubmission(submission, user_id, assignment_id, course_id);
    }

    public Submission updateSubmissionById(Submission submission) throws SQLException {
        return submissionDAO.updateSubmissionById(submission);
    }

    @Override
    public List<Submission> getAllUserSubmissionByCourse(int assignmentId, Integer userId) {
        return submissionDAO.getAllUserSubmissionByCourse(assignmentId, userId);
    }
}

