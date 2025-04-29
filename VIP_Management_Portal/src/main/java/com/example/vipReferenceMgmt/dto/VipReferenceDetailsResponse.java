package com.example.vipReferenceMgmt.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class VipReferenceDetailsResponse {

	private Long referenceId;
	private String referenceNo;
	private String subject;
	private LocalDateTime receivedDate;
	private LocalDateTime dateOfLetter;
	private LocalDateTime dateOfEntry;
	private String nameOfDignitary;
	private String emailId;
	private String designation;
	private String state;
	private String constituency;
	private String prirority;
	private String categoryOfSubject;
	private String subCategoryOfSubject;
	private String currentQueue;
	private List<VipReferenceDocumentResponse> documents;
}
