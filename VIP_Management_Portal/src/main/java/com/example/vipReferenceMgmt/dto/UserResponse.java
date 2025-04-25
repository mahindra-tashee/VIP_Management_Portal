package com.example.vipReferenceMgmt.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.vipReferenceMgmt.entity.Role;

import lombok.Data;
@Data
public class UserResponse {
	  private String name;
	  private Long userId;
	  private String userName;
	  private LocalDateTime createdAt;
	  private Set<Role> roles = new HashSet<>();
}
