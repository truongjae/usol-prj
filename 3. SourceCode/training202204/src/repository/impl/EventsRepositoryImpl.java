package repository.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import entity.Events;
import orm.AbstractQuery;
import repository.EventsRepository;

@Repository
public class EventsRepositoryImpl extends AbstractQuery<Events, Long> implements EventsRepository{

	public EventsRepositoryImpl() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

}
