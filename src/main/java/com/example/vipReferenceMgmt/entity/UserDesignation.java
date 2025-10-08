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
@Table(name="user_designation_master",schema="vip_usermgmt")
public class UserDesignation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer designationId;
	private String designationCode;
	private String designationDescription;
	
	@ManyToOne
	@JoinColumn(name = "organization_id")
	private Organization organization;
}
