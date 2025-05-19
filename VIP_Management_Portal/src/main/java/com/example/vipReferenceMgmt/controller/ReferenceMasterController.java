package com.example.vipReferenceMgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vipReferenceMgmt.entity.OfficeType;
import com.example.vipReferenceMgmt.entity.Organization;
import com.example.vipReferenceMgmt.entity.State;
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
	
	@GetMapping("/getOfficeType/{organizationId}")
    public List<OfficeType> getOfficeTypeByOrganizationId(@PathVariable("organizationId") Long organizationId) {
        return referenceMasterService.getOfficeTypeByOrganizationId(organizationId);
    }
}
