package com.example.userMgmt.serviceImpl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.userMgmt.dto.ChartData;
import com.example.userMgmt.dto.DashboardStatsResponse;
import com.example.userMgmt.dto.ReferenceAssignRequest;
import com.example.userMgmt.dto.ResponseDto;
import com.example.userMgmt.dto.UserResponse;
import com.example.userMgmt.dto.VipReferenceListResponse;
import com.example.userMgmt.entity.Role;
import com.example.userMgmt.entity.User;
import com.example.userMgmt.entity.VipReferenceAssignment;
import com.example.userMgmt.entity.VipReferenceList;
import com.example.userMgmt.enums.ReferenceStatus;
import com.example.userMgmt.repository.RoleRepository;
import com.example.userMgmt.repository.UserRepository;
import com.example.userMgmt.repository.VipReferenceAssignmentRepository;
import com.example.userMgmt.repository.VipReferenceListRepository;
import com.example.userMgmt.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private VipReferenceAssignmentRepository assignmentRepo;

	@Autowired
	private VipReferenceListRepository vipReferenceListRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository; // Make sure this is injected

	// VIP Reference User Management
	@Override
	@Transactional(rollbackOn = Exception.class)
	public ResponseEntity<Object> registerVipUser(User user) {
		try {
			Set<Role> fetchedRoles = new HashSet<>();

			if (user.getRoles() != null && !user.getRoles().isEmpty()) {
				for (Role role : user.getRoles()) {
					Role dbRole = roleRepository.findById(role.getRoleId())
							.orElseThrow(() -> new RuntimeException("Role not found with ID: " + role.getRoleId()));
					fetchedRoles.add(dbRole);
				}
			} else {
				throw new IllegalArgumentException("At least one role must be assigned.");
			}

			user.setRoles(fetchedRoles);

			if (user.getCreatedAt() == null) {
				user.setCreatedAt(LocalDateTime.now());
			}

			User savedUser = userRepository.save(user);

			return new ResponseEntity<>(savedUser, HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
			ResponseDto response = new ResponseDto();
			response.setMessage("Failed to save user: " + ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> loginUser(String username, String password) {
		ResponseDto response = new ResponseDto();
		try {
			User loginUser = userRepository.findByUserName(username);

			if (loginUser.getUserPassword().equals(password)) {

				UserResponse userResponse = new UserResponse();

				userResponse.setUserId(loginUser.getUserId());
				userResponse.setUserName(loginUser.getUserName());
				userResponse.setRoles(loginUser.getRoles());
				userResponse.setCreatedAt(loginUser.getCreatedAt());
				userResponse.setName(loginUser.getName());

				return new ResponseEntity<>(userResponse, HttpStatus.OK);
			} else {
				response.setMessage("Password mismatch");
				return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
			}

		} catch (Exception ex) {
			response.setMessage(ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public ResponseEntity<Object> getUserById(Long userId) {
		// TODO Auto-generated method stub
		ResponseDto response = new ResponseDto();
		try {
			User user2 = userRepository.findById(userId).orElse(null);
			if (user2 == null) {
				response.setMessage("User Not found!");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(user2, HttpStatus.OK);
		} catch (Exception ex) {
			response.setMessage(ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// VIP Reference Management
	@Override
	public List<VipReferenceListResponse> getReferencesOnUserId(Long userId) {
		User isUser=userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		List<VipReferenceAssignment> assignments = assignmentRepo.findByToUser_UserId(userId);
		List<VipReferenceListResponse> responseList = assignments.stream().map((VipReferenceAssignment assignment) -> {
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
		return responseList;
	}

	@Override
	public List<VipReferenceListResponse> getReferencesOnUserIdAndStatus(Long userId,ReferenceStatus status) {
		List<VipReferenceAssignment> assignments = assignmentRepo.findByToUser_UserIdAndStatus(userId,status);
		List<VipReferenceListResponse> responseList = assignments.stream().map((VipReferenceAssignment assignment) -> {
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
		return responseList;
	}
	
	@Override
	public DashboardStatsResponse getDashboardStats(Long userId) {
		User isUser=userRepository.findById(userId)
		.orElseThrow(() -> new RuntimeException("User not found"));
		
		int inboxCount = assignmentRepo.countByToUser_UserIdAndStatus(userId, ReferenceStatus.INBOX);
		int sentCount = assignmentRepo.countByToUser_UserIdAndStatus(userId, ReferenceStatus.SENT);

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
	public void assignReference(ReferenceAssignRequest request) {
		User fromUser = userRepository.findById(request.getFromUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));
		User toUser = userRepository.findById(request.getToUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		VipReferenceList vipReference = vipReferenceListRepository.findById(request.getVipReferenceId())
				.orElseThrow(() -> new RuntimeException("VIP Reference not found"));

		Role fromRole = roleRepository.findByRoleId(request.getFromRoleId())
				.orElseThrow(() -> new RuntimeException("Role not found"));
		Role toRole = roleRepository.findByRoleId(request.getToRoleId())
				.orElseThrow(() -> new RuntimeException("Role not found"));

		// ✅ Create the new assignment for the toUser
		VipReferenceAssignment newAssignment = new VipReferenceAssignment();
		newAssignment.setFromUser(fromUser);
		newAssignment.setToUser(toUser);
		newAssignment.setVipReference(vipReference);
		newAssignment.setRole(toRole);
		newAssignment.setStatus(ReferenceStatus.INBOX);
		newAssignment.setAssignedAt(LocalDateTime.now());
		assignmentRepo.save(newAssignment);

		// ✅ Update the reference's currentQueue field to reflect the new role
		vipReference.setCurrentQueue(toRole.getRoleName()); // assuming role name like "VIP_Assignee"
		vipReferenceListRepository.save(vipReference);

		// ✅ Update the sender's reference status to SENT
		Optional<VipReferenceAssignment> senderAssignment = assignmentRepo
				.findByToUserAndVipReferenceAndStatus(fromUser, vipReference, ReferenceStatus.INBOX);

		if (senderAssignment.isPresent()) {
			VipReferenceAssignment assignment = senderAssignment.get();
			assignment.setStatus(ReferenceStatus.SENT);
			assignment.setAssignedAt(LocalDateTime.now());
			assignmentRepo.save(assignment);
		} else {
			VipReferenceAssignment sentRecord = new VipReferenceAssignment();
			sentRecord.setFromUser(fromUser);
			sentRecord.setToUser(fromUser); // fromUser is both sender & previous owner
			sentRecord.setVipReference(vipReference);
			sentRecord.setRole(fromRole);
			sentRecord.setStatus(ReferenceStatus.SENT);
			sentRecord.setAssignedAt(LocalDateTime.now());
			assignmentRepo.save(sentRecord);
		}
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
	public List<VipReferenceListResponse> getReferencesByUserIdAndQueue(Long userId, String queueName) {
	    List<VipReferenceAssignment> assignments = assignmentRepo
	        .findByToUser_UserIdAndRole_RoleName(userId, queueName);

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
}
