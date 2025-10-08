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
@Table(name = "office_master", schema = "vip_usermgmt")
public class OfficeType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer officeId;
	private String officeName;
	
	@ManyToOne
	@JoinColumn(name = "organization_id")
	private Organization organization;
}
