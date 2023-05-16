package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.INotification;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class NotificationTest {

    @Test
    public void getAllNotificationsTest(){

        Notification n1 = new Notification(1,"notify_1",1,Timestamp.valueOf("2022-02-02 02:02:02"),1);
        Assertions.assertEquals(n1.getId(), 1);
        Assertions.assertEquals(n1.getText(), "notify_1");
        Assertions.assertEquals(n1.getCourse_id(), 1);
        Assertions.assertEquals(n1.getTime(), Timestamp.valueOf("2022-02-02 02:02:02"));
        Assertions.assertEquals(n1.getUser_id(), 1);
    }
    @Test
    public void getAllNotificationsTestByCourseId()throws SQLException {
        EntityFactoryProducer factory = new EntityFactoryProducer();
        INotification notificationOperations =new Notification(factory.getNotificationMockFactory().create());
        Notification n1 = new Notification(1,"notify_1",1,Timestamp.valueOf("2022-02-02 02:02:02"),1);
        List<Notification> output = notificationOperations.getAllNotificationsByUserId(n1.getCourse_id());
        Assertions.assertEquals(output.get(0).getUser_id(), 1);
        Assertions.assertEquals(output.get(0).getText(), "notify_1");
        Assertions.assertEquals(output.get(0).getCourse_id(), 1);
        Assertions.assertEquals(output.get(0).getTime(), Timestamp.valueOf("2022-02-02 02:02:02"));
        Assertions.assertEquals(output.get(0).getUser_id(), 1);
    }
    @Test
    public void createNotificationTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        INotification notificationOperations =new Notification(factory.getNotificationMockFactory().create());
        Notification n1 = new Notification(1,"notify_1",1,Timestamp.valueOf("2022-02-02 02:02:02"),1);
        String output = notificationOperations.createNotification(n1.getText(),1,1);
        Assertions.assertEquals(output, "Notification Created");
    }
    @Test
    public void deleteAllNotificationsByCourseId() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        INotification notificationOperations = new Notification(factory.getNotificationMockFactory().create());
        String output = notificationOperations.deleteAllNotificationsByCourseId(1);
        Assertions.assertEquals(output, "Notification Deleted");
    }
    @Test
    public void deleteAllNotificationsByUserId()throws SQLException {
        EntityFactoryProducer factory = new EntityFactoryProducer();
        INotification notificationOperations = new Notification(factory.getNotificationMockFactory().create());
        String output = notificationOperations.deleteAllNotificationsByCourseId(1);
        Assertions.assertEquals(output, "Notification Deleted");
    }
    @Test
    public void getAllNotificationsTestByUserId() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        INotification notificationOperations =new Notification(factory.getNotificationMockFactory().create());
        Notification n1 = new Notification(1,"notify_1",1,Timestamp.valueOf("2022-02-02 02:02:02"),1);
        List<Notification> output = notificationOperations.getAllNotificationsByUserId(n1.getUser_id());
        Assertions.assertEquals(output.get(0).getUser_id(), 1);
        Assertions.assertEquals(output.get(0).getText(), "notify_1");
        Assertions.assertEquals(output.get(0).getCourse_id(), 1);
        Assertions.assertEquals(output.get(0).getTime(), Timestamp.valueOf("2022-02-02 02:02:02"));
        Assertions.assertEquals(output.get(0).getUser_id(), 1);
    }
}
