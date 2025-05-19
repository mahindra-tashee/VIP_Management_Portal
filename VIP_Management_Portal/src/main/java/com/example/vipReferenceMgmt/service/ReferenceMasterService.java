package com.example.vipReferenceMgmt.service;

import java.util.List;

import com.example.vipReferenceMgmt.entity.OfficeType;
import com.example.vipReferenceMgmt.entity.Organization;
import com.example.vipReferenceMgmt.entity.State;

public interface ReferenceMasterService {
	public List<State> getAllState();

	public List<Organization> getAllOrganization();

	List<OfficeType> getOfficeTypeByOrganizationId(Long getOfficeTypeByOrganizationId);
}
