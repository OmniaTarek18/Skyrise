package com.example.Controllers;

import com.example.Entities.Admin;
import com.example.Entities.Customer;
import com.example.Services.SignUpLogInModuleServices ;

import jakarta.websocket.server.PathParam;
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
    public ResponseEntity<String> signUp(@RequestBody Customer customer){
        boolean flag = this.signUpLogInModuleServices.addCustomer(customer);
        if (flag)
            return new ResponseEntity<>("success" , HttpStatus.CREATED);
        return new ResponseEntity<>("fail", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @PostMapping(path = "signUp/admin")
    public ResponseEntity<String> signUp(@RequestBody Admin admin){
        boolean flag = this.signUpLogInModuleServices.addAdmin(admin) ;
        System.out.println(flag);
        if(flag)
            return new ResponseEntity<>("success" , HttpStatus.CREATED);
        return new ResponseEntity<>("fail", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @GetMapping("logIn")
    public ResponseEntity<Boolean> logIn(@PathParam(value = "email") String email, @PathParam(value = "password") String password) {
        return this.signUpLogInModuleServices.signInChecker(email, password);
    }




}
