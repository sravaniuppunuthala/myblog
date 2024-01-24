package com.blog.security;

import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService  implements UserDetailsService {//based on username it will go to database it will find record customuserdetaailsservice
    //search the record in database based on email or username

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {//this ovveride method coming from the database we need to compare logindto so inorder to compare that we need this authenticatemanager
        //the authenticate manager take the logindto usernam e oremail  an give to loadby username or  email automatically inside spingboot

User user=userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)//whatever u give aboveline (Stringg usernameOrEmail)
        .orElseThrow(()->
        new UsernameNotFoundException("user not found with username or email:"+usernameOrEmail));
return  new org.springframework.security.core.userdetails.User(user.getEmail(),
        user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){

        return roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
