package org.prodet.service.dto;

import org.prodet.repository.dao.Node;
import org.prodet.repository.dao.Visibility;
import org.prodet.service.NodeInterface;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NodeDTO implements NodeInterface {
	
	@NotNull
    @Size(min=3, max=200)
	private String title;
	
	private Long id;
	
	private String body;
	
	private UserDTO createdBy;

	private TypeDTO type;

	private Visibility visibility;

	public NodeDTO() {
		
	}

	public NodeDTO(Node node) {
		this.id = node.getId();
		this.title = node.getTitle();
		this.body = node.getBody();
		this.createdBy = new UserDTO(node.getCreatedBy());
		this.type = new TypeDTO(node.getType());
		this.visibility = node.getVisibility();
	}

	public NodeDTO getNode() {
		return this;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreatedBy(UserDTO user) {
		this.createdBy = user;
	}
	
	@Override
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
	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	@Override
	public String toString() {
		return "NodeView [title=" + title + ", id=" + id + ", body=" + body + ", createdBy=" + createdBy + "]";
	}

}
