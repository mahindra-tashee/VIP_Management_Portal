package com.example.vipReferenceMgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vipReferenceMgmt.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	 Optional<Role> findByRoleId(Long roleId);
}
