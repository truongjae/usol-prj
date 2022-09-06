package repository.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import entity.Department;
import orm.AbstractQuery;
import repository.DepartmentRepository;


@Repository
public class DepartmentRepositoryImpl extends AbstractQuery<Department, Long> implements DepartmentRepository{

	public DepartmentRepositoryImpl() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
}
