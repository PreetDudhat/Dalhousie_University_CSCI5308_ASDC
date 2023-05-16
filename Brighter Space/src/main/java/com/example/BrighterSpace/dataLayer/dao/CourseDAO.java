package com.example.BrighterSpace.dataLayer.dao;

import com.example.BrighterSpace.dataLayer.mocks.ICourseDAO;
import com.example.BrighterSpace.model.Course;
import com.example.BrighterSpace.model.User;
import com.example.BrighterSpace.model.dto.CourseDTO;
import org.springframework.context.annotation.Lazy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CourseDAO implements ICourseDAO {
    @Lazy
    private final Connection conn = com.example.BrighterSpace.config.Connection.getInstance();

    private final UserDAO userDAO = new UserDAO();

    public String createCourse(Course course, String email) throws SQLException {
        try {
            User user = userDAO.getUserByEmail(email);
            if(user.getRole().equalsIgnoreCase("instructor")) {

                String query = "{CALL createCourse(?, ?, ?, ?)}";

                CallableStatement statement = conn.prepareCall(query);
                statement.setString(1, course.getCourseCode());
                statement.setString(2, course.getCourseName());
                statement.setString(3, course.getCourseDescription());
                statement.setInt(4, user.getId());
                statement.executeQuery();
                return "Course added";
            } else {
                throw new Exception("Not authorized");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }


    public Course getCourseById(int courseId) throws SQLException {
        String query = "{CALL GetCourseById(?)}";
        Course course = null;
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                course = new Course(
                        resultSet.getString("courseCode"),
                        resultSet.getString("courseName"),
                        resultSet.getString("courseDescription"),
                        Integer.parseInt(resultSet.getString("user_id"))
                );
                course.setCourseId(resultSet.getInt(1));
            }
            return course;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public Course updateCourse(Course course) throws SQLException {
        String query = "{CALL updateCourse(?, ?, ?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, course.getCourseId());
            statement.setString(2, course.getCourseName());
            statement.setString(3, course.getCourseDescription());
            statement.executeUpdate();
            System.out.println(course.getCourseId());
            return course;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    @Override
    public String deleteCourseById(int courseId) {
        return null;
    }

    public String deleteCourseByCourseId(int courseId) throws SQLException {
        String query = "{CALL DeleteCourseByCourseId(?)}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            statement.setInt(1, courseId);
            statement.execute();
            return "Record deleted";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }


    public List<CourseDTO> getAllCourses() throws SQLException {
        List<CourseDTO> courses = new ArrayList<>();
        String query = "{CALL GetAllCourses()}";
        try {
            CallableStatement statement = conn.prepareCall(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CourseDTO course = new CourseDTO();
                course.setCourseId(resultSet.getInt(1));
                course.setCourseCode(resultSet.getString(2));
                course.setCourseName(resultSet.getString(3));
                course.setCourseDescription(resultSet.getString(4));
                course.setUserId(resultSet.getInt(5));
                course.setUserName(resultSet.getString(6));
                course.setUserEmail(resultSet.getString(7));
                courses.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
        return courses;
    }

    public List<Course> getAllCoursesByUserId(int userId) throws SQLException {
        try {
            PreparedStatement statement = conn.prepareCall("{CALL getCoursesByUserId(?)}");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Course> courses = new ArrayList<>();
            Course course;
            while (resultSet.next()) {
                course = new Course(
                        resultSet.getString("courseCode"),
                        resultSet.getString("courseName"),
                        resultSet.getString("courseDescription"),
                        resultSet.getInt("user_id")
                );
                course.setCourseId(resultSet.getInt(1));
                courses.add(course);
            }
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            conn.close();
        }
    }

    public Course getCourseByCourseIdAndUserId(int courseId, int userId) throws SQLException {
        try {
            PreparedStatement statement = conn.prepareCall("{CALL getCourseByCourseIdAndUserId(?,?)}");
            statement.setInt(1, courseId);
            statement.setInt(2, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Course course = new Course(
                        resultSet.getString("courseCode"),
                        resultSet.getString("courseName"),
                        resultSet.getString("courseDescription"),
                        resultSet.getInt("user_id")
                );
                course.setCourseId(resultSet.getInt(1));
                return course;
            } else {
                throw new Exception("No such course for user");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            conn.close();
        }
    }

    public List<Course> getCourseByUserId(int userId) throws SQLException {
        try {

            PreparedStatement statement = conn.prepareCall("{CALL getCoursesByUserId(?)}");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Course> courses = new ArrayList<>();
            Course course;
            while(resultSet.next()) {
                course = new Course(
                        resultSet.getString("courseCode"),
                        resultSet.getString("courseName"),
                        resultSet.getString("courseDescription"),
                        resultSet.getInt("user_id")
                );
                course.setCourseId(resultSet.getInt(1));
                courses.add(course);
            }
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            conn.close();
        }
    }

    public Course registerToCourse(Integer courseId, User user) throws SQLException {
        try {
            PreparedStatement statement = conn.prepareStatement("{CALL GetCourseById(?)}");
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            Course course;
            if(resultSet.next()) {
                course = new Course();
                course.setCourseId(resultSet.getInt(1));
                course.setCourseCode(resultSet.getString(2));
                course.setCourseName(resultSet.getString(3));
                course.setCourseDescription(resultSet.getString(4));
                course.setUserId(resultSet.getInt(5));

                statement = conn.prepareStatement("{CALL registerToCourse(?,?)}");
                statement.setInt(1, courseId);
                statement.setInt(2, user.getId());
                statement.executeQuery();
                return course;
            } else {
                throw new Exception("Course not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            conn.close();
        }
    }

    public List<CourseDTO> getAllRegisteredCoursesByUserId(User user) throws SQLException {
        try{
            PreparedStatement statement = conn.prepareStatement("{CALL getAllRegisteredCoursesByUserId(?)}");
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            List<CourseDTO> courses = new ArrayList<>();
            CourseDTO course;
            while(resultSet.next()) {
                course = new CourseDTO();
                course.setCourseId(resultSet.getInt(1));
                course.setCourseCode(resultSet.getString(2));
                course.setCourseName(resultSet.getString(3));
                course.setCourseDescription(resultSet.getString(4));
                course.setUserId(resultSet.getInt(5));
                course.setUserName(resultSet.getString(6));
                course.setUserEmail(resultSet.getString(7));
                courses.add(course);
            }
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            conn.close();
        }
    }
}
