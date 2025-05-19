package com.example.vipReferenceMgmt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vipReferenceMgmt.entity.OfficeType;
import com.example.vipReferenceMgmt.entity.Organization;
import com.example.vipReferenceMgmt.entity.State;
import com.example.vipReferenceMgmt.repository.OfficeTypeRepository;
import com.example.vipReferenceMgmt.repository.OrganizationRepository;
import com.example.vipReferenceMgmt.repository.StateRepository;
import com.example.vipReferenceMgmt.service.ReferenceMasterService;

@Service
public class ReferenceMasterServiceImpl implements ReferenceMasterService{

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired 
	private OrganizationRepository organizationRepository;
	
	@Autowired 
	private OfficeTypeRepository officeTypeRepository;
	
	@Override
	public List<State> getAllState() {
		return stateRepository.findAllByOrderByNameAsc();
	}
	
	
	@Override
	public List<Organization> getAllOrganization() {
		return organizationRepository.findAllByOrderByOrganizationNameAsc();
	}
	
	
	@Override
	public List<OfficeType> getOfficeTypeByOrganizationId(Long getOfficeTypeByOrganizationId) {
		return officeTypeRepository.findByOrganization_OrganizationId(getOfficeTypeByOrganizationId);
	}

}
