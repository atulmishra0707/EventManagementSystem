package com.mycompany.eventmanagement.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.eventmanagement.model.BasePO;
import com.mycompany.eventmanagement.model.Employee;
import com.mycompany.eventmanagement.model.Event;

public class EventDaoImpl implements EventDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void insert(BasePO obj) throws IOException {
		Session session=null;
		try{
			session = this.sessionFactory.openSession();		
			session.save(obj);
			session.flush();
		}finally{
			if(session !=null) 	session.close();
		}
		
	}

	@Override
	public void delete(BasePO obj) throws IOException{
		Session session=null;
		try{
			session = this.sessionFactory.openSession();		
			 session.delete(obj);
			 session.flush();
		}finally{
			if(session !=null) 	session.close();
		}
	}

	@Override
	public List<Event> getAllEvents() throws IOException{
		List<Event> events=null;
		Session session=null;
		try{
			session = this.sessionFactory.openSession();		
			 events= session.createQuery("from Event").list();
		}finally{
			if(session !=null) 	session.close();
		}
		return events;
	}

	@Override
	public void update(BasePO obj) throws IOException{
		Session session=null;
		try{
			session = this.sessionFactory.openSession();		
			session.update(obj);
			session.flush();
			}finally{
				if(session !=null) 	session.close();
			}
		
	}
	
	@Override
	public void delete(Long eventId) throws IOException{
		Session session=null;
		try{
			session = this.sessionFactory.openSession();	
			Event event = (Event)session.load(Event.class,eventId);
		    
			 session.delete(event);
			 session.flush();
		}finally{
			if(session !=null) 	session.close();
		}
	}
	
	
	@Override
	public Event getEvent(Long eventId) throws IOException{
		Session session=null;
		try{
			session = this.sessionFactory.openSession();				
			Criteria cr = session.createCriteria(Event.class);
			cr.add(Restrictions.eq("eventId", eventId));
			cr.setFetchMode("employees", FetchMode.JOIN);
			List<Event> response= cr.list();
			if(response != null && !response.isEmpty()) return response.get(0);
		}finally{
			if(session !=null) 	session.close();
		}
		return null;
	}

	@Override
	public List<Event> getEventById(List<Long> events) {
		Session session=null;
		try{
			session = this.sessionFactory.openSession();				
			Criteria cr = session.createCriteria(Event.class);
			cr.add(Restrictions.in("eventId", events));
			List<Event> response= cr.list();
			return response;
		}finally{
			if(session !=null) 	session.close();
		}
	}

}
