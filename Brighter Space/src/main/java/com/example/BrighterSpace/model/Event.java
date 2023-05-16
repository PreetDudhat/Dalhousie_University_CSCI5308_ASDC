package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.IEvent;
import com.example.BrighterSpace.dataLayer.mocks.IEventDAO;

import java.sql.SQLException;
import java.util.List;

public class Event implements IEvent {
    private int eventId;
    private String eventDescription;
    private String eventDate;
    private IEventDAO eventDAO;
    private int userId;

    public Event(int eventId, String eventDescription, String eventDate) {
        this.eventId = eventId;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
    }

    public Event(int eventId, String eventDescription, String eventDate, int userId) {
        this.eventId = eventId;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.userId = userId;
    }

    public Event(IEventDAO doa){
        this.eventDAO = doa;
    }
    public Event(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public IEventDAO getEventDAO() {
        return eventDAO;
    }

    public void setEventDAO(IEventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }


    @Override
    public List<Event> getAllEvents() throws SQLException {
        return eventDAO.getAllEvents();
    }

    @Override
    public String createEvent(Event event, String email) throws SQLException {
        return eventDAO.createEvent(event, email);
    }

    @Override
    public Event getEventById(int eventId) throws SQLException {
        return eventDAO.getEventById(eventId);
    }

    @Override
    public Event updateEventById(Event event) throws SQLException {
        return eventDAO.updateEventById(event);
    }

    @Override
    public String deleteEventById(int eventId) throws SQLException {
        return eventDAO.deleteEventById(eventId);
    }
}
