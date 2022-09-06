package controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.VoteOptionService;

@RestController
public class VoteController {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private VoteOptionService voteOptionService;
	
	@RequestMapping(value="updateVote.htm",method=RequestMethod.POST)
	public void updateVote(@RequestParam("isJoined") String isJoined,
			@RequestParam("listResultVote") String listResultVote,
			@RequestParam("attachedPersonAdult") String attachedPersonAdult,
			@RequestParam("attachedPersonChild") String attachedPersonChild,
			@RequestParam("note") String note
			) throws IllegalArgumentException, IllegalAccessException, SQLException {
		voteOptionService.updateVote(isJoined, listResultVote, attachedPersonAdult, attachedPersonChild, note);
	}
}
