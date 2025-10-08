package com.example.vipReferenceMgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vipReferenceMgmt.entity.ReferenceAssignmentHistory;


@Repository
public interface ReferenceAssignmentHistoryRepository extends JpaRepository<ReferenceAssignmentHistory, Long>{

}
