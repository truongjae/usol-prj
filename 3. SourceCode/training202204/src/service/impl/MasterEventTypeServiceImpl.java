package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.MasterEventType;
import repository.MasterEventTypeRepository;
import service.MasterEventTypeService;

@Service
public class MasterEventTypeServiceImpl implements MasterEventTypeService{

	@Autowired
	private MasterEventTypeRepository masterEventTypeRepository;
	
	@Override
	public List<MasterEventType> findAll() {
		return masterEventTypeRepository.findAll();
	}

}
