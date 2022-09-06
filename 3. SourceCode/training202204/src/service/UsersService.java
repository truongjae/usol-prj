package service;

import java.sql.SQLException;
import java.util.List;

import entity.Users;

public interface UsersService {
	Users login(String username, String password);
	List<Users> findAll();
	List<Users> findByDepartment(Long departmentId) throws SQLException;
	List<Users> getUsersByUserName(String username);
	Users findByRegistEvents(Long registEventsId) throws SQLException;
}
