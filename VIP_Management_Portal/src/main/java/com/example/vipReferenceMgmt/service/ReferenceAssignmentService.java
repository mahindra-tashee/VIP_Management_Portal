package com.example.vipReferenceMgmt.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.vipReferenceMgmt.dto.DashboardStatsResponse;
import com.example.vipReferenceMgmt.dto.ReferenceAssignRequest;
import com.example.vipReferenceMgmt.dto.VipReferenceDetailsResponse;
import com.example.vipReferenceMgmt.dto.VipReferenceListResponse;
import com.example.vipReferenceMgmt.enums.ReferenceStatus;

public interface ReferenceAssignmentService {
	List<VipReferenceListResponse> getReferencesOnLoginId(String loginId);
	public List<VipReferenceListResponse> getReferencesOnLoginIdAndStatus(String loginId,ReferenceStatus status);
	public DashboardStatsResponse getDashboardStats(String loginId);
	public ResponseEntity<String> assignReference(@ModelAttribute ReferenceAssignRequest reference);
	public List<String> getQueuesByLoginId(String loginId);
	public List<VipReferenceListResponse> getReferencesByLoginIdAndQueue(String loginId, String queueName);
	public VipReferenceDetailsResponse getReferenceDetailsById(String referenceNumber);
	public ResponseEntity<String> updateReference(ReferenceAssignRequest request);
	
	
}
