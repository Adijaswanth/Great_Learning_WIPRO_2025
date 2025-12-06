package com.example.validation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.validation.dao.UserRepository;
import com.example.validation.exception.ResourceNotFoundException;
import com.example.validation.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    // Save User
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Get user by ID
    public User getUserById(Long id) throws ResourceNotFoundException {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    // Update user fully by ID (PUT)
    public User updateUserById(Long id, User newUserData) throws ResourceNotFoundException {

        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        existingUser.setName(newUserData.getName());
        existingUser.setEmail(newUserData.getEmail());
        existingUser.setAge(newUserData.getAge());

        return userRepo.save(existingUser);
    }

    // Partial update user (PATCH)
    public User partialUpdateUserById(Long id, User newUserData) throws ResourceNotFoundException {

        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (newUserData.getName() != null)
            existingUser.setName(newUserData.getName());

        if (newUserData.getEmail() != null)
            existingUser.setEmail(newUserData.getEmail());

        if (newUserData.getAge() != 0)
            existingUser.setAge(newUserData.getAge());

        return userRepo.save(existingUser);
    }

    // Delete user by ID
    public void deleteUserById(Long id) throws ResourceNotFoundException {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userRepo.delete(existingUser);
    }
}
