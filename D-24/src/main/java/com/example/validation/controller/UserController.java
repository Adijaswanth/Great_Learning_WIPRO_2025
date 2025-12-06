package com.example.validation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.validation.model.User;
import com.example.validation.service.UserService;
import com.example.validation.exception.ResourceNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // POST
    @PostMapping("/add")
    public ResponseEntity<String> saveUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>("Added", HttpStatus.OK);
    }

    // GET ALL
    @GetMapping("/viewall")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    // GET BY ID
    @GetMapping("/view/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws ResourceNotFoundException {
        userService.deleteUserById(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    // PUT - full update
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user)
            throws ResourceNotFoundException {

        userService.updateUserById(id, user);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    // PATCH - partial
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> partialUpdateUser(@PathVariable Long id, @RequestBody User user)
            throws ResourceNotFoundException {

        userService.partialUpdateUserById(id, user);
        return new ResponseEntity<>("User partially updated", HttpStatus.OK);
    }
}
