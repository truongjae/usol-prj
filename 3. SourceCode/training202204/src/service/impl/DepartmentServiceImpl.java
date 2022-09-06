package service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Department;
import entity.Users;
import orm.querybuilder.QueryFactory;
import repository.DepartmentRepository;
import service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	public Department findByUserId(Long usersId) throws SQLException {
		List<Department> departments = departmentRepository.joinTableQuery(QueryFactory.equal("Users.id", usersId),Department.class,Users.class);
		return departments.get(0);
	}
	
}
