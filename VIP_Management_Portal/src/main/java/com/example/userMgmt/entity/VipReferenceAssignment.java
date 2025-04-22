package com.example.userMgmt.entity;

import java.time.LocalDateTime;

import com.example.userMgmt.enums.ReferenceStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vip_reference_assignment", schema = "vip_usermgmt")
public class VipReferenceAssignment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; // The user (either initiator or assignee)

	@ManyToOne
	@JoinColumn(name = "vip_reference_id")
	private VipReferenceList vipReference; // The reference linked to the user

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role; // The role of the user (initiator or assignee)

	@Enumerated(EnumType.STRING)
	private ReferenceStatus status;
}
