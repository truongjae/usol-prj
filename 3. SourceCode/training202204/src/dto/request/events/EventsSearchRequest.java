package dto.request.events;

public class EventsSearchRequest {
	private String eventName;
	private String place;
	private Long masterEventsType;
	private String startHour;
	private String startDate;
	private String endHour;
	private String endDate;
	private Long department;
	private Integer status;
	private String fullName;
	
	public EventsSearchRequest() {
		super();
	}

	public EventsSearchRequest(EventsSearchRequestBuilder eventsSearchRequestBuilder) {
		this.eventName = eventsSearchRequestBuilder.eventName;
		this.place = eventsSearchRequestBuilder.place;
		this.masterEventsType = eventsSearchRequestBuilder.masterEventsType;
		this.startHour = eventsSearchRequestBuilder.startHour;
		this.startDate = eventsSearchRequestBuilder.startDate;
		this.endHour = eventsSearchRequestBuilder.endHour;
		this.endDate = eventsSearchRequestBuilder.endDate;
		this.department = eventsSearchRequestBuilder.department;
		this.status = eventsSearchRequestBuilder.status;
		this.fullName = eventsSearchRequestBuilder.fullName;
	}
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
		
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
		
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Long getMasterEventsType() {
		return masterEventsType;
	}
	public void setMasterEventsType(Long masterEventsType) {
		this.masterEventsType = masterEventsType;
	}
	public Long getDepartment() {
		return department;
	}
	public void setDepartment(Long department) {
		this.department = department;
	}
	public static class EventsSearchRequestBuilder{
		private String eventName;
		private String place;
		private Long masterEventsType;
		private String startHour;
		private String startDate;
		private String endHour;
		private String endDate;
		private Long department;
		private Integer status;
		private String fullName;
		public EventsSearchRequestBuilder eventName(String eventName) {
			this.eventName=eventName;
			return this;
		}
		public EventsSearchRequestBuilder place(String place) {
			this.place = place;
			return this;
		}
		public EventsSearchRequestBuilder masterEventsType(Long masterEventsType) {
			this.masterEventsType = masterEventsType;
			return this;
		}
		public EventsSearchRequestBuilder startHour(String startHour) {
			this.startHour = startHour;
			return this;
		}
		public EventsSearchRequestBuilder startDate(String startDate) {
			this.startDate = startDate;
			return this;
		}
		public EventsSearchRequestBuilder endHour(String endHour) {
			this.endHour = endHour;
			return this;
		}
		public EventsSearchRequestBuilder endDate(String endDate) {
			this.endDate = endDate;
			return this;
		}
		public EventsSearchRequestBuilder department(Long department) {
			this.department = department;
			return this;
		}
		public EventsSearchRequestBuilder status(Integer status) {
			this.status = status;
			return this;
		}
		public EventsSearchRequestBuilder fullName(String fullName) {
			this.fullName = fullName;
			return this;
		}
		public EventsSearchRequest build() {
			return new EventsSearchRequest(this);
		}
		
		
	}
	@Override
	public String toString() {
		return "EventsSearchRequest [eventName=" + eventName + ", place=" + place + ", masterEventsType="
				+ masterEventsType + ", startHour=" + startHour + ", startDate=" + startDate + ", endHour=" + endHour
				+ ", endDate=" + endDate + ", department=" + department + ", status=" + status + ", fullName="
				+ fullName + "]";
	}
	
	
	
	
}
