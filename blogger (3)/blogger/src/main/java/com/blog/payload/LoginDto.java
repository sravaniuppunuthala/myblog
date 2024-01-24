package com.blog.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;//this username and password present in loginDto compared username to password in database
    private String password;




}
