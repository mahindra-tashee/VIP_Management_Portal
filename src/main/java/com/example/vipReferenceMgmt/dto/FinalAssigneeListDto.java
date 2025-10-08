package com.example.vipReferenceMgmt.dto;

import java.util.List;
import java.util.Set;

import com.example.vipReferenceMgmt.entity.Role;

import lombok.Data;

@Data
public class FinalAssigneeListDto {
	
	private int id;
	private String name;
	private String organization;
	private String office;
	private String designation;
	private String loginId;
	private Set<Role> roles;
//	private List<Long> roleIds;
}
