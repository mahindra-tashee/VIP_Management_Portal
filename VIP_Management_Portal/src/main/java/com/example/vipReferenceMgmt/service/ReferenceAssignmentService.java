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
	public List<VipReferenceListResponse> getReferencesOnUserId(Long userId);
	public List<VipReferenceListResponse> getReferencesOnUserIdAndStatus(Long userId,ReferenceStatus status);
	public DashboardStatsResponse getDashboardStats(Long userId);
	public ResponseEntity<String> assignReference(@ModelAttribute ReferenceAssignRequest reference);
	public List<String> getQueuesByUserId(Long userId);
	public List<VipReferenceListResponse> getReferencesByUserIdAndQueue(Long userId, String queueName);
	public VipReferenceDetailsResponse getReferenceDetailsById(String referenceNumber);
}
