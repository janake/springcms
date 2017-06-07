package org.prodet.service.dao;

import java.time.LocalDateTime;
import java.util.List;
import org.prodet.repository.domain.Role;
import org.prodet.repository.domain.User;

public class UserView {
	
	private long id;
	
	private String userName;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDateTime createdDate;
	
	private boolean status;
	
	private List<Role> roles;
	
	public UserView() {
	}

	public UserView(User user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.createdDate = user.getCreatedDate();
		this.status = user.isStatus();
		this.roles = user.getRoles();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
