package com.example.userMgmt.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.userMgmt.entity.Role;

import lombok.Data;
@Data
public class UserResponse {
	  private String name;
	  private Long userId;
	  private String userName;
	  private LocalDateTime createdAt;
	  private Set<Role> roles = new HashSet<>();
}
