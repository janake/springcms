package org.prodet.repository.dao;

import org.prodet.service.dto.TypeDTO;

import javax.persistence.*;

@Entity
public class Type {

	@Id
	@GeneratedValue
	private long id;

	@Column(unique=true)
	private String typeName;

	private String entityName;

	@Enumerated(EnumType.STRING)
	private Visibility visibility;

	@OneToOne
	private User createdBy;

	public Type() {
	}

	public Type(long id, String typeName, String entityName, User user) {
		this.typeName = typeName;
		this.entityName = entityName;
		this.id = id;
		this.createdBy = user;
	}

	public Type(String typeName, String entityName, Visibility visibility, User user) {
		this.typeName = typeName;
		this.entityName = entityName;
		this.visibility = visibility;
		this.createdBy = user;
	}

	public Type(String typeName, String entityName, User user) {
		this.typeName = typeName;
		this.entityName = entityName;
		this.createdBy = user;
	}

    public Type(TypeDTO typeDTO) {
		this.typeName = typeDTO.getTypeName();
		this.id = typeDTO.getId();
		this.entityName = typeDTO.getEntityName();
		this.visibility = typeDTO.getVisibility();
		this.createdBy = new User(typeDTO.getCreatedBy());
    }

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}


	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}


}
