package service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.request.events.EventsSearchRequest;
import dto.response.events.EventsResponse;
import entity.Department;
import entity.Events;
import entity.MasterEventType;
import entity.RegistEvents;
import entity.Users;
import orm.page.Page;
import orm.page.PageAble;
import orm.page.PageRequest;
import orm.querybuilder.QueryFactory;
import repository.DepartmentRepository;
import repository.EventsRepository;
import repository.RegistEventsRepository;
import repository.UsersRepository;
import service.DepartmentService;
import service.EventsService;
import service.UsersService;

@Service
public class EventsServiceImpl implements EventsService{

	@Autowired
	private EventsRepository eventsRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private RegistEventsRepository registEventsRepository;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private UsersService usersService;
	
	@Override
	public List<Events> findAll() {
		return eventsRepository.findAll();
	}

	@Override
	public List<EventsResponse> findAllByUsersId(Long usersId) throws SQLException, InstantiationException, IllegalAccessException {
		List<Events> events = eventsRepository.joinTableQuery(QueryFactory.equal("Users.id", usersId),Department.class,Users.class,RegistEvents.class,Events.class);
		List<EventsResponse> eventsByUsername = new ArrayList<EventsResponse>();
		Set<Long> listEventsId = new HashSet<Long>();
		for(Events event : events) {
			if(!listEventsId.contains(event.getId())) {
				if(!event.getIsDeleted()) {
					Users usersRegistEvent = new Users();
					Users usersEvent = getEventByEventId(event.getId()).get(0).getUsers();
					RegistEvents registEvents = event.getRegistEvents();
					Department department = departmentService.findByUserId(usersId); 
					usersRegistEvent.setDepartment(department);
					registEvents.setUsers(usersRegistEvent);
					event.setRegistEvents(registEvents);
					event.setUsers(usersEvent);
					EventsResponse eventsResponse = new EventsResponse();
					eventsResponse.setEvents(event);
					eventsResponse.setCountJoined(countJoinedByEvent(true,event.getId()));
					eventsResponse.setCountNotJoined(countJoinedByEvent(false,event.getId()));
					eventsByUsername.add(eventsResponse);
					listEventsId.add(event.getId());
				}
			}
		}
		return eventsByUsername;
	
	}

	public int countJoinedByEvent(boolean isJoined,Long eventId) throws SQLException {
		List<Events> events = eventsRepository.joinTableQuery(Events.class,RegistEvents.class,Users.class,Department.class);
		int count = 0;
		Set<Long> listUsersId = new HashSet<Long>();
		
		for(Events event : events) {
			RegistEvents registEvents = event.getRegistEvents();
			Users users = usersService.findByRegistEvents(registEvents.getId());
			if(!listUsersId.contains(users.getId())) {
				if(registEvents.getIsJoined().equals(isJoined) && event.getId().equals(eventId)) {
					count++;
					listUsersId.add(users.getId());
				}
			}
		}
		return count;
	}
	
	@Override
	public Long save(Events events) throws IllegalArgumentException, IllegalAccessException {
		return eventsRepository.save(events);
	}
	@Override
	public List<Events> getEventByEventId(Long eventsId) throws SQLException {
		List<Events> events=eventsRepository.joinTableQuery(Events.class,Users.class);
		List<Events> eventsByEventId=new ArrayList<Events>();
		for(Events event:events)
		{
			if(event.getId().equals(eventsId))
			{
				eventsByEventId.add(event);
			}
		}
		return eventsByEventId;
	}

	@Override
	public void updateEvents(Long eventsId) throws InstantiationException, IllegalAccessException, SQLException {
		Events eventsEntity = eventsRepository.findById(eventsId).get();
		eventsEntity.setIsDeleted(true);
		if(eventsEntity!=null) {
			eventsRepository.update(eventsEntity);
			
		}
		
	}
	@Override
	public void updateStatusEvents(Long eventsId) throws InstantiationException, IllegalAccessException, SQLException {
		Events eventsEntity = eventsRepository.findById(eventsId).get();
		eventsEntity.setStatus(2);
		if(eventsEntity!=null) {
			eventsRepository.update(eventsEntity);
		}
		
	}
	@Override
	public List<EventsResponse> searchByCondition(EventsSearchRequest eventsSearchRequest, Long usersId) throws SQLException, ParseException {
		List<Events> eventsByCondition =  eventsRepository.joinTableQuery(
				QueryFactory.and(
						Arrays.asList(QueryFactory.equal("Users.id", usersId), 
								QueryFactory.like("Events.eventName", eventsSearchRequest.getEventName()),
								QueryFactory.like("Events.place",eventsSearchRequest.getPlace()),
								QueryFactory.equal("Department.id",eventsSearchRequest.getDepartment()),
								QueryFactory.equal("MasterEventType.id",eventsSearchRequest.getMasterEventsType()),
								QueryFactory.equal("Events.startHour",eventsSearchRequest.getStartHour()),
								QueryFactory.equal("Events.startDate",eventsSearchRequest.getStartDate()),
								QueryFactory.equal("Events.endHour",eventsSearchRequest.getEndHour()),
								QueryFactory.equal("Events.endDate",eventsSearchRequest.getEndDate()),
								QueryFactory.equal("Events.status",eventsSearchRequest.getStatus()),
								QueryFactory.like("Users.fullName",eventsSearchRequest.getFullName())
								)
						),
				Department.class,Users.class,RegistEvents.class,Events.class,MasterEventType.class
		);
		System.out.println(eventsByCondition);
		List<EventsResponse> eventsByUsername = new ArrayList<EventsResponse>();
		Set<Long> listEventsId = new HashSet<Long>();
		for(Events event : eventsByCondition) {
			if(!listEventsId.contains(event.getId())) {
				if(!event.getIsDeleted()) {
					Users usersRegistEvent = new Users();
					Users usersEvent = getEventByEventId(event.getId()).get(0).getUsers();
					RegistEvents registEvents = event.getRegistEvents();
					Department department = departmentService.findByUserId(usersId); 
					usersRegistEvent.setDepartment(department);
					registEvents.setUsers(usersRegistEvent);
					event.setRegistEvents(registEvents);
					event.setUsers(usersEvent);
					EventsResponse eventsResponse = new EventsResponse();
					eventsResponse.setEvents(event);
					eventsResponse.setCountJoined(countJoinedByEvent(true,event.getId()));
					eventsResponse.setCountNotJoined(countJoinedByEvent(false,event.getId()));
					eventsByUsername.add(eventsResponse);
					listEventsId.add(event.getId());
				}
			}
		}
	
		return eventsByUsername;
	}
}
