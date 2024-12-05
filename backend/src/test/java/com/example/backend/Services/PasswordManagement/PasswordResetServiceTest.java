//package com.example.backend.Services.PasswordManagement;
//
//import com.example.backend.Entities.User;
//import com.example.backend.Repositories.UserRepository;
//import com.example.backend.Services.PasswordResetService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.io.IOException;
//
//import static org.springframework.test.util.AssertionErrors.assertEquals;
//
//@ExtendWith(MockitoExtension.class)
//public class PasswordResetServiceTest {
//    @Mock
//    private UserRepository userRepository;
//    private PasswordResetService passwordResetService;
//    @BeforeEach
//    void setUp() {
//        this.passwordResetService = new PasswordResetService(this.userRepository);
//    }
//
//    @Test
//    void testResetPassword_Success() throws IOException {
//
//        User mockUser = new User();
//        mockUser.setEmail("test@example.com");
//
//        Mockito.when(userRepository.existsByEmail("test@example.com")).thenReturn(1);
//
//        passwordResetService.processResetPassword("test@example.com");
//
//        Mockito.verify(userRepository).existsByEmail("test@example.com");
//
//    }
//
//    @Test
//    void testResetPassword_UserNotFound() {
//
//        Mockito.when(userRepository.existsByEmail("unknown@example.com")).thenReturn(0);
//        boolean success = passwordResetService.processResetPassword("unknown@example.com");
//        assertEquals("It found nonExisting user", false, success);
//    }
//
//}
//
//
//
