package com.example.BrighterSpace.model;

import com.example.BrighterSpace.dataLayer.IUser;
import com.example.BrighterSpace.dataLayer.IUserDAO;
import com.example.BrighterSpace.model.dto.Login;
import com.example.BrighterSpace.model.dto.SignUp;
import com.example.BrighterSpace.utils.MailService;
import com.example.BrighterSpace.utils.PasswordUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;

import java.sql.SQLException;

public class User implements IUser {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    @Lazy
    private final PasswordUtilities passwordUtilities = new PasswordUtilities();

    private Integer id;
    private String name;

    private String email;

    private String password;

    private String role;

    private IUserDAO userDAO;

    public User() {
    }

    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public User updateUser(User updatedUser, String email) {
        try {
            User user = userDAO.getUserByEmail(email);
            if (user == null) {
                throw new Exception("User does not exist");
            }
            updatedUser.setEmail(email);
            return userDAO.updateUser(updatedUser);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public User addUser(SignUp data) {
        logger.debug("Registering the user");

        try {
            User user = userDAO.getUserByEmail(data.getEmail());
            if (user != null) {
                logger.error("User already exists");
                throw new Exception("User already exists");
            }

            user = new User(
                    data.getName(),
                    data.getEmail(),
                    passwordUtilities.generateStrongPasswordHash(data.getPassword()),
                    data.getRole()
            );
            if (userDAO.addUser(user)) {
                logger.debug("User created successfully: {" + user + "}");

                MailService mailService = new MailService();
                mailService.sendRegistrationMail(data.getEmail());

                return user;
            } else {
                logger.error("Something went wrong while creating user");
                throw new Exception("User not created");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public User login(Login data) {
        try {
            User user = userDAO.getUserByEmail(data.getEmail());
            if (user == null) {
                throw new Exception("User does not exists");
            }

            String storedPassword = user.getPassword();
            if(
                    passwordUtilities.validatePassword(data.getPassword(), storedPassword)
            ) {
                logger.debug("User {" + user + "} logged in");
                return user;
            } else {
                throw new Exception("Invalid credentials");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        User user = userDAO.getUserByEmail(email);
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
