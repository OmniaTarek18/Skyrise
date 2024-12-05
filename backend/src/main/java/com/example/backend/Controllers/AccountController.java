package com.example.backend.Controllers;

import com.example.backend.Services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AccountController {

    private final AccountServices accountServices ;

    @Autowired
    public AccountController(AccountServices accountServices) {
        this.accountServices = accountServices;
    }

    @PostMapping("/change")
    public ResponseEntity<String> changePassword(
            @RequestParam String email,
            @RequestParam String currentPassword,
            @RequestParam String newPassword) {

        try {
            accountServices.changePassword(email, currentPassword, newPassword);
            return ResponseEntity.ok("Password has been changed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    @PostMapping("resetPassword/{id}")
    public ResponseEntity<Boolean> changePassword(@PathVariable Integer id, @RequestBody String password) {
        boolean flag = this.accountServices.changePassword(id, password);
        if(flag)
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
