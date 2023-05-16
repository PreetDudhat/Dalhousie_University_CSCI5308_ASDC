package com.example.BrighterSpace.model;


import com.example.BrighterSpace.dataLayer.ICourse;
import com.example.BrighterSpace.dataLayer.mocks.ICourseDAO;
import com.example.BrighterSpace.model.dto.CourseDTO;

import java.sql.SQLException;
import java.util.List;

public class Course implements ICourse {

    private Integer courseId;
    private String courseCode;
    private String courseName;
    private String courseDescription;
    private Integer userId;
    private ICourseDAO courseDAO;

    public Course(String courseCode, String courseName, String courseDescription) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    public Course(String courseCode, String courseName, String courseDescription, Integer userId) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.userId = userId;
    }

    public Course() {

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Course(ICourseDAO doa) {
        this.courseDAO = doa;
    }


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }


    @Override
    public List<CourseDTO> getAllCourses() throws SQLException {
        return courseDAO.getAllCourses();
    }

    @Override
    public String createCourse(Course course, String email) throws SQLException {
        return courseDAO.createCourse(course, email);
    }

    @Override
    public Course getCourseById(int courseId) throws SQLException {
        return courseDAO.getCourseById(courseId);
    }

    @Override
    public Course updateCourse(Course course) throws SQLException {
        return courseDAO.updateCourse(course);
    }

    @Override
    public String deleteCourseById(int courseId) throws SQLException {
        return courseDAO.deleteCourseByCourseId(courseId);
    }

    @Override
    public List<Course> getAllCoursesByUserId(int userId) throws SQLException {
        return courseDAO.getAllCoursesByUserId(userId);
    }

    @Override
    public Course registerToCourse(Integer courseId, User user) throws SQLException {
        return courseDAO.registerToCourse(courseId, user);
    }

    @Override
    public List<CourseDTO> getAllRegisteredCoursesByUserId(User user) throws SQLException {
        return courseDAO.getAllRegisteredCoursesByUserId(user);
    }
}
