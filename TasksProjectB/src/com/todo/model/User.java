package com.todo.model;
import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	
	/** 
	 * Class constructor, receiving no values
	 */
	public User(){}
	
	/** 
	 * Class constructor. which receives values for setting
	 */
	public User(String first, String last, String username, String pass){
		//will use the default c'tor first
		this();
		this.setFirstName(first);
		this.setLastName(last);
		this.setUserName(username);
		this.setPassword(hashFunction(pass));
	}
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (isAlphaNumeric(firstName)){
			this.firstName = firstName;
		}
	}

	public String getLastName() {
			return lastName;
	}

	public void setLastName(String lastName) {
		if(isAlphaNumeric(lastName)){
			this.lastName = lastName;
		}
	}

	@Column(unique=true)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		if(isAlphaNumeric(userName)){
			this.userName = userName;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(isAlphaNumeric(password)){
			this.password = password;
		}
	}
	
	/**
	 * transform the given password string to a one-way hash string
	 * @param password
	 * @return hash String
	 */
	public static String hashFunction(String password){
		int hash = 0;
		for (int i = 0; i < password.length(); i++)
		    hash = (31 * hash + password.charAt(i));
		return String.valueOf(hash);
	}
	
    private Boolean isAlphaNumeric(String str)
    {
        for (int i=0; i<str.length(); i++)
        {
            char c = str.charAt(i);
            if (!Character.isDigit(c) && !Character.isLetter(c))
                return false;
        }

        return true;
    }

}
