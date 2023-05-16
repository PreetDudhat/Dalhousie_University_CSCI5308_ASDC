package com.example.BrighterSpace.controller;

import com.example.BrighterSpace.dataLayer.IFaq;
import com.example.BrighterSpace.dataLayer.dao.CourseDAO;
import com.example.BrighterSpace.dataLayer.dao.NotificationDAO;
import com.example.BrighterSpace.dataLayer.dao.UserDAO;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import com.example.BrighterSpace.model.Faq;
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
public class FaqController {
    EntityFactoryProducer factory = new EntityFactoryProducer();
    IFaq faqOperations = new Faq(factory.getFaqFactory().create());
    CourseDAO courseDAO = factory.getCourseFactory().create();
    NotificationDAO notificationDAO = factory.getNotificationFactory().create();
    UserDAO userDAO = factory.getUserFactory().create();

    @GetMapping("{courseId}/faqs/signup")
    public String showSignUpForm(@PathVariable("courseId") int courseId, Faq faq, Model model) {
        try {
            model.addAttribute("course", courseDAO.getCourseById(courseId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "add-faq";
    }

    @GetMapping("{courseId}/faqs/list")
    public String showUpdateForm(@PathVariable("courseId") int courseId, Model model, HttpServletRequest request) {
        try {
            String email = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(email);
            model.addAttribute("user", user);
            model.addAttribute("faqs", faqOperations.getAllFaqsByCourseId(courseId));
            model.addAttribute("course", courseDAO.getCourseById(courseId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index-faq";
    }

    @PostMapping("{courseId}/faqs/add")
    public String addFaq(@PathVariable("courseId") int courseId, Faq faq, BindingResult result, Model model, HttpServletRequest request){
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            if (result.hasErrors()) {
                return "add-faq";
            }
            faqOperations.createFaq(faq, courseId);
            notificationDAO.createNotification("FAQ: " + faq.getFaqQuestion() + " . Added!", courseId, user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:list";
    }

    @GetMapping("{courseId}/faqs/update/{id}")
    public String showUpdateForm(@PathVariable("courseId") int courseId, @PathVariable("id") int id, Model model) {
        try {
            Faq faq = faqOperations.getFaqById(id);
            model.addAttribute("faq", faq);
            model.addAttribute("course", courseDAO.getCourseById(courseId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "update-faq";
    }

    @PostMapping("{courseId}/faqs/update/{id}")
    public String updateFaq(@PathVariable("id") int id, @PathVariable("courseId") int courseId,
                            Faq faq, BindingResult result, HttpServletRequest request, Model model) {
        String toBeReturned = null;
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            if (result.hasErrors()) {
                faq.setFaqId(id);
                return "update-faq";
            }
            faq.setFaqId(id);
            faqOperations.updateFaqById(faq);

            model.addAttribute("faqs", faqOperations.getAllFaqsByCourseId(courseId));
            notificationDAO.createNotification("FAQ: " + faq.getFaqQuestion() + " . Updated!", courseId, user.getId());
            toBeReturned = "redirect:/api/courses/getCourseDetails/" + courseId + "/faqs/list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toBeReturned;
    }

    @GetMapping("{courseId}/faqs/delete/{id}")
    public String deleteFaq(@PathVariable("courseId") int courseId, @PathVariable("id") int id,
                            HttpServletRequest request, Model model) {
        String toBeReturned = null;
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            Faq faq = faqOperations.getFaqById(id);
            faqOperations.deleteFaqById(id);
            model.addAttribute("faqs", faqOperations.getAllFaqsByCourseId(courseId));
            notificationDAO.createNotification("FAQ: " + faq.getFaqQuestion() + " . Deleted!", courseId, user.getId());
            toBeReturned = "redirect:/api/courses/getCourseDetails/" + courseId + "/faqs/list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toBeReturned;
    }
}
