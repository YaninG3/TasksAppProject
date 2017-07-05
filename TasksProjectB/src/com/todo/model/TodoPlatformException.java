 package com.todo.model;

public class TodoPlatformException extends Exception{

	/**
	 * an exception that is used by the {@link HibernateToDoListDAO} 
	 */
	private static final long serialVersionUID = 1L;
	public TodoPlatformException(String msg) {
		super(msg);
	}
	public TodoPlatformException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
