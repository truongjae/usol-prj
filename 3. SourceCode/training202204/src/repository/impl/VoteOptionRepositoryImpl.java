package repository.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import entity.VoteOption;
import orm.AbstractQuery;
import repository.VoteOptionRepository;
@Repository
public class VoteOptionRepositoryImpl extends AbstractQuery<VoteOption,Long> implements VoteOptionRepository{

	public VoteOptionRepositoryImpl() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

}
