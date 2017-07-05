package com.todo.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * 
 * TodoItem class represent one "To-Do" item for the tasksProject
 * a TodoItem holds a unique id for each object
 * all ToDo items are associated with a spacific User according their userId private member 
 * @author Jiminy Cricket
 *
 */
@Entity
@Table(name = "items")
public class TodoItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id = 0;
	private String subject;
	private String date;
	private Integer userId;
	
	/**
	 * Class constructor.
	 */
	public TodoItem(){}
	
	/**
	 * Class constructor. which receives values for setting
	 */
	public TodoItem(String subject, String date, Integer userId){
		//will use the empty values c'tor
		this();
		this.setSubject(subject);
		this.setDate(date);
		this.setUserId(userId);
	}
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		if(isAlphaNumericAndSpace(subject)){
			this.subject = subject;
		}
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		if(isAlphaNumericAndSpace(date)){
			this.date = date;
		}
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * returns plain text representation of the TodoItem object 
	 * @return String
	 */
	public String toString(){
		String str = new String("[" + getId().toString() + ", " + getSubject() + " ," + getDate() + ", " + getUserId().toString() + "]");
		return str;
	}
	
	/**
	 * returns HTML representation of the TodoItem object 
	 * @return String
	 */
	public String toHtmlString(){
		String str =  new String("<td>" + this.getSubject() + "</td><td>"+ this.getDate() +"</td>");
		return str;
	}
	
    /**
     * checks if the provided String contains only AlphaNumeric characters or/and space character
     * @param string
     * @return true/false
     */
    private Boolean isAlphaNumericAndSpace(String str)
    {
        for (int i=0; i<str.length(); i++)
        {
            char c = str.charAt(i);
            if (!Character.isDigit(c) && !Character.isLetter(c) && !Character.isSpaceChar(c))
                return false;
        }

        return true;
    }
}
