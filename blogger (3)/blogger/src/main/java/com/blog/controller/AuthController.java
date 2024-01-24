package com.blog.controller;

import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.payload.JWTAuthResponse;
import com.blog.payload.LoginDto;
import com.blog.payload.SignUpDto;

import com.blog.repository.RoleRepository;
import com.blog.repository.UserRepository;
import com.blog.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
   @Autowired
private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider tokenProvider;

   /* @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){//this has the data entered by the user ,ur not going to using if ocndition compared spring security will do automatically
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(//this usernamepasswordauthenticationtoken stored user  defined username and password take the object and give it to authenticate and going authenticate  to loadbyusername ,the loadbyusername take data from database and compare to username and or email it is true the data stored in sessionvariable,login is succeful user login  after entering user username or email that user details compared after comparartion results are stored in authentication

                loginDto.getUsernameOrEmail(),loginDto.getPassword());
        Authentication authentication=authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);//after compare it will set the details into context then application is eligible to use
        return  new  ResponseEntity<>("User signed-in successfully!",HttpStatus.OK);
    }*/

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){//once username password is valid token generated  it will goto logindto
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(//after that its verify
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));//after that its verify

        SecurityContextHolder.getContext().setAuthentication(authentication);//after valid securitycontextholder set the username and password is valid,if it is valid generateToken method generates token for this uername and password

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);//if it is valid generateToken method generates token for this uername and password and already generateToken method is present in tokenprovider class


        return ResponseEntity.ok(new JWTAuthResponse(token));//it takes that token to tokenprovider and it shows as response
    }



    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        if(userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email id exists:" + signUpDto.getEmail(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username exists:"+signUpDto.getUsername(),HttpStatus.INTERNAL_SERVER_ERROR);
        }



        User user=new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));


        Role roles=roleRepository.findByName("ROLE_ADMIN").get();//it will get role object,this user who signups is an admin user ,once he an admin he create the post,update the post,delete the post if login as non admin user we can only see the post not update ,delete,all
        user.setRoles(Collections.singleton(roles));//we using (collections.singleton(roles) )method its convert role object to set

        User savedUser=userRepository.save(user);
        return  new ResponseEntity<>("User registered successfully", HttpStatus.OK);


    }



}
