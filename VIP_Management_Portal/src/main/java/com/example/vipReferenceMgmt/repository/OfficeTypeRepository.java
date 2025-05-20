package com.example.vipReferenceMgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.vipReferenceMgmt.entity.OfficeType;
import com.example.vipReferenceMgmt.entity.VipReferenceAssignment;


public interface OfficeTypeRepository extends JpaRepository<OfficeType, Long>{
	
   
	List<OfficeType> findByOrganization_OrganizationCode(String getOfficeTypeByOrganizationCode);

}
