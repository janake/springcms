package org.prodet.repository.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class ContentEntityItem {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	@Column(unique=true)
	@Length(min=3, max=50)
	private String entityName;

	private String textCss;
	
	public ContentEntityItem() {
	}

	public ContentEntityItem(String entityName) {
		super();
		this.entityName = entityName;
	}

	public ContentEntityItem(long id, String entityName) {
		super();
		this.id = id;
		this.entityName = entityName;
	}

	public ContentEntityItem(String entityName, String textCss) {
		this.entityName = entityName;
		this.setTextCss(textCss);
	}

	public ContentEntityItem(long id, String entityName, String textCss) {
		this.id = id;
		this.entityName = entityName;
		this.setTextCss(textCss);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getTextCss() {
		return textCss;
	}

	public void setTextCss(String textCss) {
		this.textCss = textCss;
	}

}
