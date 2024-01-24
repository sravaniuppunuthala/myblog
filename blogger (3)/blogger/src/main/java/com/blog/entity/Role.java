package com.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

    @Entity
    @Table(name = "roles")
    @Setter
    @Getter
    public class Role {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true)
        private String roleName;

       @ManyToMany(mappedBy = "roles")
        private Set<User> users = new HashSet<>();
       private String name;

        public String getName() {
            return null;
        }

        // Constructors, getters, setters, and other methods

    }



