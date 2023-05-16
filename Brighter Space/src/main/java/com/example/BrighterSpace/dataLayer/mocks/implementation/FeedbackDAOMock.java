package com.example.BrighterSpace.dataLayer.mocks.implementation;

import com.example.BrighterSpace.dataLayer.mocks.IFeedbackDAO;
import com.example.BrighterSpace.model.Feedback;

import java.util.ArrayList;
import java.util.List;

public class FeedbackDAOMock implements IFeedbackDAO {
    @Override
    public List<Feedback> getAllFeedbacks() {
        Feedback f1 = new Feedback(1, "No Class tomorrow");
        List<Feedback> list = new ArrayList<>();
        list.add(f1);
        return list;
    }

    @Override
    public String createFeedback(Feedback feedback) {
        return "Feedback created";
    }

    @Override
    public Feedback getFeedbackById(int feedbackId) {
        Feedback f1 = new Feedback(1, "No Class tomorrow");
        return f1;
    }

    @Override
    public Feedback updateFeedbackById(Feedback feedback) {
        Feedback f1 = new Feedback(1, "No Class tomorrow");
        return f1;
    }

    @Override
    public String deleteFeedbackById(int feedbackId) {
        return "Feedback deleted";
    }
}
