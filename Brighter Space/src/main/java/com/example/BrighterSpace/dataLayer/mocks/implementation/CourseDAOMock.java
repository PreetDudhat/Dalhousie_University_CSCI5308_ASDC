package com.example.BrighterSpace.dataLayer.mocks.implementation;

import com.example.BrighterSpace.dataLayer.mocks.ICourseDAO;
import com.example.BrighterSpace.model.Course;
import com.example.BrighterSpace.model.User;
import com.example.BrighterSpace.model.dto.CourseDTO;

import java.util.ArrayList;
import java.util.List;

public class CourseDAOMock implements ICourseDAO {
    @Override
    public List<CourseDTO> getAllCourses() {
        CourseDTO a1 = new CourseDTO(1, "A", "ASDC", "ABCD", 1, "bcd", "gkiran@dal.ca");
        List<CourseDTO> list = new ArrayList<>();
        list.add(a1);
        return list;
    }

    @Override
    public String createCourse(Course course, String email) {
        return "Course created";
    }

    @Override
    public Course getCourseById(int courseId) {
        Course a1 = new Course("A", "ASDC", "ABCD", 1);
        return a1;
    }

    @Override
    public Course registerToCourse(Integer courseId, User user) {
        Course a1 = new Course("A", "ASDC", "ABCD", 1);
        return a1;
    }

    @Override
    public List<CourseDTO> getAllRegisteredCoursesByUserId(User user) {
        CourseDTO a1 = new CourseDTO(1, "A", "ASDC", "ABCD", 1, "bcd", "gkiran@dal.ca");
        List<CourseDTO> list = new ArrayList<>();
        list.add(a1);
        return list;
    }

    @Override
    public Course updateCourse(Course course) {
        Course a1 = new Course("A", "ASDC", "ABCD", 1);
        return a1;
    }

    @Override
    public String deleteCourseById(int courseId) {
        return "Course deleted";
    }

    @Override
    public List<Course> getAllCoursesByUserId(int userId) {
        Course a1 = new Course("A", "ASDC", "ABCD", 1);
        List<Course> list = new ArrayList<>();
        list.add(a1);
        return list;
    }

    @Override
    public String deleteCourseByCourseId(int courseId) {
        return "Course deleted";
    }
}
