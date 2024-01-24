package com.blog;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class MainUtil2 {
     public static void main(String[] args) {
         PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
         System.out.println(passwordEncoder.encode("testing"));
     }
         /*result=(condition)?expression1:expression2;
int x=10;
int y=20;
int max=(x>y)?x:y;
System.out.println(max);
*/

}