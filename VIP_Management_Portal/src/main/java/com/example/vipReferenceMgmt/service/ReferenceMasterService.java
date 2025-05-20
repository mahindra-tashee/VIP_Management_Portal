package com.example.vipReferenceMgmt.service;

import java.util.List;

import com.example.vipReferenceMgmt.dto.UserListDto;
import com.example.vipReferenceMgmt.entity.OfficeType;
import com.example.vipReferenceMgmt.entity.Organization;
import com.example.vipReferenceMgmt.entity.State;
import com.example.vipReferenceMgmt.entity.UserDesignation;

public interface ReferenceMasterService {
	public List<State> getAllState();

	public List<Organization> getAllOrganization();

	List<OfficeType> getOfficeTypeByOrganizationCode(String organizationCode);

	List<UserDesignation> getDesignationByOrganizationCode(String organizationCode);

	List<UserListDto> getUserList(UserListDto userListDto);


}
