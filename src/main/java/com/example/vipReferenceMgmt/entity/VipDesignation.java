package com.example.vipReferenceMgmt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name="vip_designation_master",schema="vip_usermgmt")
public class VipDesignation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer designationId;
	private String designationCode;
	private String designationName;
}
