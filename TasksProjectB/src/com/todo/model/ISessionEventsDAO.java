package com.todo.model;

import java.util.List;

/**
 * ISessionEventsDAO is an interface that describes which actions can be performed with the database
 *
 */
public interface ISessionEventsDAO {
	
	/**
	 * Will receive an SessionAttributeEvent object and add it to the database
	 * @param event
	 * @return Integer id
	 * @throws SessionsEventsDaoException
	 */
	public Integer addSessionAttributeEvent(SessionAttributeEvent event) throws SessionsEventsDaoException;
	
	/**
	 * Will reset the entire sessionAttributeEvent table
	 * @return Boolean true/false
	 * @throws SessionsEventsDaoException
	 */
	public Boolean deleteAllSessionAttributeEvents() throws SessionsEventsDaoException;
	
	/**
	 * Will read the entire sessionAttributeEvents table and return it as object list
	 * @return List<SessionAttributeEvent>
	 * @throws SessionsEventsDaoException
	 */
	public List<SessionAttributeEvent> getAllSessionAttributeEvents() throws SessionsEventsDaoException;
	
	
	/**
	 * Will receive a SessionEvent object and add it to the database
	 * @param event
	 * @return Integer id
	 * @throws SessionsEventsDaoException
	 */
	public String addSessionEvent(SessionEvent event) throws SessionsEventsDaoException;
	
	/**
	 * Will reset the entire sessionEvent table
	 * @return Boolean true/false
	 * @throws SessionsEventsDaoException
	 */
	public Boolean deleteAllSessionEvents() throws SessionsEventsDaoException;
	
	/**
	 * Will read the entire sessionEvents table and return it as object list
	 * @return List<SessionEvent>
	 * @throws SessionsEventsDaoException
	 */
	public List<SessionAttributeEvent> getAllSessionEvents() throws SessionsEventsDaoException;
}
