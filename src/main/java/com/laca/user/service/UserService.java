package com.laca.user.service;
import com.laca.user.User;
import com.laca.user.dao.UserDAO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional(readOnly = true)
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    @Transactional
    public User createUser(User user) {
        return userDAO.createUser(user);
    }

    @Transactional
    public User updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Transactional
    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }
}