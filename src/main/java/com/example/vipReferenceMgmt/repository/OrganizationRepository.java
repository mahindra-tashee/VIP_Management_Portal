package com.example.vipReferenceMgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vipReferenceMgmt.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long>{

	public List<Organization> findAllByOrderByOrganizationNameAsc();

}
