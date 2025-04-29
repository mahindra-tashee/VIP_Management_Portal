package com.example.vipReferenceMgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vipReferenceMgmt.entity.VipReferenceAssignment;
import com.example.vipReferenceMgmt.entity.VipReferenceDocument;

public interface VipReferenceDocumentRepository extends JpaRepository<VipReferenceDocument, Long>{
	  List<VipReferenceDocument> findByVipReference_ReferenceId(Long userId);
}
