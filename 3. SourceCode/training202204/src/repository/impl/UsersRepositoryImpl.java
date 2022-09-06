package repository.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import entity.Users;
import orm.AbstractQuery;
import repository.UsersRepository;


@Repository
public class UsersRepositoryImpl extends AbstractQuery<Users, Long> implements UsersRepository{

	public UsersRepositoryImpl() throws ClassNotFoundException, SQLException {
		super();
	}

}
