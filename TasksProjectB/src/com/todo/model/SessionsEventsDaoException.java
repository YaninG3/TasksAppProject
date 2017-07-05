package com.todo.model;

public class SessionsEventsDaoException extends Exception{

	/**
	 * an exception that is used by the {@link HibernateSessionsDAO} 
	 */
	private static final long serialVersionUID = 1L;
	public SessionsEventsDaoException(String msg) {
		super(msg);
	}
	public SessionsEventsDaoException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
