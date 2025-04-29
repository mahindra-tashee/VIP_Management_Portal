package com.example.vipReferenceMgmt.dto;

import lombok.Data;

@Data
public class VipReferenceDocumentResponse {
	private String fileName;
	private String filePath; // Optional — only if needed
	private String documentType;
	private String comments;
}
