package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.request.events.EventsSearchRequest;
import dto.response.events.EventsResponseSaveInfo;
import entity.Events;
import entity.Users;
import orm.utils.ReflectionUtils;
import service.DepartmentService;
import service.EventsService;
import service.MasterEventTypeService;
import service.RegistEventsService;
import service.VoteOptionService;

@Controller
public class EventsController {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpSession session;

	@Autowired
	private EventsService eventsService;
	@Autowired
	private MasterEventTypeService masterEventTypeService;

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private RegistEventsService registEventsService;
	@Autowired
	private VoteOptionService voteOptionService;

	@RequestMapping("InsertEvent.htm")
	public String ShowInsertForm() {
		request.setAttribute("departmentList", departmentService.findAll());
		request.setAttribute("masterEventTypeList", masterEventTypeService.findAll());
		return "InsertEvent";
	}

	@PostMapping("EventsInsert.htm")
	@ResponseBody
	public String InsertEvents() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
			SecurityException, ParseException, SQLException {
		Events events = ReflectionUtils.convertRequestToEntity(Events.class, request);
		Users users = (Users) session.getAttribute("users");
		events.setUsers(users);
		events.setStatus(1);
		events.setIsDeleted(false);
		Long eventsId = eventsService.save(events);
		EventsResponseSaveInfo eventsResponseSaveInfo = new EventsResponseSaveInfo();
		eventsResponseSaveInfo.setEventsId(String.valueOf(eventsId));
		
		/*if(request.getParameter("members")!=null) {
			registEventsService.save(request.getParameter("members"), eventsId);
		}
			
		
		if(request.getParameter("times")!=null)
			voteOptionService.saveTimeOption(request.getParameter("times"), eventsId);
		
		if(request.getParameter("places")!=null) {
			eventsResponseSaveInfo.setListVoteOptionId(voteOptionService.savePlaceOption(request.getParameter("places"), eventsId).toString());
		}*/
		
		if(request.getParameter("members")!=null) {
			
			if(request.getParameter("times")!=null) {
				List<Long> voteTimeOptionId = voteOptionService.saveTimeOption(request.getParameter("times"), eventsId);
				for(Long voteId : voteTimeOptionId) {
					registEventsService.save(request.getParameter("members"), eventsId,voteId);
				}
			}
				
			
			if(request.getParameter("places")!=null) {
				List<Long> votePlaceOptionId = voteOptionService.savePlaceOption(request.getParameter("places"), eventsId);
				for(Long voteId : votePlaceOptionId) {
					registEventsService.save(request.getParameter("members"), eventsId,voteId);
				}
				eventsResponseSaveInfo.setListVoteOptionId(votePlaceOptionId.toString());
			}
		}
			
		return ReflectionUtils.convertObjectToJson(eventsResponseSaveInfo);
	}

	@RequestMapping("DeleteEvents.htm")
	public String DeletedEvent() throws InstantiationException, IllegalAccessException, SQLException {
		eventsService.updateEvents(Long.parseLong(request.getParameter("id")));
		registEventsService.updateRegisterEvents(Long.parseLong(request.getParameter("id")));
		voteOptionService.updateVoteOption(Long.parseLong(request.getParameter("id")));
		session.setAttribute("events", eventsService.findAllByUsersId(((Users) session.getAttribute("users")).getId()));
		return "EventList";
	}

	@RequestMapping("DetailEvent.htm")
	public String ShowEventDetail() throws NumberFormatException, SQLException {
		request.setAttribute("registEventsDetailList", registEventsService.getRegistEventsDetailList(Long.parseLong(request.getParameter("EventId"))));
		request.setAttribute("eventDetails",
				eventsService.getEventByEventId(Long.parseLong(request.getParameter("EventId"))));
		request.setAttribute("registEventDetail",
				registEventsService.getRegistEventsByEventId(Long.parseLong(request.getParameter("EventId"))));
		request.setAttribute("voteOptionList",
				voteOptionService.getVoteOptionByEventsId(Long.parseLong(request.getParameter("EventId"))));
		return "EventDetail";
	}

	@RequestMapping("VoteEvents.htm")
	public String VoteEventsList() throws NumberFormatException, SQLException {
		request.setAttribute("registEventDetail",
				registEventsService.getRegistEventsByEventId(Long.parseLong(request.getParameter("EventId"))));
		request.setAttribute("voteOptionList",
				voteOptionService.getVoteOptionByEventsId(Long.parseLong(request.getParameter("EventId"))));
		request.setAttribute("eventsDetail",
				eventsService.getEventByEventId(Long.parseLong(request.getParameter("EventId"))));
		request.setAttribute("departmentList", departmentService.findAll());
		request.setAttribute("masterEventTypeList", masterEventTypeService.findAll());
		request.setAttribute("eventDetails",
				eventsService.getEventByEventId(Long.parseLong(request.getParameter("EventId"))));
		request.setAttribute("registEventDetail",
				registEventsService.getRegistEventsByEventId(Long.parseLong(request.getParameter("EventId"))));
		return "EventRegister";
	}
	@RequestMapping("UpdateEventsStatus.htm")
	public String UpdateEventsStatus()
			throws NumberFormatException, InstantiationException, IllegalAccessException, SQLException {
		eventsService.updateStatusEvents(Long.parseLong(request.getParameter("id")));
		session.setAttribute("events", eventsService.findAllByUsersId(((Users) session.getAttribute("users")).getId()));
		return "EventList";
	}

	@RequestMapping("EventsRegister.htm")
	public String EventRegister() throws NumberFormatException, SQLException {
		request.setAttribute("registEventDetail",
				registEventsService.getRegistEventsByEventId(Long.parseLong(request.getParameter("EventId"))));
		request.setAttribute("voteOptionList",
				voteOptionService.getVoteOptionByEventsId(Long.parseLong(request.getParameter("EventId"))));
		request.setAttribute("eventsDetail",
				eventsService.getEventByEventId(Long.parseLong(request.getParameter("EventId"))));
		request.setAttribute("departmentList", departmentService.findAll());
		request.setAttribute("masterEventTypeList", masterEventTypeService.findAll());
		request.setAttribute("eventDetails",
				eventsService.getEventByEventId(Long.parseLong(request.getParameter("EventId"))));
		
		Long eventsId = Long.parseLong(request.getParameter("EventId"));
		
		
		
		return "EventRegister";
	}
	
	@RequestMapping("searchEventInfo.htm")
	public String searchEvents() throws SQLException, ParseException, NoSuchFieldException, SecurityException, IllegalArgumentException
	{
		Users user=(Users)session.getAttribute("users");
		EventsSearchRequest eventsSearchRequest = ReflectionUtils.convertRequestToEntity(EventsSearchRequest.class,request);
		System.out.println(eventsSearchRequest);
		session.setAttribute("events", eventsService.searchByCondition(eventsSearchRequest, user.getId()));
		return "EventList";
	}
	
}
