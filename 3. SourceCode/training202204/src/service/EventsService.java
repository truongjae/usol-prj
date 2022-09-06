package service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import dto.request.events.EventsSearchRequest;
import dto.response.events.EventsResponse;
import entity.Events;
import orm.querybuilder.QueryFactory;

public interface EventsService {

	List<Events> findAll();

	List<EventsResponse> findAllByUsersId(Long usersId)
			throws SQLException, InstantiationException, IllegalAccessException;

	Long save(Events events) throws IllegalArgumentException, IllegalAccessException;

	void updateEvents(Long eventsId) throws InstantiationException, IllegalAccessException, SQLException;

	List<Events> getEventByEventId(Long eventsId) throws SQLException;

	void updateStatusEvents(Long eventsId) throws InstantiationException, IllegalAccessException, SQLException;
	
	List<EventsResponse> searchByCondition(EventsSearchRequest eventsSearchRequest, Long usersId) throws SQLException, ParseException;
}
