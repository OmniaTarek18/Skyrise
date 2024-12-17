package com.example.backend.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.backend.DTOs.LogInDTO;
import com.example.backend.Services.AccountServices;
import com.example.backend.Services.SignUpLogInModuleServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    private final AccountServices accountServices;
    private final SignUpLogInModuleServices signUpLogInModuleServices;

    @GetMapping("/email")
    public ResponseEntity<LogInDTO> checkEmail(@RequestParam String email) {
        System.out.println(email);
        return signUpLogInModuleServices.signInCheckerByEmail(email);
    }

    @PostMapping("change")
    public ResponseEntity<String> changePassword(
            @RequestParam String email,
            @RequestParam String newPassword) {

        try {
            accountServices.changePassword(email, newPassword);
            return ResponseEntity.ok().body("Password has been changed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("resetPassword/{id}")
    public ResponseEntity<Boolean> resetPassword(@PathVariable Integer id, @RequestParam String password) {
        boolean flag = this.accountServices.resetPassword(id, password);
        if (flag)
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("validate")
    public ResponseEntity<String> validateUser(@RequestParam Integer accountId, @RequestParam String password) {
        boolean isValid = accountServices.checkPassword(accountId, password);
        System.out.println(isValid);
        if (isValid)
            return ResponseEntity.ok("Valid password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteAccount(@RequestParam Integer accountId) {
        try {
            accountServices.deleteAccount(accountId);
            return ResponseEntity.ok().body("Account has been deleted.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
