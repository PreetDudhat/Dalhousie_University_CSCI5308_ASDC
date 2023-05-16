package com.example.BrighterSpace.model.dto;

public class SubmissionDTO {

    private int submissionId;
    private String submissionLink;
    private float receivedScore;
    private boolean isMarked;
    private int assignmentId;
    private String userName;

    public SubmissionDTO() {
    }

    public SubmissionDTO(int submissionId, String submissionLink, float receivedScore, boolean isMarked, int assignmentId, String userName) {
        this.submissionId = submissionId;
        this.submissionLink = submissionLink;
        this.receivedScore = receivedScore;
        this.isMarked = isMarked;
        this.assignmentId = assignmentId;
        this.userName = userName;
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

    public float getReceivedScore() {
        return receivedScore;
    }

    public void setReceivedScore(float receivedScore) {
        this.receivedScore = receivedScore;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
