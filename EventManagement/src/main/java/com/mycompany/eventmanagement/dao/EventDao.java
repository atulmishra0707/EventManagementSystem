package com.mycompany.eventmanagement.dao;

import java.io.IOException;
import java.util.List;

import com.mycompany.eventmanagement.model.Event;

public interface EventDao extends BaseDao{

	List<Event> getAllEvents() throws IOException;

	void delete(Long eventId) throws IOException;

	Event getEvent(Long eventId) throws IOException;

	List<Event> getEventById(List<Long> events);

}
