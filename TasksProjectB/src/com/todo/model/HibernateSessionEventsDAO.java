package com.todo.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateSessionEventsDAO implements ISessionEventsDAO{
	private static HibernateSessionEventsDAO instance = null;
	private SessionFactory factory;
	
	/**
	 * private constructor that accept no values.
	 * for singleton design pattern
	 */
	private HibernateSessionEventsDAO() {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}
	
	/**
	 * singleton design pattern
	 * will create one instance of HibernateSessionEventsDAO by calling the private constructor
	 * @return HibernateSessionEventsDAO
	 */
	public static HibernateSessionEventsDAO getInstance(){
		if (instance == null){
			instance = new HibernateSessionEventsDAO();
		}
		return instance;
	}
	
	/**
	 * take single SessionAttributeEvent object and adds it as a record to the database using Hibernate framework
	 */
	@Override
	public Integer addSessionAttributeEvent(SessionAttributeEvent event) throws SessionsEventsDaoException {
		
		Session session = factory.openSession();
		
		try
		{
			session.beginTransaction();
			session.save(event);
			session.getTransaction().commit();
		}
		catch ( HibernateException e )
{
			if ( session.getTransaction() != null )
			session.getTransaction().rollback();
			throw new SessionsEventsDaoException(e.getMessage(),e);
		}
		finally
		{
			try
			{
				session.close();
			}
			catch( HibernateException e )
			{
				if ( session.getTransaction() != null )
					session.getTransaction().rollback();
				throw new SessionsEventsDaoException(e.getMessage(),e);
			}
		}
		
		return event.getId();
		
	}

	/**
	 * will delete all records from SessionAttributeEvent table using Hibernate framework
	 */
	@Override
	public Boolean deleteAllSessionAttributeEvents() throws SessionsEventsDaoException {
		Session session = factory.openSession();
		
		try
		{
			Query query = session.createQuery("DELETE FROM sessionattributeevent");
			int result = query.executeUpdate();
			
			if (result > 0){
			    return true;
			}
			else
				return false;
			
		}
		catch ( HibernateException e )
		{
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
			throw new SessionsEventsDaoException(e.getMessage(),e);
		}
		finally
		{
			try
			{
				session.close();
			}
			catch( HibernateException e )
			{
				if ( session.getTransaction() != null )
					session.getTransaction().rollback();
				throw new SessionsEventsDaoException(e.getMessage(),e);
			}
		}
	}

	/**
	 * will return a list of SessionAttributeEvent objects from "SessionAttributeEvent" table database using Hibernate framework
	 */
	@Override
	public List<SessionAttributeEvent> getAllSessionAttributeEvents() throws SessionsEventsDaoException {
		
		Session session = factory.openSession();
		
		try
		{
			Query query = session.createQuery("FROM sessionattributeevent");
			@SuppressWarnings("unchecked")
			List<SessionAttributeEvent> eventList = query.list();
			return eventList;
		}
		catch ( HibernateException e )
		{
			throw new SessionsEventsDaoException(e.getMessage(),e);
		}
		finally
		{
			try
			{
				session.close();
			}
			catch( HibernateException e )
			{
				if ( session.getTransaction() != null )
					session.getTransaction().rollback();
				throw new SessionsEventsDaoException(e.getMessage(),e);
			}
		}
	}

	/**
	 * take a single SessionEvent object and adds it as a record to the database using Hibernate framework
	 */
	@Override
	public String addSessionEvent(SessionEvent event) throws SessionsEventsDaoException {
		
		Session session = factory.openSession();
		
		try
		{
			session.beginTransaction();
			session.save(event);
			session.getTransaction().commit();
		}
		catch ( HibernateException e )
{
			if ( session.getTransaction() != null )
			session.getTransaction().rollback();
			throw new SessionsEventsDaoException(e.getMessage(),e);
		}
		finally
		{
			try
			{
				session.close();
			}
			catch( HibernateException e )
			{
				if ( session.getTransaction() != null )
					session.getTransaction().rollback();
				throw new SessionsEventsDaoException(e.getMessage(),e);
			}
		}
		
		return event.getId();
		
	}

	/**
	 * will delete all records from SessionEvent table using Hibernate framework
	 */
	@Override
	public Boolean deleteAllSessionEvents() throws SessionsEventsDaoException {
		Session session = factory.openSession();
		
		try
		{
			Query query = session.createQuery("DELETE FROM sessionevent");
			int result = query.executeUpdate();
			
			if (result > 0){
			    return true;
			}
			else
				return false;
			
		}
		catch ( HibernateException e )
		{
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
			throw new SessionsEventsDaoException(e.getMessage(),e);
		}
		finally
		{
			try
			{
				session.close();
			}
			catch( HibernateException e )
			{
				if ( session.getTransaction() != null )
					session.getTransaction().rollback();
				throw new SessionsEventsDaoException(e.getMessage(),e);
			}
		}
	}

	/**
	 * will return a list of SessionEvent objects from "SessionAttributeEvent" table database using Hibernate framework
	 */
	@Override
	public List<SessionAttributeEvent> getAllSessionEvents() throws SessionsEventsDaoException {
		
		Session session = factory.openSession();
		
		try
		{
			Query query = session.createQuery("FROM sessionevent");
			@SuppressWarnings("unchecked")
			List<SessionAttributeEvent> eventList = query.list();
			return eventList;
		}
		catch ( HibernateException e )
		{
			throw new SessionsEventsDaoException(e.getMessage(),e);
		}
		finally
		{
			try
			{
				session.close();
			}
			catch( HibernateException e )
			{
				if ( session.getTransaction() != null )
					session.getTransaction().rollback();
				throw new SessionsEventsDaoException(e.getMessage(),e);
			}
		}
	}

}
