package org.prodet.service.DAO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NodeDao {
	
	@NotNull
    @Size(min=3, max=200)
	private String title;
	
	private Long id;
	
	private String body;
	
	public NodeDao() {
		
	}

	public NodeDao(String title, String body) {
		this.title = title;
		this.body = body;
	}

	public NodeDao(Long id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
	
	public static NodeDao getNode() {
		return new NodeDao();
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
	
}
