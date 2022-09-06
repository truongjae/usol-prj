package entity;

import java.util.Date;

import orm.annotation.Column;
import orm.annotation.Entity;
import orm.annotation.Id;
import orm.annotation.ManyToOne;
import orm.annotation.OneToMany;

@Entity
public class VoteOption {
	
	@Id
	private Long id;
	
	@Column
	@ManyToOne(mapping = Events.class)
	private Events events;
	
	@Column
	@OneToMany(mapping = RegistEvents.class)
	private RegistEvents RegistEvents;
	
	@Column
	private Date startDate;
	@Column
	private String place;
	@Column
	private String optionImage;
	@Column
	private Boolean isDeleted;
	public VoteOption() {}
	
	public VoteOption(VoteOptionBuilder voteOptionBuilder) {
		this.id=voteOptionBuilder.id;
		this.RegistEvents=voteOptionBuilder.RegistEvents;
		this.startDate=voteOptionBuilder.startDate;
		this.place=voteOptionBuilder.place;
		this.optionImage=voteOptionBuilder.optionImage;
		this.isDeleted=voteOptionBuilder.isDeleted;
		this.events=voteOptionBuilder.events;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Events getEvent() {
		return events;
	}
	public void setEvent(Events events) {
		this.events = events;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getOptionImage() {
		return optionImage;
	}
	public void setOptionImage(String optionImage) {
		this.optionImage = optionImage;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public RegistEvents getRegistEvents() {
		return RegistEvents;
	}
	public void setRegistEvents(RegistEvents registEvents) {
		RegistEvents = registEvents;
	}
	public static class VoteOptionBuilder{

		private Long id;
		private RegistEvents RegistEvents;
		private Date startDate;
		private String place;
		private String optionImage;
		private Boolean isDeleted;
		private Events events;
		public VoteOptionBuilder id(Long id) {
			this.id = id;
			return this;
		}
		public VoteOptionBuilder registEvents(RegistEvents registEvents) {
			this.RegistEvents = registEvents;
			return this;
		}
		public VoteOptionBuilder startDate(Date startDate) {
			this.startDate = startDate;
			return this;
		}
		public VoteOptionBuilder place(String place) {
			this.place = place;
			return this;
		}
		public VoteOptionBuilder optionImage(String optionImage) {
			this.optionImage = optionImage;
			return this;
		}
		public VoteOptionBuilder isDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
			return this;
		}
		public VoteOptionBuilder events(Events events) {
			this.events = events;
			return this;
		}
		public VoteOption builder() {
			return new VoteOption(this);
		}
	}
	@Override
	public String toString() {
		return "VoteOption [id=" + id + ", events=" + events + ", RegistEvents=" + RegistEvents + ", startDate="
				+ startDate + ", place=" + place + ", optionImage=" + optionImage + ", isDeleted=" + isDeleted + "]";
	}
	
	
}
