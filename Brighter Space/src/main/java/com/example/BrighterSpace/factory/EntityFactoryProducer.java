package com.example.BrighterSpace.factory;

import com.example.BrighterSpace.factory.implementation.*;

public class EntityFactoryProducer {
    public EventDAOFactory getEventFactory(){
        return new EventDAOFactory();
    }

    public EventDAOMockFactory getEventMockFactory(){
        return new EventDAOMockFactory();
    }

    public FeedbackDAOFactory getFeedbackFactory(){
        return new FeedbackDAOFactory();
    }

    public FeedbackDAOMockFactory getFeedbackMockFactory(){
        return new FeedbackDAOMockFactory();
    }

    public CourseDAOFactory getCourseFactory(){
        return new CourseDAOFactory();
    }

    public CourseDAOMockFactory getCourseMockFactory(){
        return new CourseDAOMockFactory();
    }
    public AnnouncementDAOFactory getAnnouncementFactory(){
        return new AnnouncementDAOFactory();
    }

    public AnnouncementDAOMockFactory getAnnouncementMockFactory() {
        return new AnnouncementDAOMockFactory();
    }

    public NotificationDAOFactory getNotificationFactory() {
        return new NotificationDAOFactory();
    }

    public NotificationDAOMockFactory getNotificationMockFactory() {
        return new NotificationDAOMockFactory();
    }

    public FaqDAOFactory getFaqFactory() {
        return new FaqDAOFactory();
    }

    public FaqDAOMockFactory getFaqMockFactory() {
        return new FaqDAOMockFactory();
    }

    public UserDAOFactory getUserFactory() {
        return new UserDAOFactory();
    }

    public UserDAOMockFactory getUserMockFactory() {
        return new UserDAOMockFactory();
    }

    public  AssignmentDAOFactory getAssignmentFactory(){return new AssignmentDAOFactory();}
    public AssignmentDAOMockFactory getAssignmentMockFactory() {
        return new AssignmentDAOMockFactory();
    }

    public SubmissionDAOFactory getSubmissionFactory(){return new SubmissionDAOFactory();}

}
