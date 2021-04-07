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

    public TypeDTO() {

    }

    public TypeDTO(long id, String typeName, String entityName, Visibility visibility) {
        this.typeName = typeName;
        this.entityName = entityName;
        this.id = id;
        this.visibility = visibility;
    }

    public TypeDTO(String typeName, String entityName) {
        this.typeName = typeName;
        this.entityName = entityName;
    }

    public TypeDTO(Type type) {
        this.entityName = type.getEntityName();
        this.id = type.getId();
        this.typeName = type.getTypeName();
        this.visibility = type.getVisibility();
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



}
