package org.prodet.repository.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Node {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	
	@Lob
	private String body;
	
	private Long user;
	
	private LocalDateTime lastModificationDate;

	private LocalDateTime createdDate;
	
	public Node() {

	}

	public Node(Long id, String title, String body, Long user, LocalDateTime createdDate,
			LocalDateTime lastModificationDate) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.user = user;
		this.createdDate = createdDate;
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

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public LocalDateTime getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = LocalDateTime.now();
	}

	public LocalDateTime getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(LocalDateTime lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

}
