package com.example.userMgmt.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.userMgmt.dto.DashboardStatsResponse;
import com.example.userMgmt.entity.User;
import com.example.userMgmt.entity.VipReferenceList;

public interface UserService {

	public ResponseEntity<Object> registerVipUser(User user);
	public ResponseEntity<Object> loginUser(String username,String password);
	public List<User> getAllUser();
	public ResponseEntity<Object> getUserById(Long userId);
	public List<VipReferenceList> getVipReferenceList(Long userId);
	public DashboardStatsResponse getDashboardStats(Long userId,String roleName);
}
