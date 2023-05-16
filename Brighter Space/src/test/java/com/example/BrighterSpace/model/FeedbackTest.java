package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.IFeedback;
import com.example.BrighterSpace.dataLayer.mocks.implementation.FeedbackDAOMock;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;


@SpringBootTest(classes = {com.example.BrighterSpace.model.Feedback.class, FeedbackDAOMock.class})
public class FeedbackTest {

    @Test
    public void feedbackTestFromAllArgsConstructor() {
        Feedback a1 = new Feedback(1, "No Class tomorrow");
        Assertions.assertEquals(a1.getFeedbackId(), 1);
        Assertions.assertEquals(a1.getComment(), "No Class tomorrow");
    }

    @Test
    public void createAnnouncementTest()throws SQLException {
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IFeedback feedbackOperations = new Feedback(factory.getFeedbackMockFactory().create());
        Feedback feedback = new Feedback(1, "No Class tomorrow");
        String output = feedbackOperations.createFeedback(feedback);
        Assertions.assertEquals(output, "Feedback created");
    }

    @Test
    public void getEventByIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IFeedback feedbackOperations = new Feedback(factory.getFeedbackMockFactory().create());
        Feedback e1 = feedbackOperations.getFeedbackById(1);
        Assertions.assertEquals(e1.getFeedbackId(), 1);
        Assertions.assertEquals(e1.getComment(), "No Class tomorrow");
    }

    @Test
    public void updateEventByIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IFeedback feedbackOperations = new Feedback(factory.getFeedbackMockFactory().create());
        Feedback e1 = feedbackOperations.getFeedbackById(1);
        Assertions.assertEquals(e1.getFeedbackId(), 1);
        Assertions.assertEquals(e1.getComment(), "No Class tomorrow");
    }

    @Test
    public void deleteEventByIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IFeedback feedbackOperations = new Feedback(factory.getFeedbackMockFactory().create());
        String a1 = feedbackOperations.deleteFeedbackById(1);
        Assertions.assertEquals(a1, "Feedback deleted");
    }

    @Test
    public void getAllAnnouncementsByCourseIdTest()throws SQLException {
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IFeedback feedbackOperations = new Feedback(factory.getFeedbackMockFactory().create());
        List<Feedback> feedbackList = feedbackOperations.getAllFeedbacks();
        Assertions.assertEquals(feedbackList.get(0).getFeedbackId(), 1);
        Assertions.assertEquals(feedbackList.get(0).getComment(), "No Class tomorrow");
    }
}
