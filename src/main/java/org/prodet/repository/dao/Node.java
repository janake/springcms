package org.prodet.repository.dao;

import org.prodet.service.NodeInterface;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Node implements NodeInterface {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;

	@OneToOne
	@JoinColumn(name = "Type", referencedColumnName = "id")
	private Type type;
	
	@Lob
	private String body;
	
	@OneToOne
	private User createdBy;
	
	private LocalDateTime lastModificationDate;

	private LocalDateTime createdDate;

	@Enumerated(EnumType.STRING)
	private Visibility visibility;
	
	public Node() {

	}

	public Node(Long id, String title, String body, User createdBy,
			LocalDateTime lastModificationDate, Type type, Visibility visibility) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.createdBy = createdBy;
		setCreatedDate();
		this.lastModificationDate = lastModificationDate;
		this.type = type;
		this.visibility = visibility;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate() {
		this.createdDate = LocalDateTime.now();
	}

	public LocalDateTime getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(LocalDateTime lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

}
