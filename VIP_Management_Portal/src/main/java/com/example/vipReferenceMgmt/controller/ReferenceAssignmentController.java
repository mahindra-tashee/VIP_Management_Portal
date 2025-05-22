package com.example.vipReferenceMgmt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vipReferenceMgmt.dto.DashboardStatsResponse;
import com.example.vipReferenceMgmt.dto.ReferenceAssignRequest;
import com.example.vipReferenceMgmt.dto.ReferenceFilterByQueue;
import com.example.vipReferenceMgmt.dto.VipReferenceDetailsResponse;
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

	@GetMapping("/reference-list/{loginId}")
	public List<VipReferenceListResponse> getVipReferenceList(@PathVariable("loginId") String loginId) {
		return referenceAssignmentService.getReferencesOnLoginId(loginId);
	}

	@GetMapping("/reference-list/{loginId}/{status}")
	public List<VipReferenceListResponse> getReferenceListBasedOnStatus(@PathVariable("loginId") String loginId,
			@PathVariable("status") ReferenceStatus status) {
		return referenceAssignmentService.getReferencesOnLoginIdAndStatus(loginId, status);
	}

	@GetMapping("/dashboard-stats/{loginId}")
	public DashboardStatsResponse getDashboardData(@PathVariable("loginId") String loginId) {
		return referenceAssignmentService.getDashboardStats(loginId);

	}

	@PostMapping(value = "/assign-reference", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> assignReference(@ModelAttribute ReferenceAssignRequest request) {
	    return referenceAssignmentService.assignReference(request);
	}


	@GetMapping("/user/{loginId}/queues")
	public ResponseEntity<Map<String, Object>> getUserQueues(@PathVariable("loginId") String loginId) {
		List<String> queues = referenceAssignmentService.getQueuesByLoginId(loginId);
		Map<String, Object> response = new HashMap<>();
		response.put("userId", loginId);
		response.put("queues", queues);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/user/queue/references")
	public ResponseEntity<List<VipReferenceListResponse>> getFilteredReferences(
			@RequestBody ReferenceFilterByQueue request) {
		List<VipReferenceListResponse> responseList = referenceAssignmentService
				.getReferencesByLoginIdAndQueue(request.getLoginId(), request.getQueue());
		return ResponseEntity.ok(responseList);
	}
	
	@GetMapping("/reference-details/{referenceNo}")
	public VipReferenceDetailsResponse getRefernceDetailsOnId(@PathVariable("referenceNo") String referenceNo)
	{
		return referenceAssignmentService.getReferenceDetailsById(referenceNo);
	}
	
	@PatchMapping("/updateReference")
	public ResponseEntity<String> updateReference(@RequestBody ReferenceAssignRequest request){
		return referenceAssignmentService.updateReference(request);
	}
}
