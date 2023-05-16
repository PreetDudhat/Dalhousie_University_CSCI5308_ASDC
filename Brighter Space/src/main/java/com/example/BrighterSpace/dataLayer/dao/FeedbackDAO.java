package com.example.BrighterSpace.dataLayer.dao;

import com.example.BrighterSpace.dataLayer.mocks.IFeedbackDAO;
import com.example.BrighterSpace.model.Feedback;
import org.springframework.context.annotation.Lazy;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO implements IFeedbackDAO {

    @Lazy
    private final Connection conn = com.example.BrighterSpace.config.Connection.getInstance();

    public String createFeedback(Feedback feedback) throws SQLException {
        String query = "{CALL createFeedback(?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setString(1, feedback.getComment());
            statement.executeQuery();
            return "Feedback added";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public Feedback getFeedbackById(int feedbackId) throws SQLException {
        String query = "{CALL GetFeedbackById(?)}";
        Feedback feedback = null;
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, feedbackId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                feedback = new Feedback(resultSet.getInt("feedbackId"), resultSet.getString("comment"));
            }
            return feedback;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public Feedback updateFeedbackById(Feedback feedback) throws SQLException {
        String query = "{CALL UpdateFeedback(?, ?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, feedback.getFeedbackId());
            statement.setString(2, feedback.getComment());
            statement.executeUpdate();
            return feedback;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public String deleteFeedbackById(int feedbackId) throws SQLException {
        String query = "{CALL DeleteFeedbackById(?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, feedbackId);
            statement.execute();
            return "Record deleted";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public List<Feedback> getAllFeedbacks() throws SQLException {
        List<Feedback> feedbacks = new ArrayList<>();
        String query = "{CALL GetAllFeedbacks()}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Feedback feedback = new Feedback(resultSet.getInt("feedbackId"), resultSet.getString("comment"));
                feedbacks.add(feedback);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
        return feedbacks;
    }
}
