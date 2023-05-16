package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.ICourse;
import com.example.BrighterSpace.dataLayer.mocks.implementation.CourseDAOMock;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import com.example.BrighterSpace.model.dto.CourseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;


@SpringBootTest(classes = {com.example.BrighterSpace.model.Course.class, CourseDAOMock.class})
public class CourseTest {

    @Test
    public void courseTestFromAllArgsConstructor() {
        Course a1 = new Course("A", "ASDC", "ABCD", 1);
        Assertions.assertEquals(a1.getCourseCode(), "A");
        Assertions.assertEquals(a1.getCourseName(), "ASDC");
        Assertions.assertEquals(a1.getCourseDescription(), "ABCD");
        Assertions.assertEquals(a1.getUserId(), 1);
    }

    @Test
    public void createCourseTest() throws SQLException {
        EntityFactoryProducer factory = new EntityFactoryProducer();
        ICourse courseOperations = new Course(factory.getCourseMockFactory().create());
        Course a1 = new Course("A", "ASDC", "ABCD", 1);
        String output = courseOperations.createCourse(a1, "gkiran@dal.ca");
        Assertions.assertEquals(output, "Course created");
    }

    @Test
    public void getCourseByIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        ICourse courseOperations = new Course(factory.getCourseMockFactory().create());
        Course c1 = courseOperations.getCourseById(1);
        Assertions.assertEquals(c1.getCourseCode(), "A");
        Assertions.assertEquals(c1.getCourseName(), "ASDC");
        Assertions.assertEquals(c1.getCourseDescription(), "ABCD");
    }

    @Test
    public void registerToCourseTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        ICourse courseOperations = new Course(factory.getCourseMockFactory().create());
        User user = new User();
        Course c1 = courseOperations.registerToCourse(1, user);
        Assertions.assertEquals(c1.getCourseCode(), "A");
        Assertions.assertEquals(c1.getCourseName(), "ASDC");
        Assertions.assertEquals(c1.getCourseDescription(), "ABCD");
    }

    @Test
    public void getAllRegisteredCoursesByUserIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        ICourse courseOperations = new Course(factory.getCourseMockFactory().create());
        User user = new User();
        List<CourseDTO> courseDTOList = courseOperations.getAllRegisteredCoursesByUserId(user);
        Assertions.assertEquals(courseDTOList.get(0).getCourseId(), 1);
        Assertions.assertEquals(courseDTOList.get(0).getCourseCode(), "A");
        Assertions.assertEquals(courseDTOList.get(0).getCourseName(), "ASDC");
        Assertions.assertEquals(courseDTOList.get(0).getCourseDescription(), "ABCD");
        Assertions.assertEquals(courseDTOList.get(0).getUserId(), 1);
        Assertions.assertEquals(courseDTOList.get(0).getUserName(), "bcd");
        Assertions.assertEquals(courseDTOList.get(0).getUserEmail(), "gkiran@dal.ca");
    }
    @Test
    public void updateCourseTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        ICourse courseOperations = new Course(factory.getCourseMockFactory().create());
        Course a1 = new Course("A", "ASDC", "ABCD", 1);
        Course c1 = courseOperations.updateCourse(a1);
        Assertions.assertEquals(c1.getCourseCode(), "A");
        Assertions.assertEquals(c1.getCourseName(), "ASDC");
        Assertions.assertEquals(c1.getCourseDescription(), "ABCD");
    }

    @Test
    public void deleteCourseByIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        ICourse courseOperations = new Course(factory.getCourseMockFactory().create());
        String a1 = courseOperations.deleteCourseById(1);
        Assertions.assertEquals(a1, "Course deleted");
    }

    @Test
    public void deleteCourseByCourseIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        ICourse courseOperations = new Course(factory.getCourseMockFactory().create());
        String a1 = courseOperations.deleteCourseById(1);
        Assertions.assertEquals(a1, "Course deleted");
    }

    @Test
    public void getAllCoursesByUserIdTest() throws SQLException{
        EntityFactoryProducer factory = new EntityFactoryProducer();
        ICourse eventOperations = new Course(factory.getCourseMockFactory().create());
        List<Course> courseList = eventOperations.getAllCoursesByUserId(1);
        Assertions.assertEquals(courseList.get(0).getCourseCode(), "A");
        Assertions.assertEquals(courseList.get(0).getCourseName(), "ASDC");
        Assertions.assertEquals(courseList.get(0).getCourseDescription(), "ABCD");
    }
}
