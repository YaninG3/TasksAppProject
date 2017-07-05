package com.todo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.todo.model.HibernateSessionEventsDAO;
import com.todo.model.ISessionEventsDAO;
import com.todo.model.SessionAttributeEvent;
import com.todo.model.SessionEvent;
import com.todo.model.SessionsEventsDaoException;

/**
 * Application Lifecycle Listener implementation class ListenerLogger
 *
 */
@WebListener
public class SessionListener implements HttpSessionAttributeListener, HttpSessionListener {

	private ISessionEventsDAO dao;
	
	public SessionListener() {
		super();
		dao = HibernateSessionEventsDAO.getInstance();
	}
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		 System.out.println("Session attribute added::{"+hsbe.getName()+","+hsbe.getValue()+"}");
		 String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		 SessionAttributeEvent event = new SessionAttributeEvent(hsbe.getSession().getId(), "Session attribute [" + hsbe.getName() + "] was added", timeStamp);
		 try {
			dao.addSessionAttributeEvent(event);
		} catch (SessionsEventsDaoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent hsbe) {
		System.out.println("Session attribute removed::{"+hsbe.getName()+","+hsbe.getValue()+"}");
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		 SessionAttributeEvent event = new SessionAttributeEvent(hsbe.getSession().getId(), "Session attribute [" + hsbe.getName() + "] was removed", timeStamp);
		 try {
			dao.addSessionAttributeEvent(event);
		} catch (SessionsEventsDaoException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent hsbe) {
		System.out.println("Session attribute removed::{"+hsbe.getName()+","+hsbe.getValue()+"}");
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		 SessionAttributeEvent event = new SessionAttributeEvent(hsbe.getSession().getId(), "Session attribute [" + hsbe.getName() + "] was replaced", timeStamp);
		 try {
			dao.addSessionAttributeEvent(event);
		} catch (SessionsEventsDaoException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		System.out.println("Session created::{"+hse.getSession().getId()+","+new Date(hse.getSession().getCreationTime())+"}");
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		SessionEvent event = new SessionEvent(hse.getSession().getId(), "Session created", timeStamp);
		try {
			dao.addSessionEvent(event);
		} catch (SessionsEventsDaoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		System.out.println("Session destroyed::{"+hse.getSession().getId()+","+new Date(hse.getSession().getCreationTime())+"}");
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		SessionEvent event = new SessionEvent(hse.getSession().getId(), "Session destroyed", timeStamp);
		try {
			dao.addSessionEvent(event);
		} catch (SessionsEventsDaoException e) {
			e.printStackTrace();
		}
	}
}
