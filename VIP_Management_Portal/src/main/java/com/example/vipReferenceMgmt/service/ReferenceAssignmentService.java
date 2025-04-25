package com.example.vipReferenceMgmt.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.example.vipReferenceMgmt.dto.DashboardStatsResponse;
import com.example.vipReferenceMgmt.dto.ReferenceAssignRequest;
import com.example.vipReferenceMgmt.dto.VipReferenceListResponse;
import com.example.vipReferenceMgmt.enums.ReferenceStatus;

public interface ReferenceAssignmentService {
	public List<VipReferenceListResponse> getReferencesOnUserId(Long userId);
	public List<VipReferenceListResponse> getReferencesOnUserIdAndStatus(Long userId,ReferenceStatus status);
	public DashboardStatsResponse getDashboardStats(Long userId);
	public ResponseEntity<String> assignReference(ReferenceAssignRequest reference);
	public List<String> getQueuesByUserId(Long userId);
	public List<VipReferenceListResponse> getReferencesByUserIdAndQueue(Long userId, String queueName);
}
