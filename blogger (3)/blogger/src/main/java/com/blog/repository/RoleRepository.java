package com.blog.repository;

import com.blog.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role,Long> {//role repository also have some custom methods
   Optional<Role> findByName(String name);;




}
