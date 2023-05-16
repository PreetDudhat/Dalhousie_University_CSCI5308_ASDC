package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.IUser;
import com.example.BrighterSpace.factory.EntityFactoryProducer;
import com.example.BrighterSpace.model.dto.Login;
import com.example.BrighterSpace.model.dto.SignUp;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {

    private final EntityFactoryProducer factory = new EntityFactoryProducer();

    private final IUser userService = new User(factory.getUserMockFactory().create());

    @Test
    public void userGetterAndSetterTest() {
        User user = new User();
        user.setName("Radhey Patel");
        user.setRole("Instructor");
        user.setPassword("root");
        user.setEmail("rd433112@gmail.com");

        assertEquals(user.getName(), "Radhey Patel");
        assertEquals(user.getEmail(), "rd433112@gmail.com");
        assertEquals(user.getPassword(), "root");
        assertEquals(user.getRole(), "Instructor");
    }

    @Test
    public void addUserThatDoesNotExistTest() {
        SignUp form = new SignUp();
        form.setName("Radhey Patel");
        form.setEmail("rd433112@dal.ca");
        form.setPassword("root");
        form.setRole("Instructor");

        User user = userService.addUser(form);

        assertEquals(user.getName(), form.getName());
        assertEquals(user.getEmail(), form.getEmail());
        assertEquals(user.getRole(), form.getRole());
    }

    @Test
    public void addUserThatDoesExistTest() {
        SignUp form = new SignUp();
        form.setName("John Doe");
        form.setEmail("john.doe@gmail.com");
        form.setPassword("root");
        form.setRole("Instructor");
        User user = userService.addUser(form);
        assertNull(user);
    }

    @Test
    public void loginUserWithCorrectCredentialsTest() {
        Login form = new Login();
        form.setEmail("john.doe@gmail.com");
        form.setPassword("root");
        User user = userService.login(form);
        assertEquals(user.getEmail(), form.getEmail());
        assertEquals(user.getName(), "John Doe");
        assertEquals(user.getEmail(), form.getEmail());
        assertEquals(user.getRole(), "Instructor");
    }

    @Test
    public void loginUserWithIncorrectCredentials() {
        Login form = new Login();
        form.setEmail("john.doe@gmail.com");
        form.setPassword("wrong-password");
        User user = userService.login(form);
        assertNull(user);
    }

    @Test
    public void loginUserThatDoesNotExist() {
        Login form = new Login();
        form.setEmail("user.does.not.exist@gmail.com");
        form.setPassword("root");
        User user = userService.login(form);
        assertNull(user);
    }

    @Test
    public void getUserByEmailTest() throws SQLException {
        String email = "john.doe@gmail.com";
        User user = userService.getUserByEmail(email);
        assertEquals(user.getName(), "John Doe");
        assertEquals(user.getEmail(), "john.doe@gmail.com");
        assertEquals(user.getRole(), "Instructor");
    }

    @Test
    public void getUserByWrongEmailTest() throws SQLException{
        String email = "user.does.not.exist@gmail.com";
        User user = userService.getUserByEmail(email);
        assertNull(user);
    }

    @Test
    public void updateUserThatExist() {
        User user = new User();
        user.setName("Updated Name");
        User updatedUser = userService.updateUser(user, "john.doe@gmail.com");
        assertEquals(updatedUser.getEmail(), "john.doe@gmail.com");
        assertEquals(updatedUser.getName(), "Updated Name");
    }

    @Test
    public void updateUserThatDoesNotExist() {
        User user = new User();
        user.setName("Updated Name");
        User updatedUser = userService.updateUser(user, "user.does.not.exist@gmail.com");
        assertNull(updatedUser);
    }
}
