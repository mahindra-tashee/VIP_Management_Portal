package com.example.vipReferenceMgmt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class VipReferenceDocument {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fileName;

	private String filePath;

	private String documentType;

	private String comments;

	@ManyToOne
	@JoinColumn(name = "reference_id")
	private VipReferenceList vipReference;
}
