package com.example.vipReferenceMgmt.serviceImpl;

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

import com.example.vipReferenceMgmt.dto.ChartData;
import com.example.vipReferenceMgmt.dto.DashboardStatsResponse;
import com.example.vipReferenceMgmt.dto.ReferenceAssignRequest;
import com.example.vipReferenceMgmt.dto.ResponseDto;
import com.example.vipReferenceMgmt.dto.UserResponse;
import com.example.vipReferenceMgmt.dto.VipReferenceListResponse;
import com.example.vipReferenceMgmt.entity.Role;
import com.example.vipReferenceMgmt.entity.UserReport;
import com.example.vipReferenceMgmt.entity.VipReferenceAssignment;
import com.example.vipReferenceMgmt.entity.VipReferenceList;
import com.example.vipReferenceMgmt.enums.ReferenceStatus;
import com.example.vipReferenceMgmt.repository.RoleRepository;
import com.example.vipReferenceMgmt.repository.UserReportRepository;
import com.example.vipReferenceMgmt.repository.VipReferenceAssignmentRepository;
import com.example.vipReferenceMgmt.repository.VipReferenceListRepository;
import com.example.vipReferenceMgmt.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private VipReferenceAssignmentRepository assignmentRepo;

	@Autowired
	private VipReferenceListRepository vipReferenceListRepository;


	@Autowired
	private RoleRepository roleRepository; 
	
	@Autowired
	private UserReportRepository userReportRepository;
	
	
	// VIP Reference User Management
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public ResponseEntity<Object> registerVipUser(UserReport userReport) {
	    try {
	        Set<Role> fetchedRoles = new HashSet<>();

	        if (userReport.getRoles() != null && !userReport.getRoles().isEmpty()) {
	            for (Role role : userReport.getRoles()) {
	                Role dbRole = roleRepository.findById(role.getRoleId())
	                        .orElseThrow(() -> new RuntimeException("Role not found with ID: " + role.getRoleId()));
	                fetchedRoles.add(dbRole);
	            }
	        } else {
	            throw new IllegalArgumentException("At least one role must be assigned.");
	        }

	        userReport.setRoles(fetchedRoles);

	        if (userReport.getCreatedDateTime() == null) {
	            userReport.setCreatedDateTime(LocalDateTime.now());
	        }

	        UserReport savedUser = userReportRepository.save(userReport);

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
	        UserReport loginUser = userReportRepository.findByLoginId(username).orElseThrow(() -> new RuntimeException("User not found with loginId: " + username));
	        System.out.println(loginUser);
	        if (loginUser == null) {
	            response.setMessage("User not found with loginId: " + username);
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }

	        if (loginUser.getUserPassword() != null && loginUser.getUserPassword().equals(password)) {

	            UserResponse userResponse = new UserResponse();
	            userResponse.setUserId(loginUser.getId());
	            userResponse.setUserName(loginUser.getLoginId());
	            userResponse.setRoles(loginUser.getRoles());
	            userResponse.setCreatedAt(loginUser.getCreatedDateTime());
	            userResponse.setName(loginUser.getName());

	            return new ResponseEntity<>(userResponse, HttpStatus.OK);
	        } else {
	            response.setMessage("Password mismatch");
	            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
	        }

	    } catch (Exception ex) {
	        response.setMessage("Login failed: " + ex.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	}

	@Override
	public List<UserReport> getAllUser() {
		// TODO Auto-generated method stub
		return userReportRepository.findAll();
	}

	@Override
	public ResponseEntity<Object> getUserByLoginId(String loginId) {
	    ResponseDto response = new ResponseDto();

	    try {
	        UserReport user = userReportRepository.findByLoginId(loginId).orElse(null);

	        if (user == null) {
	            response.setMessage("User not found!");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<>(user, HttpStatus.OK);

	    } catch (Exception ex) {
	        response.setMessage("Failed to retrieve user: " + ex.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	}

	// VIP Reference Management
	
}
