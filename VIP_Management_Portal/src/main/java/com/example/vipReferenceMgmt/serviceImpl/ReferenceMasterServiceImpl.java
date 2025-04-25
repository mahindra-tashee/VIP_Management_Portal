package com.example.vipReferenceMgmt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vipReferenceMgmt.entity.State;
import com.example.vipReferenceMgmt.repository.StateRepository;
import com.example.vipReferenceMgmt.service.ReferenceMasterService;

@Service
public class ReferenceMasterServiceImpl implements ReferenceMasterService{

	@Autowired
	private StateRepository stateRepository;
	
	@Override
	public List<State> getAllState() {
		return stateRepository.findAllByOrderByNameAsc();
	}

}
