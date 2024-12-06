package com.example.backend.Controllers;

import com.example.backend.Entities.Admin;
import com.example.backend.Entities.Customer;
import com.example.backend.EntityDTOS.LogInDTO;
import com.example.backend.Enums.Role;
import com.example.backend.Services.SignUpLogInModuleServices;

import jakarta.websocket.server.PathParam;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping()
public class SignUpLogInModuleController {
    private final SignUpLogInModuleServices signUpLogInModuleServices ;


    @Autowired
    public SignUpLogInModuleController(SignUpLogInModuleServices signUpLogInModuleServices){
        this.signUpLogInModuleServices=  signUpLogInModuleServices ;
    }



    @PostMapping(path = "signUp/customer")
    public ResponseEntity<Integer> signUp(@RequestBody Customer customer){
        return this.signUpLogInModuleServices.signUpCustomer(customer);
    }

    @PostMapping(path = "signUp/admin")
    public ResponseEntity<Integer> signUp(@RequestBody Admin admin){
        return this.signUpLogInModuleServices.signUpAdmin(admin) ;
    }

    @GetMapping("logIn")
    public ResponseEntity<LogInDTO> logIn(@PathParam(value = "email") String email, @PathParam(value = "password") String password) {
        return this.signUpLogInModuleServices.signInChecker(email, password);
    }




}
