package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.IAssignment;
import com.example.BrighterSpace.dataLayer.mocks.IAssignmentDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Assignment implements IAssignment {

    private int assignmentId;
    private String assignmentName;

    private String assignmentDescription;
    private Date assignmentDeadline;
    private String assignmentTotalScore;
    private IAssignmentDAO assignmentDAO;
    private  int course_id;
    private  int user_id;

    public Assignment(int assignmentId, String assignmentName,String assignmentDescription, Date assignmentDeadline,
                      String assignmentTotalScore, int course_id, int user_id) {
        this.assignmentId = assignmentId;
        this.assignmentName = assignmentName;
        this.assignmentDescription = assignmentDescription;
        this.assignmentDeadline = assignmentDeadline;
        this.assignmentTotalScore = assignmentTotalScore;
        this.course_id = course_id;
        this.user_id = user_id;
    }

    public Assignment(String assignmentName, String assignmentDescription,
                      Date assignmentDeadline, String assignmentTotalScore, int course_id, int user_id) {
        this.assignmentName = assignmentName;
        this.assignmentDescription = assignmentDescription;
        this.assignmentDeadline = assignmentDeadline;
        this.assignmentTotalScore = assignmentTotalScore;
        this.course_id = course_id;
        this.user_id = user_id;
    }

    public Assignment(IAssignmentDAO doa) {
        this.assignmentDAO = doa;
    }

    public Assignment() {

    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    public Date getAssignmentDeadline() {
        return assignmentDeadline;
    }

    public void setAssignmentDeadline(Date assignmentDeadline) {
        this.assignmentDeadline = assignmentDeadline;
    }

    public String getAssignmentTotalScore() {
        return assignmentTotalScore;
    }

    public void setAssignmentTotalScore(String assignmentTotalScore) {
        this.assignmentTotalScore = assignmentTotalScore;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public List<Assignment> getAllAssignmentsById(int course_id) throws SQLException {
        return assignmentDAO.getAllAssignmentsById(course_id);
    }

    public String createAssignment(Assignment assignment, int course_id, int user_id) throws SQLException {
        return assignmentDAO.createAssignment(assignment, course_id, user_id);
    }

    public Assignment getAssignmentById(int assignmentId) throws SQLException {
        return assignmentDAO.getAssignmentById(assignmentId);
    }

    public Assignment updateAssignmentById(Assignment assignment) throws SQLException {
        return assignmentDAO.updateAssignmentById(assignment);
    }

    public String deleteAssignmentById(int id) throws SQLException {
        return assignmentDAO.deleteAssignmentById(id);
    }

}
