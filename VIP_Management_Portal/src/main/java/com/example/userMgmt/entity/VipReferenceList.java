package com.example.userMgmt.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vip_reference_list", schema = "vip_usermgmt")
public class VipReferenceList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long referenceId;
	private String referenceNo;
	private String subject;
	private LocalDateTime receivedDate;
	private String prirority;
	private String currentQueue;
}
