package service;

import java.sql.SQLException;
import java.util.List;

import entity.Department;

public interface DepartmentService {
	List<Department> findAll();
	Department findByUserId(Long usersId) throws SQLException;
}
