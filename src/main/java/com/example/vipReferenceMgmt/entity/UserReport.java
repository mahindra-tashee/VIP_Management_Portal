package com.example.vipReferenceMgmt.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vip_user_login_report", schema = "vip_usermgmt")
public class UserReport {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer serialNo;
    private String organization;
    private String office;
    private String designation;
    private String name;
    private String loginId;
    private String contactNumber;
    private String emailId;
    private LocalDateTime createdDateTime;
    private Boolean hasLoginBefore;
    private LocalDateTime lastLoginTime;
    private Boolean userLocked;
    private Integer failureAttemptCount;
    private String userPassword;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	    name = "user_role_mapping",
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();

	
}
