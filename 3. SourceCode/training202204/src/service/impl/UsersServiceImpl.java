package service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Department;
import entity.RegistEvents;
import entity.Users;
import orm.querybuilder.QueryFactory;
import repository.UsersRepository;
import service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public Users login(String username, String password) {
		List<Users> users = usersRepository.findAll(QueryFactory.and(QueryFactory.equal("username", username),QueryFactory.equal("password",password)));
		if(users.size() > 0) {
			return users.get(0);
		}
		return null;
	}
	@Override
	public List<Users> findAll() {
		return usersRepository.findAll();
		
	}
	@Override
	public List<Users> findByDepartment(Long departmentId) throws SQLException {
//		return usersRepository.findAll(QueryFactory.equal("departmentId", departmentId));
		return usersRepository.joinTableQuery(QueryFactory.equal("Users.departmentId", departmentId),Users.class,Department.class);
	}
	@Override
	public List<Users> getUsersByUserName(String username) {
		return usersRepository.findAll(QueryFactory.equal("userName", username));
	}
	@Override
	public Users findByRegistEvents(Long registEventsId) throws SQLException {
		return usersRepository.joinTableQuery(QueryFactory.equal("RegistEvents.id", registEventsId),RegistEvents.class,Users.class).get(0);
	}
	
}
