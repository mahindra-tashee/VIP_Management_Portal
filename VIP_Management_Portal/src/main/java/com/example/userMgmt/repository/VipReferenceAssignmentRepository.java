package com.example.userMgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.userMgmt.entity.Role;
import com.example.userMgmt.entity.User;
import com.example.userMgmt.entity.VipReferenceAssignment;
import com.example.userMgmt.entity.VipReferenceList;
import com.example.userMgmt.enums.ReferenceStatus;

@Repository
public interface VipReferenceAssignmentRepository extends JpaRepository<VipReferenceAssignment, Long>{
	  // Adjusted query for 'fromUser' instead of 'user'
    int countByFromUser_UserIdAndStatus(Long userId, ReferenceStatus status);

    // Adjusted query for 'toUser' instead of 'user'
    int countByToUser_UserIdAndStatus(Long userId, ReferenceStatus status);

    Optional<VipReferenceAssignment> findByToUserAndVipReferenceAndStatus(
    	    User toUser, VipReferenceList vipReference, ReferenceStatus status
    	);
    
    List<VipReferenceAssignment> findByToUser_UserId(Long userId);

	List<VipReferenceAssignment> findByToUser_UserIdAndStatus(Long userId, ReferenceStatus status);

}
