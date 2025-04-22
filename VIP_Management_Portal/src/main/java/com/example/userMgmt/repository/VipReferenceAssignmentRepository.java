package com.example.userMgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.userMgmt.entity.VipReferenceAssignment;
import com.example.userMgmt.enums.ReferenceStatus;

@Repository
public interface VipReferenceAssignmentRepository extends JpaRepository<VipReferenceAssignment, Long>{
	int countByUser_UserIdAndStatus(Long userId, ReferenceStatus status);

	int countByUser_UserIdAndRole_RoleNameAndStatus(Long userId, String roleName, ReferenceStatus status);
}
