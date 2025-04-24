package com.example.userMgmt.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.userMgmt.dto.DashboardStatsResponse;
import com.example.userMgmt.dto.ReferenceAssignRequest;
import com.example.userMgmt.dto.VipReferenceListResponse;
import com.example.userMgmt.entity.User;
import com.example.userMgmt.entity.VipReferenceList;
import com.example.userMgmt.enums.ReferenceStatus;

public interface UserService {

	public ResponseEntity<Object> registerVipUser(User user);
	public ResponseEntity<Object> loginUser(String username,String password);
	public List<User> getAllUser();
	public ResponseEntity<Object> getUserById(Long userId);
	public List<VipReferenceListResponse> getReferencesOnUserId(Long userId);
	public List<VipReferenceListResponse> getReferencesOnUserIdAndStatus(Long userId,ReferenceStatus status);
	public DashboardStatsResponse getDashboardStats(Long userId);
	public ResponseEntity<String> assignReference(ReferenceAssignRequest reference);
	public List<String> getQueuesByUserId(Long userId);
	public List<VipReferenceListResponse> getReferencesByUserIdAndQueue(Long userId, String queueName);
}
