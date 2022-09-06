package entity;

import orm.annotation.Column;
import orm.annotation.Entity;
import orm.annotation.Id;
import orm.annotation.ManyToOne;

@Entity
public class RegistEvents {
	public RegistEvents() {
		
	}
	
	
	public RegistEvents(RegistEventsBuilder registEventsBuilder) {
		this.id = registEventsBuilder.id;
		this.users = registEventsBuilder.users;
		this.events = registEventsBuilder.events;
		this.isJoined = registEventsBuilder.isJoined;
		this.voteOption = registEventsBuilder.voteOption;
		this.voted = registEventsBuilder.voted;
		this.attachedPersonAdult = registEventsBuilder.attachedPersonAdult;
		this.attachedPersonChild = registEventsBuilder.attachedPersonChild;
		this.isDeleted = registEventsBuilder.isDeleted;
		this.note = registEventsBuilder.note;
	}


	@Id
	private Long id;
	
	@ManyToOne(mapping = Users.class)
	@Column
	private Users users;
	
	@Column
	@ManyToOne(mapping = Events.class)
	private Events events;
	
	@Column
	private Boolean isJoined;
	
	@ManyToOne(mapping = VoteOption.class)
	@Column
	private VoteOption voteOption;
	
	@Column
	private Boolean voted;
	
	@Column
	private Integer attachedPersonAdult;
	
	@Column
	private Integer attachedPersonChild;
	
	@Column
	private Boolean isDeleted;
	
	@Column
	private String note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Events getEvents() {
		return events;
	}

	public void setEvents(Events events) {
		this.events = events;
	}

	public Boolean getIsJoined() {
		return isJoined;
	}

	public void setIsJoined(Boolean isJoined) {
		this.isJoined = isJoined;
	}

	public VoteOption getVoteOption() {
		return voteOption;
	}

	public void setVoteOption(VoteOption voteOption) {
		this.voteOption = voteOption;
	}

	public Boolean getVoted() {
		return voted;
	}

	public void setVoted(Boolean voted) {
		this.voted = voted;
	}

	public Integer getAttachedPersonAdult() {
		return attachedPersonAdult;
	}

	public void setAttachedPersonAdult(Integer attachedPersonAdult) {
		this.attachedPersonAdult = attachedPersonAdult;
	}

	public Integer getAttachedPersonChild() {
		return attachedPersonChild;
	}

	public void setAttachedPersonChild(Integer attachedPersonChild) {
		this.attachedPersonChild = attachedPersonChild;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
	public static class RegistEventsBuilder{
		private Long id;
		private Users users;
		private Events events;
		private Boolean isJoined;
		private VoteOption voteOption;
		private Boolean voted;
		private Integer attachedPersonAdult;
		private Integer attachedPersonChild;
		private Boolean isDeleted;
		private String note;
		public RegistEventsBuilder id(Long id) {
			this.id = id;
			return this;
		}
		public RegistEventsBuilder users(Users users) {
			this.users = users;
			return this;
		}
		public RegistEventsBuilder events(Events events) {
			this.events = events;
			return this;
		}
		public RegistEventsBuilder isJoined(Boolean isJoined) {
			this.isJoined = isJoined;
			return this;
		}
		public RegistEventsBuilder voteOption(VoteOption voteOption) {
			this.voteOption = voteOption;
			return this;
		}
		public RegistEventsBuilder voted(Boolean voted) {
			this.voted = voted;
			return this;
		}
		public RegistEventsBuilder attachedPersonAdult(Integer attachedPersonAdult) {
			this.attachedPersonAdult = attachedPersonAdult;
			return this;
		}
		public RegistEventsBuilder attachedPersonChild(Integer attachedPersonChild) {
			this.attachedPersonChild = attachedPersonChild;
			return this;
		}
		public RegistEventsBuilder isDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
			return this;
		}
		public RegistEventsBuilder note(String note) {
			this.note = note;
			return this;
		}
		
		public RegistEvents builder() {
			return new RegistEvents(this);
		}
	}
	
	
	@Override
	public String toString() {
		return "RegistEvents [id=" + id + ", users=" + users + ", events=" + events + ", isJoined=" + isJoined
				+ ", voteOption=" + voteOption + ", voted=" + voted + ", attachedPersonAdult=" + attachedPersonAdult
				+ ", attachedPersonChild=" + attachedPersonChild + ", isDeleted=" + isDeleted + ", note=" + note + "]";
	}
	
}
