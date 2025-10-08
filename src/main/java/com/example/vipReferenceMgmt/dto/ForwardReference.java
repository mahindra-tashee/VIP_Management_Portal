package com.example.vipReferenceMgmt.dto;

import java.time.LocalDateTime;

import com.example.vipReferenceMgmt.entity.UserReport;

import lombok.Data;

@Data
public class ForwardReference {

	private Long id;
	private String fromLogin;
	private String toLogin;
	private Long vipReferenceId;
	private Long fromRoleId;
	private Long toRoleId;
	private LocalDateTime assignedAt;
	private String routingType;
	private String actionType;
	private String action;
	private String replyType;
	private String assigneeOrganization;
	private String assigneeOffice;
	private String assigneeDesignation;
	private String assigneeName;
	private String assignerComment;
	
}
