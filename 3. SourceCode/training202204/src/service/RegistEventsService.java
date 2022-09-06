package service;

import java.sql.SQLException;
import java.util.List;

import entity.RegistEvents;

public interface RegistEventsService {
	List<RegistEvents> getRegistEventsByEventId(Long eventsId) throws SQLException;
	void updateRegisterEvents(Long eventsId)
			throws IllegalArgumentException, IllegalAccessException, SQLException, InstantiationException;
	List<RegistEvents> getRegistEventsDetailList(Long eventsId) throws SQLException;
	void save(String listMember, Long eventsId, Long voteOptionId) throws SQLException, IllegalArgumentException, IllegalAccessException;
}
