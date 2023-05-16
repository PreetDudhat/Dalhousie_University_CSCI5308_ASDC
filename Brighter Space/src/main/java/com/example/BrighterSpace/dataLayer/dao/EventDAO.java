package com.example.BrighterSpace.dataLayer.dao;

import com.example.BrighterSpace.dataLayer.mocks.IEventDAO;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import com.example.BrighterSpace.model.Event;
import com.example.BrighterSpace.model.User;
import com.example.BrighterSpace.utils.MailService;
import org.springframework.context.annotation.Lazy;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAO implements IEventDAO {
    @Lazy
    private final Connection conn = com.example.BrighterSpace.config.Connection.getInstance();

    private final MailService mailService = new MailService();

    EntityFactoryProducer factoryProducer = new EntityFactoryProducer();
    private final UserDAO userDAO = factoryProducer.getUserFactory().create();
    public String createEvent(Event event, String email) throws SQLException {
        User user = userDAO.getUserByEmail(email);
        try {
            if (user.getRole().equalsIgnoreCase("instructor")) {
                String query = "{CALL createEvent(?, ?, ?)}";
                CallableStatement statement = conn.prepareCall(query);
                statement.setString(1, event.getEventDescription());
                statement.setString(2, event.getEventDate());
                statement.setInt(3, user.getId());
                statement.executeQuery();

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            sendEvenCreationMailToEveryUser(event);
                            conn.close();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                thread.setDaemon(true);
                thread.start();

                return "Event added";
            }
            else{
                throw new Exception("Not authorized");
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public Event getEventById(int eventId) throws SQLException {
        String query = "{CALL GetEventById(?)}";
        Event event = null;
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                event = new Event(resultSet.getInt("eventId"), resultSet.getString("eventDescription"), resultSet.getString("eventDate"));
            }
            return event;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public Event updateEventById(Event event) throws SQLException {
        String query = "{CALL UpdateEvent(?, ?, ?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, event.getEventId());
            statement.setString(2, event.getEventDescription());
            statement.setString(3, event.getEventDate());
            statement.executeUpdate();

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sendEvenCreationMailToEveryUser(event);
                        conn.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();

            return event;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public String deleteEventById(int eventId) throws SQLException {
        String query = "{CALL DeleteEventById(?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, eventId);
            statement.execute();
            return "Record deleted";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        String query = "{CALL GetAllEvents()}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event(resultSet.getInt("eventId"), resultSet.getString("eventDescription"), resultSet.getString("eventDate"));
                events.add(event);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
        return events;
    }

    private void sendEvenCreationMailToEveryUser(Event event) throws SQLException {
        List<User> users = userDAO.getAllUsers();
        for(User user : users) {
            mailService.sendEventMail(event, user);
        }
    }
}
