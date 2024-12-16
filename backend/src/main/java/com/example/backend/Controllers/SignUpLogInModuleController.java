package com.example.backend.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.backend.DTOs.LogInDTO;
import com.example.backend.Entities.Customer;
import com.example.backend.Services.SignUpLogInModuleServices;

import jakarta.websocket.server.PathParam;



@Controller
@RequestMapping()
@CrossOrigin
public class SignUpLogInModuleController {
    private final SignUpLogInModuleServices signUpLogInModuleServices ;

    public SignUpLogInModuleController(SignUpLogInModuleServices signUpLogInModuleServices){
        this.signUpLogInModuleServices=  signUpLogInModuleServices ;
    }

    @PostMapping(path = "signUp/customer")
    public ResponseEntity<Integer> signUp(@RequestBody Customer customer){
        return this.signUpLogInModuleServices.signUpCustomer(customer);
    }

    @GetMapping("logIn")
    public ResponseEntity<LogInDTO> logIn(@PathParam(value = "email") String email, @PathParam(value = "password") String password) {
        return this.signUpLogInModuleServices.signInChecker(email, password);
    }
}
