package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.IFeedback;
import com.example.BrighterSpace.dataLayer.mocks.IFeedbackDAO;

import java.sql.SQLException;
import java.util.List;

public class Feedback implements IFeedback {
    private int feedbackId;
    private String comment;
    private IFeedbackDAO feedbackDOA;

    public Feedback(int feedbackId, String comment) {
        this.feedbackId = feedbackId;
        this.comment = comment;
    }

    public Feedback(IFeedbackDAO doa){
        this.feedbackDOA = doa;
    }
    public Feedback(){

    }

    public IFeedbackDAO getFeedbackDOA() {
        return feedbackDOA;
    }

    public void setFeedbackDOA(IFeedbackDAO feedbackDOA) {
        this.feedbackDOA = feedbackDOA;
    }


    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public List<Feedback> getAllFeedbacks() throws SQLException {
        return feedbackDOA.getAllFeedbacks();
    }

    @Override
    public String createFeedback(Feedback feedback) throws SQLException {
        return feedbackDOA.createFeedback(feedback);
    }

    @Override
    public Feedback getFeedbackById(int feedbackId) throws SQLException {
        return feedbackDOA.getFeedbackById(feedbackId);
    }

    @Override
    public Feedback updateFeedbackById(Feedback feedback) throws SQLException {
        return feedbackDOA.updateFeedbackById(feedback);
    }

    @Override
    public String deleteFeedbackById(int feedbackId) throws SQLException {
        return feedbackDOA.deleteFeedbackById(feedbackId);
    }
}

