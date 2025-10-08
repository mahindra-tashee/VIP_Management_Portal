package com.example.vipReferenceMgmt.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vipReferenceMgmt.dto.FinalAssigneeListDto;
import com.example.vipReferenceMgmt.dto.UserListDto;
import com.example.vipReferenceMgmt.dto.VipReferenceListResponse;
import com.example.vipReferenceMgmt.entity.OfficeType;
import com.example.vipReferenceMgmt.entity.Organization;
import com.example.vipReferenceMgmt.entity.Role;
import com.example.vipReferenceMgmt.entity.State;
import com.example.vipReferenceMgmt.entity.UserDesignation;
import com.example.vipReferenceMgmt.entity.UserReport;
import com.example.vipReferenceMgmt.entity.UserRoleMapping;
import com.example.vipReferenceMgmt.entity.VipDesignation;
import com.example.vipReferenceMgmt.entity.VipReferenceList;
import com.example.vipReferenceMgmt.repository.OfficeTypeRepository;
import com.example.vipReferenceMgmt.repository.OrganizationRepository;
import com.example.vipReferenceMgmt.repository.RoleRepository;
import com.example.vipReferenceMgmt.repository.StateRepository;
import com.example.vipReferenceMgmt.repository.UserDesignationRepository;
import com.example.vipReferenceMgmt.repository.UserReportRepository;
import com.example.vipReferenceMgmt.repository.UserRoleMappingRepository;
import com.example.vipReferenceMgmt.repository.VipDesignationRepository;
import com.example.vipReferenceMgmt.service.ReferenceMasterService;

@Service
public class ReferenceMasterServiceImpl implements ReferenceMasterService {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private OfficeTypeRepository officeTypeRepository;

	@Autowired
	private UserDesignationRepository userDesignationRepository;

	@Autowired
	private UserReportRepository userReportRepository;
	
	@Autowired
	private VipDesignationRepository vipDesignationRepository;
	
	@Autowired
	private UserRoleMappingRepository userRoleMappingRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<State> getAllState() {
		return stateRepository.findAllByOrderByNameAsc();
	}

	@Override
	public List<Organization> getAllOrganization() {
		return organizationRepository.findAllByOrderByOrganizationNameAsc();
	}

	@Override
	public List<OfficeType> getOfficeTypeByOrganizationCode(String organizationCode) {
		return officeTypeRepository.findByOrganization_OrganizationCode(organizationCode);
	}

	@Override
	public List<UserDesignation> getDesignationByOrganizationCode(String organizationCode) {
		return userDesignationRepository.findByOrganization_OrganizationCode(organizationCode);
	}

	@Override
	public List<UserListDto> getUserList(UserListDto userListDto) {
		String organization = userListDto.getOrganization();
		String designation = Optional.ofNullable(userListDto.getDesignation()).orElse("");
		String office = Optional.ofNullable(userListDto.getOffice()).orElse("");
		
		List<UserReport> userData = userReportRepository.findCaseInsensitive(organization, designation, office);

		return userData.stream().map(assignment -> {
			UserListDto dto = new UserListDto();
			dto.setName(assignment.getName());
			dto.setId(assignment.getId());
			dto.setOrganization(assignment.getOrganization());
			dto.setDesignation(assignment.getDesignation());
			dto.setOffice(assignment.getOffice());
			dto.setLoginId(assignment.getLoginId());
			dto.setRoles(assignment.getRoles());
			return dto;
		}).collect(Collectors.toList());
	}
	
	@Override
	public List<VipDesignation> getVipDesignations(){
		return vipDesignationRepository.findAllByOrderByDesignationCodeAsc();
	}
	
	@Override
	public List<FinalAssigneeListDto> getFinalAssigneeList(){
		List<UserRoleMapping> finalAssigneeList= userRoleMappingRepository.findByRoleId(4);
		
		return finalAssigneeList.stream().map(assignment -> {
			FinalAssigneeListDto dto = new FinalAssigneeListDto();
			
			dto.setId(assignment.getId());
			dto.setName(assignment.getName());
			dto.setOrganization(assignment.getOrganization());
			dto.setOffice(assignment.getOffice());
			dto.setDesignation(assignment.getDesignation());
			dto.setLoginId(assignment.getLoginId());
			
			Role role = roleRepository.findById(assignment.getRoleId()).orElse(null);
			Set<Role> roles = new HashSet<>();
			if (role != null) {
			    roles.add(role);
			}
			dto.setRoles(roles);
			return dto;
		}).collect(Collectors.toList());
	}

}
