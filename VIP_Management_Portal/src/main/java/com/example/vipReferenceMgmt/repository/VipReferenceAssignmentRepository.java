package com.example.vipReferenceMgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vipReferenceMgmt.entity.Role;
import com.example.vipReferenceMgmt.entity.UserReport;
import com.example.vipReferenceMgmt.entity.VipReferenceAssignment;
import com.example.vipReferenceMgmt.entity.VipReferenceList;
import com.example.vipReferenceMgmt.enums.ReferenceStatus;

@Repository
public interface VipReferenceAssignmentRepository extends JpaRepository<VipReferenceAssignment, Long>{
	  // Adjusted query for 'fromUser' instead of 'user'
    int countByFromLogin_LoginIdAndStatus(String loginId, ReferenceStatus status);

    // Adjusted query for 'toUser' instead of 'user'
    int countByToLogin_LoginIdAndStatus(String loginId, ReferenceStatus status);

    Optional<VipReferenceAssignment> findByToLoginAndVipReferenceAndStatus(
    		UserReport toLogin, VipReferenceList vipReference, ReferenceStatus status
    	);
    
    List<VipReferenceAssignment> findByToLogin_LoginId(String loginId);

	List<VipReferenceAssignment> findByToLogin_LoginIdAndStatus(String loginId, ReferenceStatus status);

	List<VipReferenceAssignment> findByToLogin_LoginIdAndRole_RoleName(String loginId, String queueName);

}
