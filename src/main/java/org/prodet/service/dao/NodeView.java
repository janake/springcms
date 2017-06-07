package org.prodet.service.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NodeView {
	
	@NotNull
    @Size(min=3, max=200)
	private String title;
	
	private Long id;
	
	private String body;
	
	private UserView createdBy;
	
	public NodeView() {
		
	}

	public NodeView(String title, String body, UserView createdBy) {
		this.title = title;
		this.body = body;
		this.createdBy = createdBy;
	}

	public NodeView(Long id, String title, String body, UserView createdBy) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.createdBy = createdBy;
	}
	
	public static NodeView getNode() {
		return new NodeView();
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

	public void setCreatedBy(UserView user) {
		this.createdBy = user;
	}
	
	public UserView getCreatedBy() {
		return this.createdBy;
	}

	@Override
	public String toString() {
		return "NodeView [title=" + title + ", id=" + id + ", body=" + body + ", createdBy=" + createdBy + "]";
	}
	
}
