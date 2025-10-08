package com.example.vipReferenceMgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vipReferenceMgmt.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{
	public List<State> findAllByOrderByNameAsc();
}
