package com.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
    @Entity
    @Table(name = "users")
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true)
        private String email;

        private String name;
        @Column(nullable = false)
        private String password;
        @Column(nullable = false,unique = true)
        private String username;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id",referencedColumnName="id"),
                inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName="id")
        )
        private Set<Role> roles=new HashSet<>();//why used set here bcoz set not allow duplicates users cannot be duplicated
        //data structures used in while developing spring security feature i had implemented datastructure called as set datastructure becoz users cannot be duplicated so i used set ,the set not allows duplicates,user also unique ,roles also unique

        // Constructors, getters, setters, and other methods

    }




