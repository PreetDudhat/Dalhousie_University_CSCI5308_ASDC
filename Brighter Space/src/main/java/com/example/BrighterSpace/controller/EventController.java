package com.example.BrighterSpace.controller;

import com.example.BrighterSpace.dataLayer.IEvent;
import com.example.BrighterSpace.dataLayer.INotification;
import com.example.BrighterSpace.dataLayer.dao.UserDAO;
import com.example.BrighterSpace.dataLayer.mocks.INotificationDAO;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import com.example.BrighterSpace.model.Event;
import com.example.BrighterSpace.model.Notification;
import com.example.BrighterSpace.model.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("/api/events/")
@org.springframework.stereotype.Controller
public class EventController {
    EntityFactoryProducer factory = new EntityFactoryProducer();
    IEvent eventOperations = new Event(factory.getEventFactory().create());

    INotification notificationDAO = new Notification(factory.getNotificationFactory().create());
    UserDAO userDAO = factory.getUserFactory().create();

    @GetMapping("signup")
    public String showSignUpForm(Event event) {
        return "add-event";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model, HttpServletRequest request)  {
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            model.addAttribute("events", eventOperations.getAllEvents());
            model.addAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index-event";
    }

    @PostMapping("add")
    public String addEvent(Event event, BindingResult result, Model model, HttpServletRequest request) {
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            if (result.hasErrors()) {
                return "add-event";
            }
            eventOperations.createEvent(event, userEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            Event event = eventOperations.getEventById(id);
            model.addAttribute("events", event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "update-event";
    }

    @PostMapping("update/{id}")
    public String updateEvent(@PathVariable("id") int id, Event event, BindingResult result,
                              Model model, HttpServletRequest request) {
        try {
            if (result.hasErrors()) {
                event.setEventId(id);
                return "update-event";
            }
            event.setEventId(id);
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            model.addAttribute("user", user);
            eventOperations.updateEventById(event);
            model.addAttribute("events", eventOperations.getAllEvents());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index-event";
    }

    @GetMapping("delete/{id}")
    public String deleteEvent(@PathVariable("id") int id, Model model, HttpServletRequest request){
        try {
            Event event = eventOperations.getEventById(id);

            eventOperations.deleteEventById(id);
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            model.addAttribute("user", user);
            model.addAttribute("events", eventOperations.getAllEvents());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index-event";
    }

}
