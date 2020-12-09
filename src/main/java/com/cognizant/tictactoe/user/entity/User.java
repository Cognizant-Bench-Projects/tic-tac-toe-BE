package com.cognizant.tictactoe.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;

}
