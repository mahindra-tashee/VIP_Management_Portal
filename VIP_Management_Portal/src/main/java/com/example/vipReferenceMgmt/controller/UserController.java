package com.example.vipReferenceMgmt.controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vipReferenceMgmt.dto.DashboardStatsResponse;
import com.example.vipReferenceMgmt.dto.LoginRequest;
import com.example.vipReferenceMgmt.dto.ReferenceAssignRequest;
import com.example.vipReferenceMgmt.dto.ReferenceFilterByQueue;
import com.example.vipReferenceMgmt.dto.VipReferenceListResponse;
import com.example.vipReferenceMgmt.entity.UserReport;
import com.example.vipReferenceMgmt.entity.VipReferenceList;
import com.example.vipReferenceMgmt.enums.ReferenceStatus;
import com.example.vipReferenceMgmt.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usermgmt")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/register-user")
	public ResponseEntity<Object> registerVipUser(@RequestBody UserReport user) {
		return userService.registerVipUser(user);
	}
	
	@PostMapping("/login-user")
	public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest){
		
		return userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
	}
	
	@GetMapping("/get-users")
	public List<UserReport> getAllUser(){
		return userService.getAllUser();
	}
	
	@GetMapping("/user/{loginId}")
	public ResponseEntity<Object> getUserById(@PathVariable("loginId") String loginId){
		return userService.getUserByLoginId(loginId);
	}
	
	
}
