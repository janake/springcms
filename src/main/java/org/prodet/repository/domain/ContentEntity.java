package org.prodet.repository.domain;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class ContentEntity {

	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	@Column(unique=true)
	@Length(min=3, max=20)
	private String name;
	
	@NotNull
	@OneToMany
	@MapKeyColumn(name="id")
	Map<String, ContentEntityItem> contentEntityItem;
	
	public ContentEntity() {
	}

	public ContentEntity(String name, Map<String, ContentEntityItem> contentEntityItem) {
		super();
		this.name = name;
		this.contentEntityItem = contentEntityItem;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, ContentEntityItem> getContentEntityItem() {
		return contentEntityItem;
	}

	public void setContentEntityItem(Map<String, ContentEntityItem> contentEntityItem) {
		this.contentEntityItem = contentEntityItem;
	}
	
}
