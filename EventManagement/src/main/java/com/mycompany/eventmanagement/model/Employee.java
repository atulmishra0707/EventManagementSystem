package com.mycompany.eventmanagement.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mycompany.eventmanagement.EventManagementException;

import javax.persistence.JoinColumn;


@Entity
@Table(name="employee")
public class Employee implements BasePO{

	private Long mId;
	
	private String name;
	
	private Date joinDate;
	
	private String emailId;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private Set<Event> events = new HashSet<Event>(0);
	
	@Transient
	private String date;
	

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="JOIN_DATE")
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	@Column(name="EMAIL_ID")
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Id
	@Column(name = "MID", unique = true, nullable = false)
	public Long getMId() {
		return this.mId;
	}
	
	
	public void setMId(Long mId) {
		this.mId = mId;
	}
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "employee_event", catalog = "mydb", joinColumns = { 
			@JoinColumn(name = "MID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "EVENT_ID", 
					nullable = false, updatable = false) })
	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	
	@Override
	public boolean equals(Object emp){
		
		if(emp instanceof Employee && ((Employee) emp).mId == this.mId) return true;
		return false;
		
	}
	
	@Override
	public String toString(){
		
		StringBuilder employeeStr= new StringBuilder();
		employeeStr.append("Emp ID: ") ;
		employeeStr.append(this.mId);
		employeeStr.append(" ");
		employeeStr.append("Name: ");
		employeeStr.append(" ");
		employeeStr.append(this.name);
		employeeStr.append(" ");
		employeeStr.append("Join Date: ");
		employeeStr.append(this.joinDate);
		employeeStr.append(" ");
		employeeStr.append("Email Id:");
		employeeStr.append(this.emailId);
		return employeeStr.toString();
	}
	
	public int hashcode(){
		return mId.intValue();
	}

	public static void validate(Employee obj) throws EventManagementException {
		Pattern p = Pattern.compile("[^A-Za-z0-9 ]");
	    Matcher m = null;
	      
		if(obj != null && obj.getMId() != null &&  obj.getMId() < 0){
			throw new EventManagementException("Invalid Employee Id entered.");
		}
		if(obj != null && obj.getName() != null ){
			m = p.matcher(obj.getName());
			boolean b = m.find();
			if (b == true)
				throw new EventManagementException("There is a special character in Employee Name, only allowed value A-Za-z0-9 ");
		}
		if(obj != null && obj.getEmailId() != null){
			p =  Pattern.compile("[^A-Za-z0-9+_.-@]", Pattern.CASE_INSENSITIVE);
			m = p.matcher(obj.getEmailId());
			boolean b = m.find();
			if (b == true)
				throw new EventManagementException("There is a special character in Employee email address, only allowed value A-Za-z0-9 Additionally email may contain only dot(.), dash(-) and underscore(_)");
		}
	}
	
}
