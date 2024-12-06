package com.example.backend.Controllers.PasswordManagement;

import com.example.backend.Services.PasswordChangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/password")
public class PasswordChangeController {

    private final PasswordChangeService passwordChangeService;

    public PasswordChangeController(PasswordChangeService passwordChangeService) {
        this.passwordChangeService = passwordChangeService;
    }

    @PostMapping("/change")
    public ResponseEntity<String> changePassword(
            @RequestParam String email,
            @RequestParam String newPassword) {

        try {
            passwordChangeService.changePassword(email, newPassword);
            return ResponseEntity.ok("Password has been changed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
