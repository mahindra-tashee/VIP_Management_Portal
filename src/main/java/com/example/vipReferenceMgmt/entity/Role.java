package com.example.vipReferenceMgmt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_roles", schema = "vip_usermgmt")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleId;
	private String roleName;
	
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User user;

}
