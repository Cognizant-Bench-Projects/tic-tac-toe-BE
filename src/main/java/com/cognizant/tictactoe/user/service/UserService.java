package com.cognizant.tictactoe.user.service;

import com.cognizant.tictactoe.user.entity.User;
import com.cognizant.tictactoe.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(User user) {
        if (user.getEmail() != null) {
            return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        }
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    public User register(User user) throws Exception {
        User getUserByEmail = userRepository.findByEmail(user.getEmail());
        if (getUserByEmail != null) {
            throw new Exception("Email already in use");
        }
        User getUserByUsername = userRepository.findByUsername(user.getUsername());
        if (getUserByUsername != null) {
            throw new Exception("Username already in use");
        }
        return userRepository.save(user);
    }
}
