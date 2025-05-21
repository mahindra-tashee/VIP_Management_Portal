package com.example.vipReferenceMgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vipReferenceMgmt.entity.VipDesignation;

public interface VipDesignationRepository extends JpaRepository<VipDesignation, Long>{

	List<VipDesignation> findAllByOrderByDesignationCodeAsc();
	
}
