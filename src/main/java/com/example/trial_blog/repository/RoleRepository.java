package com.example.trial_blog.repository;

import com.example.trial_blog.entity.user_entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRole(String role);

}
