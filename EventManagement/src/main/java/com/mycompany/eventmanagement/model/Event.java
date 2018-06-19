package com.mycompany.eventmanagement.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.mycompany.eventmanagement.EventManagementException;


@Entity
@Table(name="event")
public class Event implements BasePO{

    private Long eventId;
	
	private String eventTitle;
	
	private String description;
	
	private Set<Employee> employees = new HashSet<Employee>(0);
	
	
	@Column(name="EVENT_TITLE")
	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "EVENT_ID", unique = true, nullable = false)
	public Long getEventId() {
		return this.eventId;
	}
	
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "events")
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Event && ((Event) obj).eventId == this.eventId) return true;
		
		return false;
	}
	
	@Override
	public String toString(){
		
		StringBuilder eventStr=new StringBuilder();
		eventStr.append("Event ID:");
		eventStr.append(this.eventId);
		eventStr.append(" ");
		eventStr.append("Event Title ");
		eventStr.append(this.eventTitle);
		eventStr.append(" ");
		eventStr.append("Event Description: ");
		eventStr.append(this.description);
		return eventStr.toString();
	}
	
	public int hashCode(){
		return eventId.intValue();
	}

	public static void validate(Event obj) throws EventManagementException {
		Pattern p = Pattern.compile("[^A-Za-z0-9 ]");
	    Matcher m = null;
	      
		if(obj != null && obj.getEventId() != null &&  obj.getEventId() < 0){
			throw new EventManagementException("Invalid Event got selected");
		}
		if(obj != null && obj.getDescription() != null ){
			m = p.matcher(obj.getDescription());
			boolean b = m.find();
			if (b == true)
				throw new EventManagementException("There is a special character in Event Description, only allowed value A-Za-z0-9 ");
		}
		if(obj != null && obj.getEventTitle() != null){
			m = p.matcher(obj.getEventTitle());
			boolean b = m.find();
			if (b == true)
				throw new EventManagementException("There is a special character in Event Title, only allowed value A-Za-z0-9 ");
		}
		
	}
}
