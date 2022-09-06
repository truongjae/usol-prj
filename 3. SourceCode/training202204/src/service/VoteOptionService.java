package service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import entity.VoteOption;

public interface VoteOptionService {

	List<VoteOption> getVoteOptionByEventsId(Long EventsId) throws SQLException;

	void updateVoteOption(Long eventsId) throws InstantiationException, IllegalAccessException, SQLException;
	
	List<Long> saveTimeOption(String times, Long eventsId) throws ParseException, IllegalArgumentException, IllegalAccessException;
	
	List<Long> savePlaceOption(String places, Long eventsId) throws IllegalArgumentException, IllegalAccessException;
	
	void insertImage(String urlImage, Long voteOptionId) throws IllegalArgumentException, IllegalAccessException, SQLException;
	
	void updateVote(String isJoined, String listResultVote,String attachedPersonAdult,String attachedPersonChild,String note) throws IllegalArgumentException, IllegalAccessException, SQLException;
}
