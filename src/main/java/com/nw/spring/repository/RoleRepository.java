package com.nw.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nw.spring.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRole(String role);
}
