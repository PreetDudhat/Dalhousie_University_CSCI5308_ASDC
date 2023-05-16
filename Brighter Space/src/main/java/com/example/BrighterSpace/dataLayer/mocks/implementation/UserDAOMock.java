package com.example.BrighterSpace.dataLayer.mocks.implementation;

import com.example.BrighterSpace.dataLayer.IUserDAO;
import com.example.BrighterSpace.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOMock implements IUserDAO {

    private final List<User> users = new ArrayList<>();

    public UserDAOMock() {
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("john.doe@gmail.com");
        user1.setName("John Doe");
        user1.setRole("Instructor");
        user1.setPassword("1000:87f0e3c9dcef3530cdeb84074f64ba8b:4163ab87068d9cb7c47ad7ae7290fed757c1252934f9ea078173c6902dcb042a3b460ba52886355e38ea906f6de7ea33324c456f6d635362fb4fafaf48fd56e5");

        User user2 = new User();
        user2.setId(2);
        user2.setName("Jane Doe");
        user2.setEmail("jane.doe@gmail.com");
        user2.setRole("Student");
        user2.setPassword("1000:87f0e3c9dcef3530cdeb84074f64ba8b:4163ab87068d9cb7c47ad7ae7290fed757c1252934f9ea078173c6902dcb042a3b460ba52886355e38ea906f6de7ea33324c456f6d635362fb4fafaf48fd56e5");

        users.add(user1);
        users.add(user2);

    }

    @Override
    public List<User> getAllUsers() {
        return this.users;
    }

    @Override
    public User getUserByEmail(String email) {
       for(User user : this.users) {
           if(user.getEmail().equals(email)) {
               return user;
           }
       }
       return null;
    }

    @Override
    public Boolean addUser(User user) {
        return true;
    }

    @Override
    public User updateUser(User user) {
        User updatedUser = new User();
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        return updatedUser;
    }

    @Override
    public List<User> getAllInstructorsWhoHasCourses() {
        List<User> instructors = new ArrayList<>();
        instructors.add(this.users.get(0));
        return instructors;
    }
}
