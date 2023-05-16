package com.example.BrighterSpace.controller;

import com.example.BrighterSpace.dataLayer.INotification;
import com.example.BrighterSpace.dataLayer.dao.UserDAO;
import com.example.BrighterSpace.dataLayer.mocks.INotificationDAO;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import com.example.BrighterSpace.model.Notification;
import com.example.BrighterSpace.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("/api/courses/notifications/")
@org.springframework.stereotype.Controller
public class NotificationController {

    EntityFactoryProducer factory = new EntityFactoryProducer();
    INotification notificationOperations = new Notification(factory.getNotificationFactory().create());
    UserDAO userDAO = factory.getUserFactory().create();

    @GetMapping("clear")
    public String deleteAllNotifications(Notification notification, Model model, HttpServletRequest request){
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            notificationOperations.deleteAllNotificationsByUserId(user.getId());
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:list";
    }

    @GetMapping("list")
    public String showUpdateForm(String email, Model model, HttpServletRequest request) {
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            model.addAttribute("user", user);
            model.addAttribute("notifications",
                    notificationOperations.getAllNotificationsByUserId(user.getId()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "index-notification";
    }
}
