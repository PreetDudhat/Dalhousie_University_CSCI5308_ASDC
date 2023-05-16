package com.example.BrighterSpace.controller;

import com.example.BrighterSpace.dataLayer.IAnnouncement;
import com.example.BrighterSpace.dataLayer.dao.CourseDAO;
import com.example.BrighterSpace.dataLayer.dao.NotificationDAO;
import com.example.BrighterSpace.dataLayer.dao.UserDAO;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import com.example.BrighterSpace.model.Announcement;
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

@RequestMapping("/api/courses/getCourseDetails/")
@org.springframework.stereotype.Controller
public class AnnouncementController {

    EntityFactoryProducer factory = new EntityFactoryProducer();
    IAnnouncement announcementOperations = new Announcement(factory.getAnnouncementFactory().create());
    CourseDAO courseDAO = factory.getCourseFactory().create();
    NotificationDAO notificationDAO = factory.getNotificationFactory().create();
    UserDAO userDAO = factory.getUserFactory().create();

    @GetMapping("{courseId}/announcements/signup")
    public String showSignUpForm(@PathVariable("courseId") int courseId, Announcement announcement, Model model)  {
        try {
            model.addAttribute("course", courseDAO.getCourseById(courseId));
            model.addAttribute("courseCode", courseDAO.getCourseById(courseId).getCourseCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "add-announcement";
    }

    @GetMapping("{courseId}/announcements/list")
    public String showUpdateForm(@PathVariable("courseId") int courseId, Model model, HttpServletRequest request)  {
        try {
            String email = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(email);
            model.addAttribute("user", user);
            model.addAttribute("announcements",
                    announcementOperations.getAllAnnouncementsByCourseId(courseId));
            model.addAttribute("course", courseDAO.getCourseById(courseId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index-announcement";
    }

    @PostMapping("{courseId}/announcements/add")
    public String addAnnouncement(@PathVariable("courseId") int courseId, Announcement announcement,
                                  BindingResult result, Model model, HttpServletRequest request) {
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            if (result.hasErrors()) {
                return "add-announcement";
            }
            model.addAttribute("course", courseDAO.getCourseById(courseId));
            announcementOperations.createAnnouncement(announcement, courseId);
            notificationDAO.createNotification("Announcement: " + announcement.getTitle() + " . Added!", courseId, user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:list";
    }

    @GetMapping("{courseId}/announcements/update/{id}")
    public String showUpdateForm(@PathVariable("courseId") int courseId,
                                 @PathVariable("id") int id,
                                 Model model) {
        try {
            Announcement announcement = announcementOperations.getAnnouncementById(id);
            model.addAttribute("announcement", announcement);
            model.addAttribute("course", courseDAO.getCourseById(courseId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "update-announcement";
    }

    @PostMapping("{courseId}/announcements/update/{id}")
    public String updateAnnouncement(@PathVariable("courseId") int courseId,
                                     @PathVariable("id") int id,
                                     Announcement announcement, BindingResult result,
                                     Model model, HttpServletRequest request) {
        String toBeReturned = null;
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            if (result.hasErrors()) {
                announcement.setId(id);
                return "update-announcement";
            }
            announcement.setId(id);
            announcement.setCourseId(courseId);
            announcementOperations.updateAnnouncementById(announcement);
            notificationDAO.createNotification("Announcement: " + announcement.getTitle() + " . Updated!", courseId, user.getId());
            toBeReturned = "redirect:/api/courses/getCourseDetails/" + courseId + "/announcements/list";
        } catch (Exception e ){
            e.printStackTrace();
        }
        return toBeReturned;
    }

    @GetMapping("{courseId}/announcements/delete/{id}")
    public String deleteAnnouncement(@PathVariable("courseId") int courseId,
                                     @PathVariable("id") int id, Model model, HttpServletRequest request)  {
        String toBeReturned = null;
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            Announcement announcement = announcementOperations.getAnnouncementById(id);
            announcementOperations.deleteAnnouncementById(id);
            notificationDAO.createNotification("Announcement: " + announcement.getTitle() + " . Deleted!", courseId, user.getId());
            toBeReturned = "redirect:/api/courses/getCourseDetails/" + courseId + "/announcements/list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toBeReturned;
    }
}
