package com.example.backend.Entities;


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
   @Column(nullable = false)
   private boolean role ;  //0 if normal user 1 if admin
   @Column(unique = true , nullable = false)
   private  String email ;
   @Column(nullable = false)
   private String password;




   public int getAccountId() {
      return accountId;
   }

   public void setAccountId(int accountId) {
      this.accountId = accountId;
   }

   public boolean isRole() {
      return role;
   }

   public void setRole(boolean role) {
      this.role = role;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   @OneToOne(mappedBy = "account")
   private User user ;


   public Account() {}

   public Account(String email, String password, boolean role) {
      this.email = email;
      this.password = password;
      this.role = role;
   }


}
