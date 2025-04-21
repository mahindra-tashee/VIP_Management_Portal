package com.example.userMgmt.serviceImpl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.userMgmt.dto.ResponseDto;
import com.example.userMgmt.dto.UserResponse;
import com.example.userMgmt.entity.Role;
import com.example.userMgmt.entity.User;
import com.example.userMgmt.entity.VipReferenceList;
import com.example.userMgmt.repository.RoleRepository;
import com.example.userMgmt.repository.UserRepository;
import com.example.userMgmt.repository.VipReferenceListRepository;
import com.example.userMgmt.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private VipReferenceListRepository vipReferenceListRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository; // Make sure this is injected

	@Override
	@Transactional(rollbackOn = Exception.class)
	public ResponseEntity<Object> registerVipUser(User user) {
	    try {
	        Set<Role> fetchedRoles = new HashSet<>();

	        // ✅ Replace transient roles with managed ones from DB
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

	        // Set createdAt if missing
	        if (user.getCreatedAt() == null) {
	            user.setCreatedAt(LocalDateTime.now());
	        }

	        // ✅ Save user — now with managed roles
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
				
				UserResponse userResponse=new UserResponse();
				
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

	@Override
	public List<VipReferenceList> getVipReferenceList(Long userId){
		return vipReferenceListRepository.findAll();
	}
}
