package com.example.vipReferenceMgmt.dto;

import com.example.vipReferenceMgmt.enums.ReferenceStatus;

import lombok.Data;

@Data
public class ReferenceFilterByQueue {
	  private String loginId;
	  private String queue;
	  private ReferenceStatus status;
}
