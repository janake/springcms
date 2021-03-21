package org.prodet.repository.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.prodet.service.dao.UserView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {
	
	@GeneratedValue
	@Id
	private long id;
	
	@Column(unique=true)
	private String userName;
	
	@Email
	@NotNull
	@Column(unique=true)
	private String email;
	
	@NotNull
	private String passwd;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDateTime createdDate;
	
	private boolean status;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Role> roles;
	
	public User() {
	}

	public User(long id, String userName, String email, String passwd, String firstName, String lastName,
			LocalDateTime createdDate, boolean status, List<Role> list) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.passwd = passwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.createdDate = createdDate;
		this.status = status;
		this.roles = list;
	}
	
		public User(String userName, String email, String passwd, String firstName, String lastName,
			 boolean status, List<Role> roles) {
		super();
		setPasswd(passwd);
		setCreatedDate();
		this.userName = userName;
		this.email = email;
		this.passwd = getPasswd();
		this.firstName = firstName;
		this.lastName = lastName;
		this.createdDate = getCreatedDate();
		this.status = status;
		this.roles = roles;
	}

	public User(UserView createdBy) {
		this.id = createdBy.getId();
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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.passwd = encoder.encode(passwd);
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

	public void setCreatedDate() {
		this.createdDate = LocalDateTime.now();
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
	
	public String[] getRolesName() {
		String[] roleNames = getNamesFromRoles();
		return roleNames;
	}
	
	public void setRoles(ArrayList<Role> roles) {
		this.roles = roles;
	}

	private String[] getNamesFromRoles() {
		
		String[] roleNames = new String[roles.size()];
		int i = 0;

		for (Role role : roles) {
			roleNames[i++] = role.getRoleName();
		}
		
		return roleNames;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", passwd=" + passwd + ", firstName="
				+ firstName + ", lastName=" + lastName + ", createdDate=" + createdDate + ", status=" + status
				+ ", roles=" + roles + "]";
	}
		
}
