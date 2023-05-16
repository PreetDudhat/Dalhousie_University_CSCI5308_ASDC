package com.example.BrighterSpace.dataLayer;

import com.example.BrighterSpace.model.Event;

import java.sql.SQLException;
import java.util.List;

public interface IEvent {
    List<Event> getAllEvents() throws SQLException;
    String createEvent(Event event, String email) throws SQLException;
    Event getEventById(int eventId) throws SQLException;
    Event updateEventById(Event event) throws SQLException;
    String deleteEventById(int eventId) throws SQLException;
}
