package controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import orm.utils.ReflectionUtils;
import service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService usersService;

	@Autowired
	HttpServletRequest request;

	@GetMapping("department.htm")
	@ResponseBody
	public String findByDepartment() throws IllegalArgumentException, IllegalAccessException, SQLException {
		return ReflectionUtils
				.convertListObjectsToJson(usersService.findByDepartment(Long.parseLong(request.getParameter("id"))));
	}

}
