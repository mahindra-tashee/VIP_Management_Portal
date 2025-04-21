package com.example.userMgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userMgmt.entity.VipReferenceList;

@Repository
public interface VipReferenceListRepository extends JpaRepository<VipReferenceList,Long>{

}
