package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.IEvent;
import com.example.BrighterSpace.dataLayer.mocks.implementation.EventDAOMock;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;


@SpringBootTest(classes = {com.example.BrighterSpace.model.Event.class, EventDAOMock.class})
public class EventTest {

    @Test
    public void eventTestFromAllArgsConstructor() {
        Event a1 = new Event(1, "No Class tomorrow", "2022-01-01", 1);
        Assertions.assertEquals(a1.getEventId(), 1);
        Assertions.assertEquals(a1.getEventDescription(), "No Class tomorrow");
        Assertions.assertEquals(a1.getEventDate(), "2022-01-01");
        Assertions.assertEquals(a1.getUserId(), 1);
    }

    @Test
    public void createEventTest() throws SQLException {
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IEvent eventOperations = new Event(factory.getEventMockFactory().create());
        Event event = new Event(1, "No Class tomorrow", "2022-01-01");
        String output = eventOperations.createEvent(event, "gkiran@dal.ca");
        Assertions.assertEquals(output, "Event created");
    }

    @Test
    public void getEventByIdTest()throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IEvent eventOperations = new Event(factory.getEventMockFactory().create());
        Event e1 = eventOperations.getEventById(1);
        Assertions.assertEquals(e1.getEventId(), 1);
        Assertions.assertEquals(e1.getEventDescription(), "No Class tomorrow");
        Assertions.assertEquals(e1.getEventDate(), "2022-01-01");
    }

    @Test
    public void updateEventByIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IEvent eventOperations = new Event(factory.getEventMockFactory().create());
        Event e1 = eventOperations.getEventById(1);
        Assertions.assertEquals(e1.getEventId(), 1);
        Assertions.assertEquals(e1.getEventDescription(), "No Class tomorrow");
        Assertions.assertEquals(e1.getEventDate(), "2022-01-01");
    }

    @Test
    public void deleteEventByIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IEvent eventOperations = new Event(factory.getEventMockFactory().create());
        String a1 = eventOperations.deleteEventById(1);
        Assertions.assertEquals(a1, "Event deleted");
    }

    @Test
    public void getAllEventsTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        IEvent eventOperations = new Event(factory.getEventMockFactory().create());
        List<Event> eventList = eventOperations.getAllEvents();
        Assertions.assertEquals(eventList.get(0).getEventId(), 1);
        Assertions.assertEquals(eventList.get(0).getEventDescription(), "No Class tomorrow");
        Assertions.assertEquals(eventList.get(0).getEventDate(), "2022-01-01");
    }
}
