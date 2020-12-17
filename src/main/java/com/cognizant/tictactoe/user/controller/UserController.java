package com.cognizant.tictactoe.user.controller;

import com.cognizant.tictactoe.user.entity.User;
import com.cognizant.tictactoe.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Welcome to my Tic Tac Toe game!");
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        return ResponseEntity.ok(userService.login(user));
    }

    @PostMapping
    public ResponseEntity<User> register(@RequestBody User user) throws Exception {
        return new ResponseEntity<User>(userService.register(user), HttpStatus.CREATED);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(403).body(e.getMessage());
    }
}
