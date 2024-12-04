package com.example.backend.Entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Admin {

    private String password ;
    private String email ;

    public Admin(){}
    public Admin(String email , String password){
        this.email = email ;
        this.password = password ;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
