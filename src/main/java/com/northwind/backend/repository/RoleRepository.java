package com.northwind.backend.repository;

import com.northwind.backend.entities.Role;
import com.northwind.backend.entities.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(ERole name);
}