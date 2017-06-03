package org.prodet.repository.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true)
	private String roleName;

	public Role() {
	}
	
	public Role(long id, String roleName) {
		super();
		this.id = id;
		setRoleName(roleName);
	}

	public Role(String roleName) {
		this.roleName = roleName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = "ROLE_" + roleName.toUpperCase();
	}
	
}
