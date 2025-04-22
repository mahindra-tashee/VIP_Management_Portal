package com.example.userMgmt.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class VipReferenceListResponse {
	private Long referenceId;
    private String referenceNo;
    private String subject;
    private LocalDateTime receivedDate;
    private String prirority;
    private String currentQueue;
    private String status;
    
}
