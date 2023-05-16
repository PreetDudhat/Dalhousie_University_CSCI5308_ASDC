package com.example.BrighterSpace.controller;

import com.example.BrighterSpace.dataLayer.ISubmission;
import com.example.BrighterSpace.dataLayer.dao.AssignmentDAO;
import com.example.BrighterSpace.dataLayer.dao.CourseDAO;
import com.example.BrighterSpace.dataLayer.dao.NotificationDAO;
import com.example.BrighterSpace.dataLayer.dao.UserDAO;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import com.example.BrighterSpace.model.Assignment;
import com.example.BrighterSpace.model.Submission;
import com.example.BrighterSpace.model.User;
import com.example.BrighterSpace.model.dto.SubmissionDTO;
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
public class SubmissionController {
    EntityFactoryProducer factory = new EntityFactoryProducer();
    ISubmission submissionOperations = new Submission(factory.getSubmissionFactory().create());

    CourseDAO courseDAO = new CourseDAO();
    AssignmentDAO assignmentDAO = factory.getAssignmentFactory().create();
    UserDAO userDAO = factory.getUserFactory().create();
    NotificationDAO notificationDAO = factory.getNotificationFactory().create();

    @GetMapping("{courseId}/assignments/{assignmentId}/submissions/signup")
    public String showSignUpForm(@PathVariable("courseId") int courseId, @PathVariable("assignmentId") int assignmentId,
                                 Submission submission, Model model) {
        try {
            model.addAttribute("course", courseDAO.getCourseById(courseId));
            model.addAttribute("assignment", assignmentDAO.getAssignmentById(assignmentId));
            model.addAttribute("courseCode", courseDAO.getCourseById(courseId).getCourseCode());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "add-submission";
    }

    @GetMapping("{courseId}/assignments/{assignmentId}/submissions/list")
    public String showUpdateForm(@PathVariable("courseId") int courseId,
                                 @PathVariable("assignmentId") int assignmentId, Model model,
                                 HttpServletRequest request){
        try{
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            if(user.getRole().equalsIgnoreCase("Student")){
                List<Submission> submissionList = null;
                submissionList = submissionOperations.getAllUserSubmissionByCourse(assignmentId, user.getId());
                model.addAttribute("allSubmissions", submissionList);
            } else if(user.getRole().equalsIgnoreCase("Instructor")) {
                List<SubmissionDTO> submissionList = null;
                submissionList = submissionOperations.getAllSubmissionsByCourse(courseId, assignmentId);
                model.addAttribute("allSubmissions", submissionList);
            }

            model.addAttribute("assignment", assignmentDAO.getAssignmentById(assignmentId));
            model.addAttribute("user", userDAO.getUserByEmail(userEmail));
            model.addAttribute("course", courseDAO.getCourseById(courseId));
        }catch (Exception e){
            e.printStackTrace();
        }

        return "index-submission";
    }

    @PostMapping("{courseId}/assignments/{assignmentId}/submissions/add")
    public String addSubmission(@PathVariable("courseId") int courseId, Submission submission,
                                BindingResult result, Model model, HttpServletRequest request, Assignment assignment){
        try{
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            if (result.hasErrors()) {
                return "add-submission";
            }
            model.addAttribute("course", courseDAO.getCourseById(courseId));
            submissionOperations.createSubmission(submission, user.getId(), assignment.getAssignmentId(), courseId);
            notificationDAO.createNotification("Submission done for " + courseDAO.getCourseById(courseId).getCourseCode(), courseId, user.getId());

        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:list";
    }

    @GetMapping("{courseId}/assignments/{assignmentId}/submissions/update/{id}")
    public String showUpdateForm(@PathVariable("courseId") int courseId,
                                 @PathVariable("assignmentId") int assignmentId,
                                 @PathVariable("id") int id,
                                 Model model,
                                 HttpServletRequest request){
        try {
            Submission submission = submissionOperations.getSubmissionById(id);
            model.addAttribute("assignment", assignmentDAO.getAssignmentById(assignmentId));
            model.addAttribute("submission", submission);
            model.addAttribute("course", courseDAO.getCourseById(courseId));
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "update-submission";
    }

    @PostMapping("{courseId}/assignments/{assignmentId}/submissions/update/{id}")
    public String updateAssignment(@PathVariable("courseId") int courseId,
                                   @PathVariable("assignmentId") int assignmentId,
                                   @PathVariable("id") int id, Submission submission,
                                   BindingResult result,
                                   Model model, HttpServletRequest request){
        try{
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            if (result.hasErrors()) {
                submission.setSubmissionId(id);
                return "update-submission";
            }
            model.addAttribute("assignment", assignmentDAO.getAssignmentById(assignmentId));
            submission.setSubmissionId(id);
            submission.setCourse_id(courseId);
            submission.setAssignment_id((assignmentId));
            submissionOperations.updateSubmissionById(submission);
            notificationDAO.createNotification("Submission updates for + " + courseDAO.getCourseById(courseId).getCourseCode(), courseId, user.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        String toBeReturned = "redirect:/api/courses/getCourseDetails/" + courseId + "/assignments/" + assignmentId + "/submissions/list";
        return toBeReturned;
    }
}
