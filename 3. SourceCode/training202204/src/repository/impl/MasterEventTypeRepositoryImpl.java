package repository.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import entity.MasterEventType;
import orm.AbstractQuery;
import repository.MasterEventTypeRepository;

@Repository
public class MasterEventTypeRepositoryImpl extends AbstractQuery<MasterEventType, Long> implements MasterEventTypeRepository{

	public MasterEventTypeRepositoryImpl() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

}
