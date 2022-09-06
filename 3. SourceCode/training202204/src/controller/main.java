package controller;

import java.sql.SQLException;

import repository.EventsRepository;
import repository.impl.EventsRepositoryImpl;

public class main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		EventsRepository e = new EventsRepositoryImpl();
		e.findAll().stream().forEach(System.out::println);
	}
}
