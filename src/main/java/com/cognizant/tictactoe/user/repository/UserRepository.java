package com.cognizant.tictactoe.user.repository;

import com.cognizant.tictactoe.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmailAndPassword(String email, String Password);
    User findByUsernameAndPassword(String username, String Password);

    User findByEmail(String email);
    User findByUsername(String username);
}
