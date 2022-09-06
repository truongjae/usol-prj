package entity;

import java.sql.SQLException;
import java.util.List;

import orm.querybuilder.QueryFactory;
import repository.EventsRepository;
import repository.UsersRepository;
import repository.impl.EventsRepositoryImpl;
import repository.impl.UsersRepositoryImpl;

public class main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		UsersRepository u = new UsersRepositoryImpl();
		List<Users> users = u.findAll();
		users.stream().forEach(System.out::println);
		
		Users user = new Users.UsersBuilder()
//				.departId(1L)
				.deleted(false)
				.userName("tuaha2a")
				.fullName("nguyen thi tu anh")
				.email("anha@gmail.com")
				.password("123a")
				.builder();
//		u.save(user);
		//u.findAll(QueryFactory.or(QueryFactory.equal("username", "asdas"), QueryFactory.equal("id", 1L))).stream().forEach(System.out::println);
		
		
		EventsRepository e = new EventsRepositoryImpl();
		e.findAll().stream().forEach(System.out::println);
		
	}
}
