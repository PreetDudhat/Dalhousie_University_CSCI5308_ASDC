package com.example.BrighterSpace.dataLayer.mocks;

import com.example.BrighterSpace.model.Feedback;

import java.sql.SQLException;
import java.util.List;

public interface IFeedbackDAO {
    List<Feedback> getAllFeedbacks() throws SQLException;
    String createFeedback(Feedback feedback) throws SQLException;
    Feedback getFeedbackById(int feedbackId) throws SQLException;
    Feedback updateFeedbackById(Feedback feedback) throws SQLException;
    String deleteFeedbackById(int feedbackId) throws SQLException;

}
