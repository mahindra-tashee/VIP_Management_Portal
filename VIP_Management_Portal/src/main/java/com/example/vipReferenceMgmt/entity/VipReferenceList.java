package com.example.vipReferenceMgmt.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
    private LocalDateTime dateOfLetter;
    private LocalDateTime dateOfEntry;
    
    private String nameOfDignitary;
    private String emailId;
    private String designation;

    private String state;
    private String constituency;
    
    private String categoryOfSubject;
    private String subCategoryOfSubject;
    
    @OneToMany(mappedBy = "vipReference", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VipReferenceDocument> documents;
   
}
