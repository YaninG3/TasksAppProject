package com.todo.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * the object describes a single session event
 * such as "session created" or "session destroyed" 
 *
 */
@Entity
@Table(name = "sessionevent")
public class SessionEvent implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private String id;
	private String description;
	private String time;
	
	public SessionEvent() {
		super();
	}
	
	public SessionEvent(String id, String description, String time) {
		this();
		this.id = id;
		this.description = description;
		this.time = time;
	}
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return time;
	}
	public void setDate(String time) {
		this.time = time;
	}
}
