package org.prodet.service.dto;

import org.prodet.repository.dao.Node;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NodeDTO {
	
	@NotNull
    @Size(min=3, max=200)
	private String title;
	
	private Long id;
	
	private String body;
	
	private UserDTO createdBy;

	private TypeDTO type;

	public NodeDTO() {
		
	}

	public NodeDTO(Node node) {
		this.id = node.getId();
		this.title = node.getTitle();
		this.body = node.getBody();
		this.createdBy = new UserDTO(node.getCreatedBy());
		this.type = new TypeDTO(node.getType());
	}

	public NodeDTO(Long id, String title, String body, UserDTO createdBy) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.createdBy = createdBy;
	}
	
	public NodeDTO getNode() {
		return this;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreatedBy(UserDTO user) {
		this.createdBy = user;
	}
	
	public UserDTO getCreatedBy() {
		return this.createdBy;
	}

	public void setType(TypeDTO type) {
		this.type = type;
	}

	public TypeDTO getType() {
		return type;
	}

	@Override
	public String toString() {
		return "NodeView [title=" + title + ", id=" + id + ", body=" + body + ", createdBy=" + createdBy + "]";
	}

}
