package com.example.vipReferenceMgmt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "role_mapping", schema = "vip_usermgmt")
public class UserRoleMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String loginId;
	private String name;
	private int roleId;
	private String organization;
	private String office;
	private String designation;
}
