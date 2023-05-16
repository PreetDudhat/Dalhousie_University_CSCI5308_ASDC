package com.example.BrighterSpace.controller;

import com.example.BrighterSpace.dataLayer.ICourse;
import com.example.BrighterSpace.dataLayer.dao.NotificationDAO;
import com.example.BrighterSpace.dataLayer.dao.UserDAO;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import com.example.BrighterSpace.model.Course;
import com.example.BrighterSpace.model.User;
import com.example.BrighterSpace.model.dto.CourseDTO;
import com.example.BrighterSpace.utils.MailService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("/api/")
@org.springframework.stereotype.Controller
public class CourseController {

    EntityFactoryProducer factory = new EntityFactoryProducer();
    ICourse courseOperations = new Course(factory.getCourseFactory().create());

    NotificationDAO notificationDAO = new NotificationDAO();
    UserDAO userDAO = factory.getUserFactory().create();

    MailService mailService = new MailService();

    @GetMapping("courses/add")
    public String getCourseAddForm(Course course, Model model) {
        model.addAttribute(course);
        return "add-course";
    }

    @PostMapping("courses/add")
    public String addCourse(Course course, BindingResult result, Model model, HttpServletRequest request) {
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            if (result.hasErrors()) {
                return "add-course";
            }
            courseOperations.createCourse(course, userEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:list";
    }

    @GetMapping("courses/list")
    public String getAllCourses(Model model, HttpServletRequest request){
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            model.addAttribute("courses", courseOperations.getAllCoursesByUserId(user.getId()));
            model.addAttribute("user", userDAO.getUserByEmail(userEmail));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index-course";
    }

    @GetMapping("courses/getCourseDetails/{id}")
    public String showCourseDashboard(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            model.addAttribute("user", userDAO.getUserByEmail(userEmail));
            Course course = courseOperations.getCourseById(id);
            model.addAttribute("course", course);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "course-dashboard";
    }

    @GetMapping("courses/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model){
        try {
            Course course = courseOperations.getCourseById(id);
            model.addAttribute("course", course);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "update-course";
    }

    @PostMapping("courses/update/{id}")
    public String updateCourse(
            @PathVariable("id") int id,
            Course course,
            BindingResult result,
            Model model,
            HttpServletRequest request
    ) {
        try {
            if (result.hasErrors()) {
                course.setCourseId(id);
                return "update-course";
            }
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            course.setUserId(user.getId());
            course.setCourseId(id);
            courseOperations.updateCourse(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/api/courses/list";
    }

    @GetMapping("courses/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(userEmail);
            Course course = courseOperations.getCourseById(id);
            courseOperations.deleteCourseById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/api/courses/list";
    }

    @GetMapping("student/courses/list")
    public String getAllCoursesForStudent(Model model, HttpServletRequest request) {
        try {
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            model.addAttribute("courses", courseOperations.getAllCourses());
            model.addAttribute("user", userDAO.getUserByEmail(userEmail));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index-course";
    }

    @GetMapping("student/courses/{courseId}/register")
    public String registerUserToCourse(@PathVariable int courseId, HttpServletRequest request, Model model) {
        try {
            String email = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(email);
            Course course = courseOperations.registerToCourse(courseId, user);
            if (course != null) {
                List<CourseDTO> courses = courseOperations.getAllRegisteredCoursesByUserId(user);
                model.addAttribute("courses", courses);
                model.addAttribute("user", user);
                mailService.sendCourseRegistrationMail(course, user);
                notificationDAO.createNotification("Registered to " + course.getCourseCode(), courseId, user.getId());
                return "my-courses";
            } else {
                return "error";
            }
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("student/courses/registeredCourses")
    public String showRegisteredCourses(HttpServletRequest request, Model model) {
        try {
            String email = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            User user = userDAO.getUserByEmail(email);
            model.addAttribute("courses", courseOperations.getAllRegisteredCoursesByUserId(user));
            model.addAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "my-courses";
    }
}
