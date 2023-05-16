package com.example.BrighterSpace.dataLayer.mocks;

import com.example.BrighterSpace.model.Course;
import com.example.BrighterSpace.model.User;
import com.example.BrighterSpace.model.dto.CourseDTO;

import java.sql.SQLException;
import java.util.List;

public interface ICourseDAO {
    List<CourseDTO> getAllCourses() throws SQLException;

    String createCourse(Course course, String email) throws SQLException;

    Course getCourseById(int courseId) throws SQLException;

    Course registerToCourse(Integer courseId, User user) throws SQLException;

    List<CourseDTO> getAllRegisteredCoursesByUserId(User user) throws SQLException;

    Course updateCourse(Course course) throws SQLException;

    String deleteCourseById(int courseId);

    List<Course> getAllCoursesByUserId(int userId) throws SQLException;

    String deleteCourseByCourseId(int courseId) throws SQLException;
}
