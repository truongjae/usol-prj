 	package service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Events;
import entity.RegistEvents;
import entity.VoteOption;
import repository.RegistEventsRepository;
import repository.VoteOptionRepository;
import service.VoteOptionService;
@Service
public class VoteOptionServiceImpl implements VoteOptionService{
	@Autowired
	private VoteOptionRepository voteOptionRepository;
	
	@Autowired
	private RegistEventsRepository registEventsRepository;
	
	@Override
	public List<VoteOption> getVoteOptionByEventsId(Long EventsId) throws SQLException {
		List<VoteOption> voteOptions=voteOptionRepository.joinTableQuery(Events.class,VoteOption.class);
		List<VoteOption> voteOptionList=new ArrayList<VoteOption>();
		for(VoteOption voteOption:voteOptions)
		{
			if(voteOption.getEvent().getId().equals(EventsId))
			{
				voteOptionList.add(voteOption);
			}
		}
		return voteOptionList;
	}
	
	public void updateVoteOption(Long eventsId) throws InstantiationException, IllegalAccessException, SQLException {
		List<VoteOption> voteOptions=voteOptionRepository.joinTableQuery(Events.class,VoteOption.class);
		for(VoteOption voteOption:voteOptions)
		{
			if(voteOption.getEvent().getId().equals(eventsId))
			{
				VoteOption voteOptionEntity=voteOptionRepository.findById(voteOption.getEvent().getId()).get();
				voteOptionEntity.setIsDeleted(true);
				if(voteOptionEntity!=null)
				{
					voteOptionRepository.update(voteOptionEntity);
				}
			}
		}
	}

	@Override
	public List<Long> saveTimeOption(String times, Long eventsId) throws ParseException, IllegalArgumentException, IllegalAccessException {
		String[] arrTimes = times.split(",");
		Events events = new Events();
		events.setId(eventsId);
		List<Long> voteTimeOptionId = new ArrayList<Long>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		for(String time : arrTimes) {
			VoteOption voteOption = new VoteOption.VoteOptionBuilder()
					.events(events)
					.startDate(formatter.parse(time))
					.isDeleted(false)
					.builder();
			Long id = voteOptionRepository.save(voteOption);
			voteTimeOptionId.add(id);
		}
		return voteTimeOptionId;
	}

	@Override
	public List<Long> savePlaceOption(String places, Long eventsId) throws IllegalArgumentException, IllegalAccessException{
		String[] arrPlaces = places.split(",");
		Events events = new Events();
		events.setId(eventsId);
		List<Long> votePlaceOptionId = new ArrayList<Long>();
		for(String place : arrPlaces) {
			VoteOption voteOption = new VoteOption.VoteOptionBuilder()
					.events(events)
					.place(place)
					.isDeleted(false)
					.builder();
			Long id = voteOptionRepository.save(voteOption);
			votePlaceOptionId.add(id);
		}
		return votePlaceOptionId;
	}

	@Override
	public void insertImage(String urlImage, Long voteOptionId) throws IllegalArgumentException, IllegalAccessException, SQLException {
		VoteOption voteOption = new VoteOption.VoteOptionBuilder()
				.id(voteOptionId)
				.optionImage(urlImage)
				.builder();
		voteOptionRepository.update(voteOption);
	}

	@Override
	public void updateVote(String isJoined, String listResultVote, String attachedPersonAdult,
			String attachedPersonChild, String note) throws IllegalArgumentException, IllegalAccessException, SQLException {
		
		String[] listVote = listResultVote.split(",");
		for(String vote : listVote) {
			String result[] = vote.split("_");
			Boolean voted = !result[1].equals("false");
			Boolean isJoinedBoolean = !isJoined.equals("false");
			if(isJoinedBoolean) {
				RegistEvents registEvents = new RegistEvents.RegistEventsBuilder()
						.attachedPersonAdult(Integer.parseInt(attachedPersonAdult))
						.attachedPersonChild(Integer.parseInt(attachedPersonChild))
						.note(note)
						.voted(voted)
						.isJoined(isJoinedBoolean)
						.id(Long.parseLong(result[0]))
						.builder();
				registEventsRepository.update(registEvents);
			}
			else {
				RegistEvents registEvents = new RegistEvents.RegistEventsBuilder()
						.isJoined(isJoinedBoolean)
						.id(Long.parseLong(result[0]))
						.builder();
				registEventsRepository.update(registEvents);
			}
			
		}
		
	}

	

}
