package com.example.userMgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userMgmt.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	 Optional<Role> findByRoleId(Long roleId);
}
