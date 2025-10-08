package com.example.vipReferenceMgmt.dto;

import java.util.Set;

import com.example.vipReferenceMgmt.entity.Role;

import lombok.Data;

@Data
public class UserListDto {

	private Long id;
	private String name;
	private String organization;
	private String office;
	private String designation;
	private String loginId;
	private Set<Role> roles;
}
