package com.example.vipReferenceMgmt.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.vipReferenceMgmt.dto.DashboardStatsResponse;
import com.example.vipReferenceMgmt.dto.ReferenceAssignRequest;
import com.example.vipReferenceMgmt.dto.VipReferenceListResponse;
import com.example.vipReferenceMgmt.entity.User;
import com.example.vipReferenceMgmt.entity.VipReferenceList;
import com.example.vipReferenceMgmt.enums.ReferenceStatus;

public interface UserService {
	
	public ResponseEntity<Object> registerVipUser(User user);
	public ResponseEntity<Object> loginUser(String username,String password);
	public List<User> getAllUser();
	public ResponseEntity<Object> getUserById(Long userId);
	
}
