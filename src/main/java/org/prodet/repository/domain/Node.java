package org.prodet.repository.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.prodet.service.dao.NodeView;
import org.prodet.service.dao.UserView;

@Entity
public class Node {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	
	@Lob
	private String body;
	
	@OneToOne
	private User createdBy;
	
	private LocalDateTime lastModificationDate;

	private LocalDateTime createdDate;
	
	public Node() {

	}

	public Node(Long id, String title, String body, User createdBy,
			LocalDateTime lastModificationDate) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.createdBy = createdBy;
		setCreatedDate();
		this.lastModificationDate = lastModificationDate;
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

}
