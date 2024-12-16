package com.example.backend.Controllers;

import com.example.backend.Services.AdminServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
@CrossOrigin
public class AdminController {

    private final AdminServices adminServices ;

    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices ;
    }

    @PutMapping(path = "upgrade")
    public ResponseEntity<String> upgradeUser(@RequestParam String email){
        boolean flag = adminServices.upgradeUserToAdmin(email) ;
        if (flag) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.NOT_FOUND);
    }
}
