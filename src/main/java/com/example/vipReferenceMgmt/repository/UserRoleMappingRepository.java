package com.example.vipReferenceMgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vipReferenceMgmt.dto.FinalAssigneeListDto;
import com.example.vipReferenceMgmt.entity.UserRoleMapping;

@Repository
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMapping, Long>{

	List<UserRoleMapping> findByRoleId(int roleId);
}
