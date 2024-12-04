package com.example.backend.Controllers.PasswordManagement;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.Services.PasswordResetService;
import org.springframework.stereotype.Controller;


@Controller
@RequestMapping("/password")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String email) {

        boolean success = passwordResetService.processResetPassword(email);

        if (success) {
            return ResponseEntity.ok("Email Exists");
        } else {
            return ResponseEntity.badRequest().body("Email Doesn't Exist!");
        }
    }


}
