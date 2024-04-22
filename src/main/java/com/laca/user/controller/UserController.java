package com.laca.user.controller;

import com.laca.user.User;
import com.laca.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}/")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {

        User user = userService.getUserById(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{identificationNumber}")
    public ResponseEntity<User> getUserByIdentificationNumber(@PathVariable String identificationNumber) {
        User user = userService.getUserByIdentificationNumber(identificationNumber);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // add an authUser method
    @GetMapping("/auth")
    public ResponseEntity<User> authUser(@RequestParam String identificationNumber, @RequestParam String password) {
        User user = userService.authUser(identificationNumber, password);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User user) {
        user.setUserID(userId);
        User updatedUser = userService.updateUser(user);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}