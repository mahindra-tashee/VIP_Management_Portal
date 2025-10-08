package com.example.vipReferenceMgmt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "organization_master", schema = "vip_usermgmt")
public class Organization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer organizationId;
	private String organizationCode;
	private String organizationName;
}
