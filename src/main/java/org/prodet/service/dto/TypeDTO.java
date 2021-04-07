package org.prodet.service.dto;

import org.prodet.repository.dao.Type;
import org.prodet.repository.dao.Visibility;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class TypeDTO {

    private long id;

    private String typeName;

    private String entityName;

    private Visibility visibility;

    private UserDTO createdBy;

    public TypeDTO() {

    }

    public TypeDTO(long id, String typeName, String entityName, Visibility visibility, UserDTO createdBy) {
        this.typeName = typeName;
        this.entityName = entityName;
        this.id = id;
        this.visibility = visibility;
        this.createdBy = createdBy;
    }

    public TypeDTO(String typeName, String entityName, UserDTO createdBy) {
        this.typeName = typeName;
        this.entityName = entityName;
        this.createdBy = createdBy;
    }

    public TypeDTO(Type type) {
        this.entityName = type.getEntityName();
        this.id = type.getId();
        this.typeName = type.getTypeName();
        this.visibility = type.getVisibility();
        this.createdBy = new UserDTO(type.getCreatedBy());
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

    public UserDTO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDTO createdBy) {
        this.createdBy = createdBy;
    }


}
