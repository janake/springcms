package org.prodet.service.dao;

import org.prodet.repository.domain.Type;

public class TypeView {

    private long id;

    private String typeName;

    private String entityName;

    public TypeView () {

    }

    public TypeView(long id, String typeName, String entityName) {
        this.typeName = typeName;
        this.entityName = entityName;
        this.id = id;
    }

    public TypeView(String typeName, String entityName) {
        this.typeName = typeName;
        this.entityName = entityName;
    }

    public TypeView(Type type) {
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
