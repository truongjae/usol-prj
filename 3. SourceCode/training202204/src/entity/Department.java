package entity;

import orm.annotation.Column;
import orm.annotation.Entity;
import orm.annotation.Id;
import orm.annotation.OneToMany;



@Entity
public class Department {
	@Id
	private Long id;
	@Column
	private String departName;
	@Column
	private Boolean isDeleted;
	
	@OneToMany(mapping = Users.class)
	@Column
	private Users users;
	
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Department() {
		
	}
	public Department(DepartmentBuilder departmentBuilder) {
		this.id= departmentBuilder.id;
		this.departName = departmentBuilder.departName;
		this.isDeleted = departmentBuilder.isDeleted;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public static class DepartmentBuilder{
		private Long id;
		private String departName;
		private Boolean isDeleted;
		public DepartmentBuilder id(Long id) {
			this.id = id;
			return this;
		}
		public DepartmentBuilder departName(String departName) {
			this.departName = departName;
			return this;
		}
		public DepartmentBuilder isDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
			return this;
		}
		public Department builder() {
			return new Department(this);
		}
		
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", departName=" + departName + ", isDeleted=" + isDeleted + ", users=" + users
				+ "]";
	}



	
}
