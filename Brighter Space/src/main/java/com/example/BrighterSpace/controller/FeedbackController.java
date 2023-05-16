package com.example.BrighterSpace.controller;

import com.example.BrighterSpace.dataLayer.IFeedback;
import com.example.BrighterSpace.dataLayer.dao.UserDAO;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import com.example.BrighterSpace.model.Feedback;
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

@RequestMapping("/api/feedbacks/")
@org.springframework.stereotype.Controller
public class FeedbackController {

    EntityFactoryProducer factory = new EntityFactoryProducer();
    IFeedback feedbackOperations = new Feedback(factory.getFeedbackFactory().create());

    UserDAO userDAO = factory.getUserFactory().create();

    @GetMapping("signup")
    public String showSignUpForm(Feedback feedback) {
        return "add-feedback";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model, HttpServletRequest request)  {
        try {
            model.addAttribute("feedbacks", feedbackOperations.getAllFeedbacks());
            String email = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(email);
            model.addAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index-feedback";
    }

    @PostMapping("add")
    public String addFeedback(Feedback feedback, BindingResult result, Model model, HttpServletRequest request) {
        try {
            if (result.hasErrors()) {
                return "add-feedback";
            }
            feedbackOperations.createFeedback(feedback);

            String email = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(email);
            model.addAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model, HttpServletRequest request){
        try {
            Feedback feedback = feedbackOperations.getFeedbackById(id);

            String email = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(email);
            model.addAttribute("user", user);
            model.addAttribute("feedback", feedback);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "update-feedback";
    }

    @PostMapping("update/{id}")
    public String updateFeedback(@PathVariable("id") int id, Feedback feedback, BindingResult result,
                               Model model, HttpServletRequest request) {
        try {
            if (result.hasErrors()) {
                feedback.setFeedbackId(id);
                return "update-feedback";
            }
            feedback.setFeedbackId(id);
            feedbackOperations.updateFeedbackById(feedback);
            String email = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(email);
            model.addAttribute("user", user);
            model.addAttribute("feedbacks", feedbackOperations.getAllFeedbacks());
        } catch (Exception e){
            e.printStackTrace();
        }
        return "index-feedback";
    }

    @GetMapping("delete/{id}")
    public String deleteFeedback(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            Feedback feedback = feedbackOperations.getFeedbackById(id);
            feedbackOperations.deleteFeedbackById(id);

            String email = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(email);
            model.addAttribute("user", user);

            model.addAttribute("feedbacks", feedbackOperations.getAllFeedbacks());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index-feedback";
    }

}
