package com.example.backend.Account;

import com.example.backend.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int accountId ;
   @Column
   private boolean role ;  //0 if normal user 1 if admin
   @Column(unique = true , nullable = false)
   private  String email ;
   @Column(nullable = false)
   private String password;




   @OneToOne(mappedBy = "account")
   private User user ;


   public Account() {}

   public Account(String email, String password, boolean role) {
      this.email = email;
      this.password = password;
      this.role = role;
   }
}
