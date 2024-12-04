package com.example.backend.Services.PasswordManagement;

import com.example.backend.Entities.User;
import com.example.backend.Repositories.UserRepository;
import com.example.backend.Services.PasswordChangeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class PasswordChangeServiceTest {
    @Mock
    private UserRepository userRepository;
    private PasswordChangeService passwordChangeService;
    @BeforeEach
    void setUp() {
        this.passwordChangeService = new PasswordChangeService(this.userRepository);
    }

    @Test
    void testChangePassword_Success() throws IOException {

        User mockUser = new User();
        mockUser.setEmail("test@example.com");

        Mockito.when(userRepository.findByEmail("test@example.com")).thenReturn(mockUser);

        passwordChangeService.changePassword("test@example.com", "newPassword123");

        Mockito.verify(userRepository).findByEmail("test@example.com");
        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

    @Test
    void testChangePassword_InvalidCurrentPassword() throws IOException {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                passwordChangeService.changePassword("test@example.com", "newPassword123")
        );
    }

}

/*









}

*
* */
