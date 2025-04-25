package com.example.vipReferenceMgmt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vipReferenceMgmt.dto.DashboardStatsResponse;
import com.example.vipReferenceMgmt.dto.ReferenceAssignRequest;
import com.example.vipReferenceMgmt.dto.ReferenceFilterByQueue;
import com.example.vipReferenceMgmt.dto.VipReferenceListResponse;
import com.example.vipReferenceMgmt.enums.ReferenceStatus;
import com.example.vipReferenceMgmt.service.ReferenceAssignmentService;
import com.example.vipReferenceMgmt.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reference")
public class ReferenceAssignmentController {
	@Autowired
	ReferenceAssignmentService referenceAssignmentService;

	@GetMapping("/reference-list/{userId}")
	public List<VipReferenceListResponse> getVipReferenceList(@PathVariable("userId") Long userId) {
		return referenceAssignmentService.getReferencesOnUserId(userId);
	}

	@GetMapping("/reference-list/{userId}/{status}")
	public List<VipReferenceListResponse> getReferenceListBasedOnStatus(@PathVariable("userId") Long userId,
			@PathVariable("status") ReferenceStatus status) {
		return referenceAssignmentService.getReferencesOnUserIdAndStatus(userId, status);
	}

	@GetMapping("/dashboard-stats/{userId}")
	public DashboardStatsResponse getDashboardData(@PathVariable("userId") Long userId) {
		return referenceAssignmentService.getDashboardStats(userId);

	}

	@PostMapping("/assign-reference")
	public ResponseEntity<String> assignReference(@RequestBody ReferenceAssignRequest request) {
		return referenceAssignmentService.assignReference(request);
//        return ResponseEntity.ok("Reference assigned successfully.");
	}

	@GetMapping("/user/{userId}/queues")
	public ResponseEntity<Map<String, Object>> getUserQueues(@PathVariable("userId") Long userId) {
		List<String> queues = referenceAssignmentService.getQueuesByUserId(userId);
		Map<String, Object> response = new HashMap<>();
		response.put("userId", userId);
		response.put("queues", queues);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/user/queue/references")
	public ResponseEntity<List<VipReferenceListResponse>> getFilteredReferences(
			@RequestBody ReferenceFilterByQueue request) {
		List<VipReferenceListResponse> responseList = referenceAssignmentService
				.getReferencesByUserIdAndQueue(request.getUserId(), request.getQueue());
		return ResponseEntity.ok(responseList);
	}
}
