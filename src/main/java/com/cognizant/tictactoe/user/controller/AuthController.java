package com.cognizant.tictactoe.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2/callback")
public class AuthController {

    @GetMapping
    public String authCallback() {
        return "Authentication Failed";
    }
}
