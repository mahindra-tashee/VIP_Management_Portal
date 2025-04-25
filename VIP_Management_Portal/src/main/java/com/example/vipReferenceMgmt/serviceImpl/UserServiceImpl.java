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
import com.example.vipReferenceMgmt.entity.User;
import com.example.vipReferenceMgmt.entity.VipReferenceAssignment;
import com.example.vipReferenceMgmt.entity.VipReferenceList;
import com.example.vipReferenceMgmt.enums.ReferenceStatus;
import com.example.vipReferenceMgmt.repository.RoleRepository;
import com.example.vipReferenceMgmt.repository.UserRepository;
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
	
}
