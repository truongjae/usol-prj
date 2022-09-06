package controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.Users;
import service.DepartmentService;
import service.EventsService;
import service.MasterEventTypeService;
import service.UsersService;

@Controller
public class LoginController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	private UsersService userService;
	@Autowired
	private EventsService eventsService;
	@Autowired
	private DepartmentService departmentService ;
	@Autowired
	private MasterEventTypeService masterEventTypeService;
	@RequestMapping("Login.htm")
	public String Login(HttpSession session) throws SQLException, InstantiationException, IllegalAccessException {
		if(request.getParameter("username")!= null && request.getParameter("password") != null) {
			Users users = userService.login(request.getParameter("username"), request.getParameter("password"));
			if (users != null) {
				session.setAttribute("username", users.getUserName());
				session.setAttribute("users", users);
				session.setAttribute("userList",userService.getUsersByUserName(request.getParameter("username")));
				session.setAttribute("events",eventsService.findAllByUsersId(users.getId()));
				session.setAttribute("departmentList", departmentService.findAll());
				session.setAttribute("masterEventTypeList", masterEventTypeService.findAll());
				return "EventList";
			}
			else {
				if(session.getAttribute("username")!=null)
				{
					return "EventList";
				}
				else {
					request.setAttribute("invalid", "Account does not exists!");
					return "index";
				}
			}
		}
		else if(session.getAttribute("username")!=null)
			return "EventList";
		return "index";
	}
	@RequestMapping("/Logout.htm")
	public String Logout(HttpSession session, Users users)
	{
		if(session.getAttribute("username")!=null)
		{
			session.removeAttribute("username");
		}
		return "index";
	}
	
}