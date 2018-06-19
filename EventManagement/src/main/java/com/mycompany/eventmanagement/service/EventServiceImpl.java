package com.mycompany.eventmanagement.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.eventmanagement.EventManagementException;
import com.mycompany.eventmanagement.dao.EventDao;
import com.mycompany.eventmanagement.model.Employee;
import com.mycompany.eventmanagement.model.Event;

@Component
public class EventServiceImpl implements EventService{ 

	@Autowired
	private EventDao eventDao;

	private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);


	public EventDao getEventDao() {
		return eventDao;
	}

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	@Override
	@Transactional
	public void addEvent(Event event) throws EventManagementException {
		try {
			eventDao.insert(event);
			logger.info("Added event " + event );
		} catch (IOException e) {
			logger.error("Error adding event " + event);
			e.printStackTrace();
			throw new EventManagementException("Error adding Event" ,e);
		}
	}

	@Override
	public List<Event> getAllEvents() {
		List<Event> events=null;
		try {
			events = eventDao.getAllEvents();
		} catch (IOException e) {
			logger.error("Error getting All Events");
			e.printStackTrace();
		}
		return events;
	}

	@Override
	public void deleteEvent(Long eventId) throws EventManagementException {
		try {
			eventDao.delete(eventId);
			logger.info("Delete event Successfully with ID :" + eventId);
		} catch (IOException e) {
			logger.error("Error deleting event " + eventId);
			e.printStackTrace();
			throw new EventManagementException("Error deleting Event" ,e);
		}
		
	}

	@Override
	public void updateEvent(Event event) throws EventManagementException {
		try {
			eventDao.update(event);
			logger.info("Update event " + event);
		} catch (IOException e) {
			logger.error("Error updating event " + event);
			e.printStackTrace();
			throw new EventManagementException("Error updating Event" ,e);
		}
		
	}
	
	
	@Override
	public Event getEvent(Long eventId) throws EventManagementException {
		try {
			Event event=eventDao.getEvent(eventId);
			return event;
			} catch (IOException e) 
			{
			logger.error("Error fetching event " + eventId);			
			e.printStackTrace();
			throw new EventManagementException("Error fetching event");
		}
		
	}

	@Override
	public List<Event> getEvents(List<Long> events) {
		List<Event> eventObjects= eventDao.getEventById(events);
		return eventObjects;
	}
}
