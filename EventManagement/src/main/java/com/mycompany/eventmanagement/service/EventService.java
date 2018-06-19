package com.mycompany.eventmanagement.service;

import java.util.List;
import com.mycompany.eventmanagement.EventManagementException;
import com.mycompany.eventmanagement.model.Event;

public interface EventService {

	void addEvent(Event event) throws EventManagementException;

	void deleteEvent(Long eventId) throws EventManagementException;
	
	void updateEvent(Event event) throws EventManagementException;
	
	List<Event> getAllEvents();

	Event getEvent(Long eventId) throws EventManagementException;

	List<Event> getEvents(List<Long> events);

}
