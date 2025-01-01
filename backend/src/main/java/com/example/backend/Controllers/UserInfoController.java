package com.example.backend.Controllers;

import com.example.backend.DTOs.UserNameDTO;
import com.example.backend.Services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class UserInfoController {

    private final UserServices userService;

    @GetMapping("/user/{userId}/name")
    public ResponseEntity<UserNameDTO> getUserName(@PathVariable Integer userId) {
        UserNameDTO userName = userService.getUserName(userId);
        return ResponseEntity.ok(userName);
    }
}
