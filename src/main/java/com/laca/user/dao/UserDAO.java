package com.laca.user.dao;

import com.laca.user.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    User getUserById(int userId);
    User getUserByIdentificationNumber(String identificationNumber);
    User authUser(String identificationNumber, String password);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(int userId);
}