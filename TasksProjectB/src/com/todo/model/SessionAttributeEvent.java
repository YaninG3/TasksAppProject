package com.todo.model;

import javax.persistence.*;

@Entity
@Table(name = "sessionattributeevent")
public class SessionAttributeEvent {

	private Integer id;
	private String sessionId;
	private String description;
	private String time;

	public SessionAttributeEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SessionAttributeEvent(String sessionId, String description, String time) {
		this();
		this.sessionId = sessionId;
		this.description = description;
		this.time = time;
	}
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
