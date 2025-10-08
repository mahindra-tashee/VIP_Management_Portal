package com.example.vipReferenceMgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vipReferenceMgmt.dto.FinalAssigneeListDto;
import com.example.vipReferenceMgmt.dto.UserListDto;
import com.example.vipReferenceMgmt.entity.OfficeType;
import com.example.vipReferenceMgmt.entity.Organization;
import com.example.vipReferenceMgmt.entity.State;
import com.example.vipReferenceMgmt.entity.UserDesignation;
import com.example.vipReferenceMgmt.entity.VipDesignation;
import com.example.vipReferenceMgmt.service.ReferenceMasterService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reference-master")
public class ReferenceMasterController {

	@Autowired
	ReferenceMasterService referenceMasterService;
	
	@GetMapping("/states")
    public List<State> getStates() {
        return referenceMasterService.getAllState();
    }
	
	@GetMapping("/organization")
    public List<Organization> getOrganizations() {
        return referenceMasterService.getAllOrganization();
    }
	
	@GetMapping("/vip-designations")
    public List<VipDesignation> getVipDesignations() {
        return referenceMasterService.getVipDesignations();
    }
	
	@GetMapping("/get-office-types/{organizationCode}")
    public List<OfficeType> getOfficeTypeByOrganizationId(@PathVariable("organizationCode") String organizationCode) {
        return referenceMasterService.getOfficeTypeByOrganizationCode(organizationCode);
    }
	
	@GetMapping("/get-designations/{organizationCode}")
    public List<UserDesignation> getDesignationByOrganizationId(@PathVariable("organizationCode") String organizationCode) {
        return referenceMasterService.getDesignationByOrganizationCode(organizationCode);
    }
	
	@PostMapping("/get-users")
	public List<UserListDto> getUserList(@RequestBody UserListDto userListDto){
		return referenceMasterService.getUserList(userListDto);
	}
	
	@GetMapping("/get-final-assignee")
	public List<FinalAssigneeListDto> getFinalAssigneeList(){
		return referenceMasterService.getFinalAssigneeList();
	}
	
}
