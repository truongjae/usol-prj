package entity;

import orm.annotation.Column;
import orm.annotation.Entity;
import orm.annotation.Id;
import orm.annotation.OneToMany;

@Entity
public class MasterEventType {
	public MasterEventType() {
		
	}
	@Id
	private Long id;
	
	@Column
	private String eventType;
	
	@Column
	private Boolean isDeleted;
	
	@OneToMany(mapping = Events.class)
	@Column
	private Events events;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Events getEvents() {
		return events;
	}

	public void setEvents(Events events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "MasterEventType [id=" + id + ", eventType=" + eventType + ", isDeleted=" + isDeleted + ", events="
				+ events + "]";
	}
}
