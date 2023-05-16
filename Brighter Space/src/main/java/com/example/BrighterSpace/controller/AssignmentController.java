package com.example.BrighterSpace.controller;

import com.example.BrighterSpace.dataLayer.IAssignment;
import com.example.BrighterSpace.dataLayer.dao.CourseDAO;
import com.example.BrighterSpace.dataLayer.dao.UserDAO;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import com.example.BrighterSpace.model.Assignment;
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
public class AssignmentController {
    EntityFactoryProducer factory = new EntityFactoryProducer();
    IAssignment assignmentOperations = new Assignment(factory.getAssignmentFactory().create());

    CourseDAO courseDAO = new CourseDAO();
    UserDAO userDAO = new UserDAO();

    @GetMapping("{courseId}/assignments/signup")
    public String showSignUpForm(@PathVariable("courseId") int courseId, Assignment assignment, Model model) {
        try {
            model.addAttribute("course", courseDAO.getCourseById(courseId));
            model.addAttribute("courseCode", courseDAO.getCourseById(courseId).getCourseCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "add-assignment";
    }

    @GetMapping("{courseId}/assignments/list")
    public String showUpdateForm(@PathVariable("courseId") int courseId, Model model,
                                 HttpServletRequest request, Assignment assignment) {
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            model.addAttribute("user", user);
            model.addAttribute("assignments",
                    assignmentOperations.getAllAssignmentsById(courseId));
            model.addAttribute("course", courseDAO.getCourseById(courseId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index-assignment";
    }
    @PostMapping("{courseId}/assignments/add")
    public String addAssignment(@PathVariable("courseId") int courseId, Assignment assignment,
                                BindingResult result, Model model, HttpServletRequest request) {
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            if (result.hasErrors()) {
                return "add-assignment";
            }
            model.addAttribute("course", courseDAO.getCourseById(courseId));
            assignmentOperations.createAssignment(assignment, courseId, user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:list";
    }

    @GetMapping("{courseId}/assignments/update/{id}")
    public String showUpdateForm(@PathVariable("courseId") int courseId,
                                 @PathVariable("id") int id,
                                 Model model,
                                 HttpServletRequest request){
        try {
            Assignment assignment = assignmentOperations.getAssignmentById(id);
            model.addAttribute("assignment", assignment);
            model.addAttribute("course", courseDAO.getCourseById(courseId));
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "update-assignment";
    }

    @PostMapping("{courseId}/assignments/update/{id}")
    public String updateAssignment(@PathVariable("courseId") int courseId,
                                   @PathVariable("id") int id,
                                   Assignment assignment, BindingResult result,
                                   Model model, HttpServletRequest request){
        String toBeReturned = null;
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            if (result.hasErrors()) {
                assignment.setAssignmentId(id);
                return "update-assignment";
            }
            assignment.setAssignmentId(id);
            assignment.setCourse_id(courseId);
            assignment.setUser_id(user.getId());
            assignmentOperations.updateAssignmentById(assignment);
            toBeReturned = "redirect:/api/courses/getCourseDetails/" + courseId + "/assignments/list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toBeReturned;
    }

    @GetMapping("{courseId}/assignments/delete/{id}")
    public String deleteAnnouncement(@PathVariable("courseId") int courseId,
                                     @PathVariable("id") int id, Model model, HttpServletRequest request) {
        String toBeReturned = null;
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            Assignment assignment = assignmentOperations.getAssignmentById(id);
            assignmentOperations.deleteAssignmentById(id);
            toBeReturned = "redirect:/api/courses/getCourseDetails/" + courseId + "/announcements/list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toBeReturned;
    }

}
