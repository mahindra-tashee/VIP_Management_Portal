package com.example.vipReferenceMgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.vipReferenceMgmt.entity.UserDesignation;

public interface UserDesignationRepository extends JpaRepository<UserDesignation, Long>{

	List<UserDesignation> findByOrganization_OrganizationCode(String getOfficeTypeByOrganizationId);
}
