package com.example.userMgmt.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userMgmt.dto.LoginRequest;
import com.example.userMgmt.entity.User;
import com.example.userMgmt.entity.VipReferenceList;
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
	public List<VipReferenceList> getVipReferenceList(@PathVariable("userId") Long userId){
		return userService.getVipReferenceList(userId);
	}
}
