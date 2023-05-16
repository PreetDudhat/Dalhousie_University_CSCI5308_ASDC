package com.example.BrighterSpace.controller;


import com.example.BrighterSpace.dataLayer.IUser;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import com.example.BrighterSpace.model.User;
import com.example.BrighterSpace.model.dto.Login;
import com.example.BrighterSpace.model.dto.SignUp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    EntityFactoryProducer factoryProducer = new EntityFactoryProducer();
    IUser userService = new User(factoryProducer.getUserFactory().create());

    @PostMapping("/api/register")
    public String signup(@ModelAttribute SignUp data) {
        User user = userService.addUser(data);
        if (user != null) {
            return "homepage";
        } else {
            return "error";
        }
    }

    @GetMapping("/api/register")
    public String signup(Model model, SignUp data) {
        model.addAttribute(data);
        return "register";
    }

    @GetMapping("/api/login")
    public String login(Login login, Model model) {
        model.addAttribute(login);
        return "login";
    }

    @PostMapping("/api/login")
    public String login(@ModelAttribute Login data, HttpServletRequest request) {
        User user = userService.login(data);
        if (user != null) {
            List<String> userList = (List<String>) request.getSession().getAttribute("userId");
            if (userList == null) {
                userList = new ArrayList<>();
            }
            userList.add(data.getEmail());
            request.getSession().setAttribute("userId", userList);
            if (user.getRole().equalsIgnoreCase("instructor")) {
                return "instructor-dashboard";
            } else {
                return "student-dashboard";
            }
        } else {
            return "error";
        }
    }

    @GetMapping("/api/user/update")
    public String updateUser(Model model, HttpServletRequest request)  {
        try{
            String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
            model.addAttribute(userService.getUserByEmail(userEmail));
        } catch (Exception e){
            e.printStackTrace();
        }

        return "update-user";
    }

    @PostMapping("/api/user/update")
    public String updateUser(@ModelAttribute User user, HttpServletRequest request) {
        String userEmail = ((List<String>) request.getSession().getAttribute("userId")).get(0);
        userService.updateUser(user, userEmail);
        return "update-user";
    }

    @GetMapping("/")
    public String getHomePage() {
        return "homepage";
    }

    @GetMapping("/api/user/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "homepage";
    }

    @GetMapping("/api/student/dashboard")
    public String getStudentDashboard() {
        return "student-dashboard";
    }

    @GetMapping("/api/instructor/dashboard")
    public String getInstructorDashboard() {
        return "instructor-dashboard";
    }

}
