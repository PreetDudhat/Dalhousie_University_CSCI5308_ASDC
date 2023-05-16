package com.example.BrighterSpace.dataLayer;

import com.example.BrighterSpace.model.User;
import com.example.BrighterSpace.model.dto.Login;
import com.example.BrighterSpace.model.dto.SignUp;

import java.sql.SQLException;

public interface IUser {
    User updateUser(User updatedUser, String email);

    User addUser(SignUp data);

    User login(Login data);

    User getUserByEmail(String email) throws SQLException;
}
