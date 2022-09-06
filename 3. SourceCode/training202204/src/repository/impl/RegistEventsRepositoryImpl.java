package repository.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import entity.RegistEvents;
import orm.AbstractQuery;
import repository.RegistEventsRepository;
@Repository
public class RegistEventsRepositoryImpl extends AbstractQuery<RegistEvents, Long> implements RegistEventsRepository{

	public RegistEventsRepositoryImpl() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

}
