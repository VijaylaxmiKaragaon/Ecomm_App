package com.ecomm.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.user.entity.Role;
import com.ecomm.user.enums.RoleType;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByRoleName(RoleType roleName);
}
