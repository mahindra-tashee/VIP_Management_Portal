package com.example.vipReferenceMgmt.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vipReferenceMgmt.dto.UserListDto;
import com.example.vipReferenceMgmt.dto.VipReferenceListResponse;
import com.example.vipReferenceMgmt.entity.OfficeType;
import com.example.vipReferenceMgmt.entity.Organization;
import com.example.vipReferenceMgmt.entity.State;
import com.example.vipReferenceMgmt.entity.UserDesignation;
import com.example.vipReferenceMgmt.entity.UserReport;
import com.example.vipReferenceMgmt.entity.VipDesignation;
import com.example.vipReferenceMgmt.entity.VipReferenceList;
import com.example.vipReferenceMgmt.repository.OfficeTypeRepository;
import com.example.vipReferenceMgmt.repository.OrganizationRepository;
import com.example.vipReferenceMgmt.repository.StateRepository;
import com.example.vipReferenceMgmt.repository.UserDesignationRepository;
import com.example.vipReferenceMgmt.repository.UserReportRepository;
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
			return dto;
		}).collect(Collectors.toList());
	}
	
	@Override
	public List<VipDesignation> getVipDesignations(){
		return vipDesignationRepository.findAllByOrderByDesignationCodeAsc();
	}

}
