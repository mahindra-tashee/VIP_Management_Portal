package com.example.vipReferenceMgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vipReferenceMgmt.entity.User;
import com.example.vipReferenceMgmt.entity.VipReferenceList;
import com.example.vipReferenceMgmt.enums.ReferenceStatus;

@Repository
public interface VipReferenceListRepository extends JpaRepository<VipReferenceList,Long>{

	Long countByState(String stateCode);

	Optional<VipReferenceList> findByReferenceNo(String referenceNumber);
	
}
