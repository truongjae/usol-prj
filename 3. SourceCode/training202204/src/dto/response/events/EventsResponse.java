package dto.response.events;

import entity.Events;

public class EventsResponse {
	private Events events;
	private int countJoined;
	private int countNotJoined;
	public Events getEvents() {
		return events;
	}
	public void setEvents(Events events) {
		this.events = events;
	}
	public int getCountJoined() {
		return countJoined;
	}
	public void setCountJoined(int countJoined) {
		this.countJoined = countJoined;
	}
	public int getCountNotJoined() {
		return countNotJoined;
	}
	public void setCountNotJoined(int countNotJoined) {
		this.countNotJoined = countNotJoined;
	}
	@Override
	public String toString() {
		return "EventsResponse [events=" + events + ", countJoined=" + countJoined + ", countNotJoined="
				+ countNotJoined + "]";
	}
	
}
