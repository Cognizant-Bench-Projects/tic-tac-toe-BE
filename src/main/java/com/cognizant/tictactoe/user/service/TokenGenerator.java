package com.cognizant.tictactoe.user.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenGenerator {

    public String createToken(Authentication authentication) {
        DefaultOAuth2User userPrincipal = (DefaultOAuth2User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getName())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "926D96C90030DD58429D2751AC1BDBBC")
                .setClaims(userPrincipal.getAttributes())
                .compact();
    }
}
