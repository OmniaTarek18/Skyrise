package com.example.backend.Controllers;

import com.example.backend.Services.AccountServices;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AccountController {

    private final AccountServices accountServices ;

    @Autowired
    public AccountController(AccountServices accountServices) {
        this.accountServices = accountServices;
    }

    @PostMapping("resetPassword/{id}")
    public ResponseEntity<Boolean> changePassword(@PathVariable Integer id, @RequestBody String password) {
        boolean flag = this.accountServices.changePassword(id, password);
        if(flag)
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
