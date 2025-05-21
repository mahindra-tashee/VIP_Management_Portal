package com.example.vipReferenceMgmt.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.example.vipReferenceMgmt.dto.ChartData;
import com.example.vipReferenceMgmt.dto.DashboardStatsResponse;
import com.example.vipReferenceMgmt.dto.ReferenceAssignRequest;
import com.example.vipReferenceMgmt.dto.VipReferenceDetailsResponse;
import com.example.vipReferenceMgmt.dto.VipReferenceDocumentResponse;
import com.example.vipReferenceMgmt.dto.VipReferenceListResponse;
import com.example.vipReferenceMgmt.entity.Role;
import com.example.vipReferenceMgmt.entity.User;
import com.example.vipReferenceMgmt.entity.UserReport;
import com.example.vipReferenceMgmt.entity.VipReferenceAssignment;
import com.example.vipReferenceMgmt.entity.VipReferenceDocument;
import com.example.vipReferenceMgmt.entity.VipReferenceList;
import com.example.vipReferenceMgmt.enums.ReferenceStatus;
import com.example.vipReferenceMgmt.repository.RoleRepository;
import com.example.vipReferenceMgmt.repository.UserReportRepository;
import com.example.vipReferenceMgmt.repository.UserRepository;
import com.example.vipReferenceMgmt.repository.VipReferenceAssignmentRepository;
import com.example.vipReferenceMgmt.repository.VipReferenceDocumentRepository;
import com.example.vipReferenceMgmt.repository.VipReferenceListRepository;
import com.example.vipReferenceMgmt.service.ReferenceAssignmentService;

import jakarta.transaction.Transactional;

@Service
public class ReferenceAssignmentServiceImpl implements ReferenceAssignmentService{
	@Autowired
	private VipReferenceAssignmentRepository assignmentRepo;

	@Autowired
	private VipReferenceListRepository vipReferenceListRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private VipReferenceDocumentRepository vipReferenceDocumentRepository;
	
	@Autowired
	private UserReportRepository userReportRepository;
	
	@Override
	public List<VipReferenceListResponse> getReferencesOnLoginId(String loginId) {
		// here need to check login user are present or not
		
		List<VipReferenceAssignment> assignments = assignmentRepo.findByToLogin_LoginId(loginId);

	    return assignments.stream().map(assignment -> {
	        VipReferenceList ref = assignment.getVipReference();
	        VipReferenceListResponse response = new VipReferenceListResponse();
	        response.setReferenceId(ref.getReferenceId());
	        response.setReferenceNo(ref.getReferenceNo());
	        response.setSubject(ref.getSubject());
	        response.setPrirority(ref.getPrirority());
	        response.setReceivedDate(ref.getReceivedDate());
	        response.setStatus(assignment.getStatus().name());
	        response.setCurrentQueue(ref.getCurrentQueue());
	        return response;
	    }).collect(Collectors.toList());
	}

	@Override
	public List<VipReferenceListResponse> getReferencesOnLoginIdAndStatus(String loginId,ReferenceStatus status) {
		// here need to check login user are present or not
		
		List<VipReferenceAssignment> assignments = assignmentRepo.findByToLogin_LoginIdAndStatus(loginId, status);

	    return assignments.stream().map(assignment -> {
	        VipReferenceList ref = assignment.getVipReference();
	        VipReferenceListResponse response = new VipReferenceListResponse();
	        response.setReferenceId(ref.getReferenceId());
	        response.setReferenceNo(ref.getReferenceNo());
	        response.setSubject(ref.getSubject());
	        response.setPrirority(ref.getPrirority());
	        response.setReceivedDate(ref.getReceivedDate());
	        response.setStatus(assignment.getStatus().name());
	        response.setCurrentQueue(ref.getCurrentQueue());
	        return response;
	    }).collect(Collectors.toList());
	}
	
	@Override
	public DashboardStatsResponse getDashboardStats(String loginId) {
		 int inboxCount = assignmentRepo.countByToLogin_LoginIdAndStatus(loginId, ReferenceStatus.INBOX);
		    int sentCount = assignmentRepo.countByToLogin_LoginIdAndStatus(loginId, ReferenceStatus.SENT);

		    ChartData chartData = new ChartData();
		    chartData.setLabels(List.of("Inbox", "Sent"));
		    chartData.setData(List.of(inboxCount, sentCount));

		    DashboardStatsResponse response = new DashboardStatsResponse();
		    response.setInboxCount(inboxCount);
		    response.setSentCount(sentCount);
		    response.setChartData(chartData);

		    return response;
	}

	@Override
	@Transactional
	public ResponseEntity<String> assignReference(@ModelAttribute ReferenceAssignRequest request) { 
		
		// Create VIP Reference from form
		VipReferenceList vipReference = new VipReferenceList();
		vipReference.setDateOfLetter(request.getDateOfLetter());
		vipReference.setReceivedDate(request.getDateOfReceiving());
		vipReference.setDateOfEntry(request.getDateOfEntry());
		vipReference.setNameOfDignitary(request.getNameOfDignitary());
		vipReference.setEmailId(request.getEmailId());
		vipReference.setDesignation(request.getDesignation());
		vipReference.setState(request.getState());
		vipReference.setConstituency(request.getConstituency());
		vipReference.setPrirority(request.getPrirority());
		vipReference.setCategoryOfSubject(request.getCategoryOfSubject());
		vipReference.setSubCategoryOfSubject(request.getSubCategoryOfSubject());
		vipReference.setSubject(request.getSubject());

		// Auto-generate reference number
		String stateCode = request.getState(); // Ex: "KA"
		Long count = vipReferenceListRepository.countByState(stateCode); // Custom query
		String referenceNo = stateCode + String.format("%05d", count + 1);
		vipReference.setReferenceNo(referenceNo);
		
		// Create assignment record for Assigner
		UserReport fromLogin = userReportRepository.findByLoginId(request.getFromLoginId()).orElseThrow(() -> new RuntimeException("From User not found with loginId"));
	    UserReport toLogin = userReportRepository.findByLoginId(request.getToLoginId()).orElseThrow(() -> new RuntimeException("To User not found with loginId"));
		Role fromRole = roleRepository.findByRoleId(request.getFromRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));
		Role toRole = roleRepository.findByRoleId(request.getToRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));

		// Set current queue as "VIP_Assigner" because that's the next after initiator
		vipReference.setCurrentQueue(toRole.getRoleName());

		// Save VIP reference
		vipReference = vipReferenceListRepository.save(vipReference);



		VipReferenceAssignment assignment = new VipReferenceAssignment();
		assignment.setFromLogin(fromLogin);
		assignment.setToLogin(toLogin);
		assignment.setVipReference(vipReference);
		assignment.setRole(toRole);
		assignment.setStatus(ReferenceStatus.INBOX);
		assignment.setAssignedAt(LocalDateTime.now());
		assignmentRepo.save(assignment);

		// Also record SENT entry for initiator (fromUser)
		VipReferenceAssignment sentRecord = new VipReferenceAssignment();
		sentRecord.setFromLogin(fromLogin);
		sentRecord.setToLogin(fromLogin); // since this is the sender
		sentRecord.setVipReference(vipReference);
		sentRecord.setRole(fromRole);
		sentRecord.setStatus(ReferenceStatus.SENT);
		sentRecord.setAssignedAt(LocalDateTime.now());
		assignmentRepo.save(sentRecord);

		String uploadDir = "C:/Users/NIC/Desktop/VIP_Management/VIP_Reference_Mgmt_Angular/VIP_Reference_Management/src/assets/pdf";

		try {
		    Files.createDirectories(Paths.get(uploadDir)); // Create folder if not exists

		    for (int i = 0; i < request.getFiles().size(); i++) {
		        MultipartFile file = request.getFiles().get(i);
		        
		        if (!file.getContentType().equals("application/pdf")) {
		            throw new RuntimeException("Only PDF files are allowed.");
		        }

		        if (file.getSize() > 10 * 1024 * 1024) { // 10 MB = 10 * 1024 * 1024 bytes
		            throw new RuntimeException("File " + file.getOriginalFilename() + " exceeds the maximum allowed size of 10 MB.");
		        }
		        String documentType = request.getDocumentTypes().get(i);
		        String comment = request.getComments().get(i);

		        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		        String filePath = uploadDir + fileName;
		        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

		        VipReferenceDocument document = new VipReferenceDocument();
		        document.setFileName(file.getOriginalFilename());
		        document.setFilePath(filePath);
		        document.setDocumentType(documentType);
		        document.setComments(comment);
		        document.setVipReference(vipReference);
		        vipReferenceDocumentRepository.save(document);
		    }
		} catch (IOException e) {
		    throw new RuntimeException("Failed to save file: " + e.getMessage(), e);
		}

		return ResponseEntity.ok("Reference assigned successfully.");
	}
	
	
	// VIP QUEUE List 
	@Override
	public List<String> getQueuesByUserId(Long userId) {
	    User user = userRepository.findById(userId)
	        .orElseThrow(() -> new RuntimeException("User not found"));

	    return user.getRoles().stream()
	               .map(Role::getRoleName).collect(Collectors.toList());
	}
	

	@Override
	public List<VipReferenceListResponse> getReferencesByLoginIdAndQueue(String loginId, String queueName) {
	    List<VipReferenceAssignment> assignments = assignmentRepo
	        .findByToLogin_LoginIdAndRole_RoleName(loginId, queueName);

	    return assignments.stream()
	        .map(assignment -> {
	            VipReferenceList ref = assignment.getVipReference();
	            VipReferenceListResponse response = new VipReferenceListResponse();
	            response.setReferenceId(ref.getReferenceId());
	            response.setReferenceNo(ref.getReferenceNo());
	            response.setSubject(ref.getSubject());
	            response.setPrirority(ref.getPrirority());
	            response.setReceivedDate(ref.getReceivedDate());
	            response.setStatus(assignment.getStatus().name());
	            response.setCurrentQueue(ref.getCurrentQueue());
	            return response;
	        })
	        .collect(Collectors.toList());
	}
	
	
	@Override
	public VipReferenceDetailsResponse getReferenceDetailsById(String referenceNumber) {
	    VipReferenceList ref = vipReferenceListRepository.findByReferenceNo(referenceNumber)
	        .orElseThrow(() -> new RuntimeException("Reference not found"));

	    VipReferenceDetailsResponse response = new VipReferenceDetailsResponse();
	    response.setReferenceId(ref.getReferenceId());
	    response.setReferenceNo(ref.getReferenceNo());
	    response.setSubject(ref.getSubject());
	    response.setReceivedDate(ref.getReceivedDate());
	    response.setDateOfLetter(ref.getDateOfLetter());
	    response.setDateOfEntry(ref.getDateOfEntry());
	    response.setNameOfDignitary(ref.getNameOfDignitary());
	    response.setEmailId(ref.getEmailId());
	    response.setDesignation(ref.getDesignation());
	    response.setState(ref.getState());
	    response.setConstituency(ref.getConstituency());
	    response.setPrirority(ref.getPrirority());
	    response.setCategoryOfSubject(ref.getCategoryOfSubject());
	    response.setSubCategoryOfSubject(ref.getSubCategoryOfSubject());
	    response.setCurrentQueue(ref.getCurrentQueue());

	 // Map documents if available
	    List<VipReferenceDocumentResponse> documentResponses = ref.getDocuments().stream()
	            .map(doc -> {
	                VipReferenceDocumentResponse docRes = new VipReferenceDocumentResponse();
	                
	                docRes.setFileName(doc.getFileName());
	                docRes.setFilePath(doc.getFilePath());
	                docRes.setDocumentType(doc.getDocumentType());
	                docRes.setComments(doc.getComments());
	                return docRes;
	            }).collect(Collectors.toList());
	    response.setDocuments(documentResponses);
	    return response;
	}

}
