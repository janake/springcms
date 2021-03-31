package org.prodet.repository.dao;

import org.prodet.service.dto.TypeDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Type {

	@Id
	@GeneratedValue
	private long id;

	@Column(unique=true)
	private String typeName;

	private String entityName;

	public Type() {
	}

	public Type(long id, String typeName, String entityName) {
		this.typeName = typeName;
		this.entityName = entityName;
		this.id = id;
	}

	public Type(String typeName, String entityName) {
		this.typeName = typeName;
		this.entityName = entityName;
	}

    public Type(TypeDTO typeDTO) {
		this.typeName = typeDTO.getTypeName();
		this.id = typeDTO.getId();
		this.entityName = typeDTO.getEntityName();
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

}
