package com.example.vipReferenceMgmt.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "reference_assignment_history", schema = "vip_usermgmt")
public class ReferenceAssignmentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromLogin;
    private String toLogin;
    private Long vipReference;
    private Integer role;  
    private String status;
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
