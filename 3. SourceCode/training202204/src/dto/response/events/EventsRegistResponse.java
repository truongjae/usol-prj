package dto.response.events;

import entity.Department;
import entity.Events;
import entity.RegistEvents;
import entity.Users;

public class EventsRegistResponse {
	private Users users;
	private Department department;
	private Events events;
	private RegistEvents registEvents;
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Events getEvents() {
		return events;
	}
	public void setEvents(Events events) {
		this.events = events;
	}
	public RegistEvents getRegistEvents() {
		return registEvents;
	}
	public void setRegistEvents(RegistEvents registEvents) {
		this.registEvents = registEvents;
	}
	@Override
	public String toString() {
		return "EventsRegistResponse [users=" + users + ", department=" + department + ", events=" + events
				+ ", registEvents=" + registEvents + "]";
	}
}
