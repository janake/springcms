package org.prodet.service.dto;

import org.prodet.repository.dao.Type;

public class TypeDTO {

    private long id;

    private String typeName;

    private String entityName;

    public TypeDTO() {

    }

    public TypeDTO(long id, String typeName, String entityName) {
        this.typeName = typeName;
        this.entityName = entityName;
        this.id = id;
    }

    public TypeDTO(String typeName, String entityName) {
        this.typeName = typeName;
        this.entityName = entityName;
    }

    public TypeDTO(Type type) {
        this.entityName = type.getEntityName();
        this.id = type.getId();
        this.typeName = type.getTypeName();
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
