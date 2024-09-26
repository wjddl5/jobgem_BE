package com.sist.jobgem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sist.jobgem.dto.LoginResponse;
import com.sist.jobgem.entity.User;
import com.sist.jobgem.enums.LoginStatusEnum;
import com.sist.jobgem.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testLoginSuccess() {
        // Mock data
        String usId = "user@example.com";
        String usPw = "password";
        String hashedPassword = "hashed_password"; // replace with the actual hashed password

        User user = User.builder()
            .usId(usId)
            .usPw(hashedPassword)
            .build();

        // Mock behavior
        when(userRepository.findByUsId(usId)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(usPw, hashedPassword)).thenReturn(true);

        // Call the method
        LoginResponse response = userService.login(usId, usPw);

        // Verify results
        assertEquals(LoginStatusEnum.LOGIN_SUCCESS.getMessage(), response.getMsg());
    }
}
