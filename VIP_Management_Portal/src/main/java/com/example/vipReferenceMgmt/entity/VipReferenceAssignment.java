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
    @JoinColumn(name = "role_id")
    private Role role;  // The role of the current user (toUser)

    @Enumerated(EnumType.STRING)
    private ReferenceStatus status;

    private LocalDateTime assignedAt;
}
