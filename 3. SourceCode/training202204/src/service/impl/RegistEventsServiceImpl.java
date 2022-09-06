package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Department;
import entity.Events;
import entity.RegistEvents;
import entity.Users;
import entity.VoteOption;
import orm.querybuilder.QueryFactory;
import repository.RegistEventsRepository;
import repository.UsersRepository;
import service.RegistEventsService;
@Service
public class RegistEventsServiceImpl implements RegistEventsService{
	@Autowired
	private RegistEventsRepository registEventsRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Override
	public List<RegistEvents> getRegistEventsByEventId(Long eventsId) throws SQLException{
		List<RegistEvents> registEvents=registEventsRepository.joinTableQuery(Events.class,RegistEvents.class,Users.class);
		List<RegistEvents> registEventsByEventId=new ArrayList<RegistEvents>();
		for(RegistEvents registEvent:registEvents)
		{
			if(registEvent.getEvents().getId().equals(eventsId))
			{
				System.out.println(registEvent.getEvents().getId());
				registEventsByEventId.add(registEvent);
			}
		}
		return registEventsByEventId;
	}
	public void updateRegisterEvents(Long eventsId)
			throws IllegalArgumentException, IllegalAccessException, SQLException, InstantiationException {
		List<RegistEvents> registEvents=registEventsRepository.joinTableQuery(Events.class,RegistEvents.class,VoteOption.class);
		for(RegistEvents registEvent:registEvents)
		{
			if(registEvent.getEvents().getId().equals(eventsId))
			{
				RegistEvents registEventsEntity =registEventsRepository .findById(registEvent.getId()).get();
				registEventsEntity.setIsDeleted(true);
				if(registEventsEntity!=null) {
					registEventsRepository.update(registEventsEntity);
					
			}
			}
		}
	}
	@Override
	public void save(String listMember, Long eventsId, Long voteOptionId) throws SQLException, IllegalArgumentException, IllegalAccessException {
		List<Long> listUsersId = convertRequestToListUsersId(listMember);
		Events events = new Events();
		events.setId(eventsId);
		for(Long usersId : listUsersId) {
			Users users = new Users();
			users.setId(usersId);
			
			VoteOption voteOption = new VoteOption();
			voteOption.setId(voteOptionId);
			RegistEvents registEvents = new RegistEvents.RegistEventsBuilder()
					.users(users)
					.events(events)
					.isJoined(false)
					.voteOption(voteOption)
					.voted(false)
					.attachedPersonAdult(0)
					.attachedPersonChild(0)
					.isDeleted(false)
					.note(null)
					.builder();
			registEventsRepository.save(registEvents);
		}
	}
	private List<Long> convertRequestToListUsersId(String listMember) throws SQLException {
		String arrStringMember[] = listMember.split(",");
		List<Long> listUsersId = new ArrayList<>();
		for(String member : arrStringMember) {
			String memberSplit[] = member.split("-");
			if(memberSplit.length == 2) {
				String memberId = memberSplit[1].split("_")[1];
				listUsersId.add(Long.parseLong(memberId));
			}
			else {
				Long departmentId = Long.parseLong(memberSplit[0].split("_")[1]);
				List<Users> usersByDepartment = usersRepository.joinTableQuery(QueryFactory.equal("Users.departmentId", departmentId),Users.class,Department.class);
				for(Users users : usersByDepartment) {
					listUsersId.add(users.getId());
				}
			}
		}
		return listUsersId;
	}
	@Override
	public List<RegistEvents> getRegistEventsDetailList(Long eventsId) throws SQLException {
		
		return null;
	}
}
