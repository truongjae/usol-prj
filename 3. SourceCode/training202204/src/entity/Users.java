package entity;

import java.util.List;

import orm.annotation.Column;
import orm.annotation.Entity;
import orm.annotation.Id;
import orm.annotation.Ignore;
import orm.annotation.ManyToOne;
import orm.annotation.OneToMany;

@Entity
public class Users {
	@Id
	private Long id;
	@Column
	private String userName;
	@Column
	private String fullName;

	@Column
	private String email;
	
	@Column
	@Ignore
	private String password;
	@Column
	private Boolean isDeleted;
	
	@ManyToOne(mapping = Department.class)
	@Column
	private Department department;
	
	@OneToMany(mapping = Events.class)
	@Column
	private Events events;
	
	@OneToMany(mapping = RegistEvents.class)
	@Column
	private RegistEvents registEvents;
	
	public Users() {
		
	}
	public Users(UsersBuilder userBuilder) {
		this.id = userBuilder.id;
		this.userName = userBuilder.userName;
		this.fullName = userBuilder.fullName;
		this.department = userBuilder.department;
		this.email = userBuilder.email;
		this.password = userBuilder.password;
		this.isDeleted = userBuilder.isDeleted;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public static class UsersBuilder{
		private Long id;
		private String userName;
		private String fullName;
		private Department department;
		private String email;
		private String password;
		private Boolean isDeleted;
		public UsersBuilder id(Long id) {
			this.id = id;
			return this;
		}
		public UsersBuilder userName(String userName) {
			this.userName = userName;
			return this;
		}
		public UsersBuilder fullName(String fullName) {
			this.fullName = fullName;
			return this;
		}
		public UsersBuilder department(Department department) {
			this.department = department;
			return this;
		}
		public UsersBuilder email(String email) {
			this.email = email;
			return this;
		}
		public UsersBuilder password(String password) {
			this.password = password;
			return this;
		}
		public UsersBuilder deleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
			return this;
		}
		public Users builder() {
			return new Users(this);
		}
		
	}




	@Override
	public String toString() {
		return "Users [id=" + id + ", userName=" + userName + ", fullName=" + fullName + ", email=" + email
				+ ", password=" + password + ", isDeleted=" + isDeleted + ", department=" + department + ", events="
				+ events + ", registEvents=" + registEvents + "]";
	}

	

}
