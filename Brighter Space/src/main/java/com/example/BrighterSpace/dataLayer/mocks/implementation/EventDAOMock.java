package com.example.BrighterSpace.dataLayer.mocks.implementation;

import com.example.BrighterSpace.dataLayer.mocks.IEventDAO;
import com.example.BrighterSpace.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventDAOMock implements IEventDAO {
    @Override
    public List<Event> getAllEvents() {
        Event e1 = new Event(1, "No Class tomorrow", "2022-01-01", 1);
        List<Event> list = new ArrayList<>();
        list.add(e1);
        return list;
    }

    @Override
    public String createEvent(Event event, String email) {
        return "Event created";
    }

    @Override
    public Event getEventById(int eventId) {
        Event e1 = new Event(1, "No Class tomorrow", "2022-01-01", 1);
        return e1;
    }

    @Override
    public Event updateEventById(Event event) {
        Event e1 = new Event(1, "No Class tomorrow", "2022-01-01", 1);
        return e1;
    }

    @Override
    public String deleteEventById(int eventId) {
        return "Event deleted";
    }
}
