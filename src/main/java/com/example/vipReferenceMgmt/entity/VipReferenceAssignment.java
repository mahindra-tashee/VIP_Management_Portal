package com.example.vipReferenceMgmt.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.example.vipReferenceMgmt.enums.ReferenceStatus;

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
    @JoinColumn(name = "from_login_id", referencedColumnName = "loginId")
    private UserReport fromLogin;
    
    @ManyToOne
    @JoinColumn(name = "to_login_id", referencedColumnName = "loginId")
    private UserReport toLogin;

    @ManyToOne
    @JoinColumn(name = "vip_reference_id")
    private VipReferenceList vipReference;

    @ManyToOne
    @JoinColumn(name = "from_role_id", referencedColumnName= "roleId")
    private Role fromRole;  // The role of the current user (toUser)
    
    @ManyToOne
    @JoinColumn(name = "to_role_id", referencedColumnName= "roleId")
    private Role toRole;

    @Enumerated(EnumType.STRING)
    private ReferenceStatus status;

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
