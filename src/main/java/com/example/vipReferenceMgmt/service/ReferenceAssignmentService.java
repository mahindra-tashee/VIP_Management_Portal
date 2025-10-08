package com.example.vipReferenceMgmt.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.vipReferenceMgmt.dto.DashboardStatsResponse;
import com.example.vipReferenceMgmt.dto.FinalAssigneeListDto;
import com.example.vipReferenceMgmt.dto.ForwardReference;
import com.example.vipReferenceMgmt.dto.ReferenceAssignRequest;
import com.example.vipReferenceMgmt.dto.ReferenceFilterByQueue;
import com.example.vipReferenceMgmt.dto.VipReferenceDetailsResponse;
import com.example.vipReferenceMgmt.dto.VipReferenceListResponse;
import com.example.vipReferenceMgmt.enums.ReferenceStatus;

public interface ReferenceAssignmentService {
	List<VipReferenceListResponse> getReferencesOnLoginId(String loginId);
	public List<VipReferenceListResponse> getReferencesOnLoginIdAndStatus(String loginId,ReferenceStatus status);
	public DashboardStatsResponse getDashboardStats(String loginId);
	public ResponseEntity<String> assignReference(@ModelAttribute ReferenceAssignRequest reference);
	public List<String> getQueuesByLoginId(String loginId);
	public List<VipReferenceListResponse> getReferencesByLoginIdAndQueueAndStatus(ReferenceFilterByQueue request);
	public VipReferenceDetailsResponse getReferenceDetailsById(String referenceNumber);
	public ResponseEntity<String> updateReference(ReferenceAssignRequest request);
	public ResponseEntity<String> forwardReference(ForwardReference request);

}
