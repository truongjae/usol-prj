package entity;

import java.util.Date;

import orm.annotation.Column;
import orm.annotation.Entity;
import orm.annotation.Id;
import orm.annotation.ManyToOne;
import orm.annotation.OneToMany;
import orm.annotation.Time;

@Entity
public class Events {
	@Id
	private Long id;
	@Column
	private String eventName;
	
	@Column
	@ManyToOne(mapping = MasterEventType.class)
	private MasterEventType masterEventType;
	@Column
	@OneToMany(mapping=VoteOption.class)
	private VoteOption voteOption;
	public VoteOption getVoteOption() {
		return voteOption;
	}

	public void setVoteOption(VoteOption voteOption) {
		this.voteOption = voteOption;
	}


	@Column
	private String eventImage;
	
	@ManyToOne(mapping = Users.class)
	@Column
	private Users users;
	
	@Column
	private String description;
	@Column
	private Integer status;
	@Column
	private Integer optionType;
	@Column
	private String place;
	@Column
	@orm.annotation.Date
	private Date startDate;
	@Column
	@Time
	private Date startHour;
	@Column
	@orm.annotation.Date
	private Date endDate;
	@Column
	@Time
	private Date endHour;
	@Column
	private Boolean isDeleted;
	
	@OneToMany(mapping = RegistEvents.class)
	@Column
	private RegistEvents registEvents;
	
	public Events() {}
	
	public Events(EventsBuilder eventsBuilder) {
		this.id = eventsBuilder.id;
		this.eventName = eventsBuilder.eventName;
		this.masterEventType = eventsBuilder.masterEventType;
		this.eventImage = eventsBuilder.eventImage;
		this.users = eventsBuilder.users;
		this.description = eventsBuilder.description;
		this.status = eventsBuilder.status;
		this.optionType = eventsBuilder.optionType;
		this.place = eventsBuilder.place;
		this.startDate = eventsBuilder.startDate;
		this.startHour = eventsBuilder.startHour;
		this.endDate = eventsBuilder.endDate;
		this.endHour = eventsBuilder.endHour;
		this.isDeleted = eventsBuilder.isDeleted;
		this.registEvents = eventsBuilder.registEvents;
		//this.voteOption=eventsBuilder.voteOption;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public MasterEventType getMasterEventType() {
		return masterEventType;
	}
	public void setMasterEventType(MasterEventType masterEventType) {
		this.masterEventType = masterEventType;
	}
	public String getEventImage() {
		return eventImage;
	}
	public void setEventImage(String eventImage) {
		this.eventImage = eventImage;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOptionType() {
		return optionType;
	}
	public void setOptionType(Integer optionType) {
		this.optionType = optionType;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getStartHour() {
		return startHour;
	}
	public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getEndHour() {
		return endHour;
	}
	public void setEndHour(Date endHour) {
		this.endHour = endHour;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public RegistEvents getRegistEvents() {
		return registEvents;
	}
	public void setRegistEvents(RegistEvents registEvents) {
		this.registEvents = registEvents;
	}


	public static class EventsBuilder{
		private Long id;
		private String eventName;
		private MasterEventType masterEventType;
		private String eventImage;
		private Users users;
		private String description;
		private Integer status;
		private Integer optionType;
		private String place;
		private Date startDate;
		private Date startHour;
		private Date endDate;
		private Date endHour;
		private Boolean isDeleted;
		private RegistEvents registEvents;
		private VoteOption voteOption;
		public EventsBuilder id(Long id) {
			this.id = id;
			return this;
		}
		public EventsBuilder eventName(String eventName) {
			this.eventName = eventName;
			return this;
		}
		public EventsBuilder masterEventType(MasterEventType masterEventType) {
			this.masterEventType = masterEventType;
			return this;
		}
		public EventsBuilder eventImage(String eventImage) {
			this.eventImage = eventImage;
			return this;
		}
		public EventsBuilder users(Users users) {
			this.users = users;
			return this;
		}
		public EventsBuilder description(String description) {
			this.description = description;
			return this;
		}
		public EventsBuilder status(Integer status) {
			this.status = status;
			return this;
		}
		public EventsBuilder optionType(Integer optionType) {
			this.optionType = optionType;
			return this;
		}
		public EventsBuilder place(String place) {
			this.place = place;
			return this;
		}
		public EventsBuilder startDate(Date startDate) {
			this.startDate = startDate;
			return this;
		}
		public EventsBuilder startHour(Date startHour) {
			this.startHour = startHour;
			return this;
		}
		public EventsBuilder endDate(Date endDate) {
			this.endDate = endDate;
			return this;
		}
		public EventsBuilder endHour(Date endHour) {
			this.endHour = endHour;
			return this;
		}
		public EventsBuilder isDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
			return this;
		}
		public EventsBuilder registEvents(RegistEvents registEvents) {
			this.registEvents = registEvents;
			return this;
		}
		
		public EventsBuilder voteOption(VoteOption voteOption) {
			this.voteOption = voteOption;
			return this;
		}
		public Events builder() {
			return new Events(this);
		}
	}


	@Override
	public String toString() {
		return "Events [id=" + id + ", eventName=" + eventName + ", masterEventType=" + masterEventType
				+ ", voteOption=" + voteOption + ", eventImage=" + eventImage + ", users=" + users + ", description="
				+ description + ", status=" + status + ", optionType=" + optionType + ", place=" + place
				+ ", startDate=" + startDate + ", startHour=" + startHour + ", endDate=" + endDate + ", endHour="
				+ endHour + ", isDeleted=" + isDeleted + ", registEvents=" + registEvents + "]";
	}



	
	
	
}
