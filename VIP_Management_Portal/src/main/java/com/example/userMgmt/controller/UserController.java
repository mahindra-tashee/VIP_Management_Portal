package com.example.userMgmt.controller;
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

import com.example.userMgmt.dto.DashboardStatsResponse;
import com.example.userMgmt.dto.LoginRequest;
import com.example.userMgmt.dto.ReferenceAssignRequest;
import com.example.userMgmt.dto.ReferenceFilterByQueue;
import com.example.userMgmt.dto.VipReferenceListResponse;
import com.example.userMgmt.entity.User;
import com.example.userMgmt.entity.VipReferenceList;
import com.example.userMgmt.enums.ReferenceStatus;
import com.example.userMgmt.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usermgmt")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/register-user")
	public ResponseEntity<Object> registerVipUser(@RequestBody User user) {
		return userService.registerVipUser(user);
	}
	
	@PostMapping("/login-user")
	public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest){
		
		return userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
	}
	
	@GetMapping("/get-users")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	@GetMapping("/userById/{userId}")
	public ResponseEntity<Object> getUserById(@PathVariable("userId") Long userId){
		return userService.getUserById(userId);
	}
	
	@GetMapping("/get-vip-reference-list/{userId}")
	public List<VipReferenceListResponse> getVipReferenceList(@PathVariable("userId") Long userId){
		return userService.getReferencesOnUserId(userId);
	}
	
	@GetMapping("/get-vip-reference-list/{userId}/{status}")
	public List<VipReferenceListResponse> getReferenceListBasedOnStatus(@PathVariable("userId") Long userId,@PathVariable("status") ReferenceStatus status){
		return userService.getReferencesOnUserIdAndStatus(userId, status);
	}
	
	@GetMapping("/get-dashboard-stats/{userId}")
	public DashboardStatsResponse getDashboardData(@PathVariable("userId") Long userId) {
		return userService.getDashboardStats(userId);
		
	}
	
	@PostMapping("/assign-reference")
    public ResponseEntity<String> assignReference(@RequestBody ReferenceAssignRequest request) {
        return userService.assignReference(request);
//        return ResponseEntity.ok("Reference assigned successfully.");
    }
	
	@GetMapping("/user/{userId}/queues")
	public ResponseEntity<Map<String, Object>> getUserQueues(@PathVariable("userId") Long userId) {
		List<String> queues=userService.getQueuesByUserId(userId);
		Map<String, Object> response = new HashMap<>();
	    response.put("userId", userId);
	    response.put("queues", queues);
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/user/queue/references")
	public ResponseEntity<List<VipReferenceListResponse>> getFilteredReferences(@RequestBody ReferenceFilterByQueue request) {
	    List<VipReferenceListResponse> responseList = userService.getReferencesByUserIdAndQueue(
	            request.getUserId(),
	            request.getQueue()
	    );
	    return ResponseEntity.ok(responseList);
	}
}
