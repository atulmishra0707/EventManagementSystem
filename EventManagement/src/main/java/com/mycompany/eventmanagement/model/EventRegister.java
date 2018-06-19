package com.mycompany.eventmanagement.model;

import java.util.List;

import com.mycompany.eventmanagement.EventManagementException;

public class EventRegister {
	
	private Long mId;
	private List<Long> events;
	
	public Long getmId() {
		return mId;
	}
	public void setmId(Long mId) {
		this.mId = mId;
	}
	public List<Long> getEvents() {
		return events;
	}
	public void setEvents(List<Long> events) {
		this.events = events;
	}
	public static void validate(EventRegister obj) throws EventManagementException {
		if(obj != null && obj.getmId() < 0){
			throw new EventManagementException("Invalid Employee got selected");
		}
		if(obj != null && obj.getEvents().size() > 0){
			for (Long event : obj.getEvents()) {
    			if(event != null && event < 0){
					throw new EventManagementException("Invalid Event got selected");
				}
			}
		}
	}

}
