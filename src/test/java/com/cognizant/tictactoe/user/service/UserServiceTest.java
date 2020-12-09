package com.cognizant.tictactoe.user.service;

import com.cognizant.tictactoe.user.entity.User;
import com.cognizant.tictactoe.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userService = new UserService(userRepository);
    }

    @Test
    void login_successWithEmailAndPassword() {
        User inputUser = User.builder().email("newuser@email.com").password("password").build();

        doReturn(mockUser()).when(userRepository).findByEmailAndPassword(inputUser.getEmail(), inputUser.getPassword());

        User getUser = userService.login(inputUser);

        assertEquals(mockUser(), getUser);
        verify(userRepository, times(1)).findByEmailAndPassword(inputUser.getEmail(), inputUser.getPassword());
    }

    @Test
    void login_successWithUsernameAndPassword() {
        User inputUser = User.builder().username("newuser").password("password").build();

        doReturn(mockUser()).when(userRepository).findByUsernameAndPassword(inputUser.getUsername(), inputUser.getPassword());

        User getUser = userService.login(inputUser);

        assertEquals(mockUser(), getUser);
        verify(userRepository, times(1)).findByUsernameAndPassword(inputUser.getUsername(), inputUser.getPassword());
    }

    @Test
    void login_failWithEmailAndPassword() {
        User inputUser = User.builder().email("newuser").password("12345678").build();

        doReturn(null).when(userRepository).findByEmailAndPassword(inputUser.getEmail(), inputUser.getPassword());

        User getUser = userService.login(inputUser);

        assertEquals(null, getUser);
        verify(userRepository, times(1)).findByEmailAndPassword(inputUser.getEmail(), inputUser.getPassword());
    }

    @Test
    void login_failWithUsernameAndPassword() {
        User inputUser = User.builder().username("newuser").password("12345678").build();

        doReturn(null).when(userRepository).findByUsernameAndPassword(inputUser.getUsername(), inputUser.getPassword());

        User getUser = userService.login(inputUser);

        assertEquals(null, getUser);
        verify(userRepository, times(1)).findByUsernameAndPassword(inputUser.getUsername(), inputUser.getPassword());
    }

    @Test
    void register_success() throws Exception {
        User inputUser = User.builder().email("newuser@email.com").username("newuser").password("password").build();

        doReturn(mockUser()).when(userRepository).save(inputUser);
        doReturn(null).when(userRepository).findByEmail(inputUser.getEmail());
        doReturn(null).when(userRepository).findByUsername(inputUser.getUsername());

        User savedUser = userService.register(inputUser);

        assertEquals(mockUser(), savedUser);
        verify(userRepository, times(1)).save(inputUser);
        verify(userRepository, times(1)).findByUsername(inputUser.getUsername());
        verify(userRepository, times(1)).findByEmail(inputUser.getEmail());
    }

    @Test
    void register_failedEmailNotUnique() throws Exception {
        User inputUser = User.builder().email("newuser@email.com").username("newuser").password("password").build();

        doReturn(mockUser()).when(userRepository).findByEmail(inputUser.getEmail());

        Exception exception = assertThrows(Exception.class, () -> userService.register(inputUser));

        assertEquals("Email already in use", exception.getMessage());
        verify(userRepository, times(0)).save(inputUser);
        verify(userRepository, times(0)).findByUsername(inputUser.getUsername());
        verify(userRepository, times(1)).findByEmail(inputUser.getEmail());
    }

    @Test
    void register_failedUsernameNotUnique() throws Exception {
        User inputUser = User.builder().email("newuser@email.com").username("newuser").password("password").build();

        doReturn(null).when(userRepository).findByEmail(inputUser.getEmail());
        doReturn(mockUser()).when(userRepository).findByUsername(inputUser.getUsername());

        Exception exception = assertThrows(Exception.class, () -> userService.register(inputUser));

        assertEquals("Username already in use", exception.getMessage());
        verify(userRepository, times(0)).save(inputUser);
        verify(userRepository, times(1)).findByUsername(inputUser.getUsername());
        verify(userRepository, times(1)).findByEmail(inputUser.getEmail());
    }

    private User mockUser() {
        return User.builder()
                .email("newuser@email.com")
                .id(1)
                .username("newuser")
                .password("password")
                .build();
    }
}