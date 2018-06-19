package com.mycompany.eventmanagement;


public class EventManagementException extends Exception {

	private static final long serialVersionUID = 4152372170541508940L;

	/**
	 * 
	 */
	public EventManagementException() {
		super();
 	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public EventManagementException(String message, Throwable ex) {
		super(message, ex);
 	}

	/**
	 * @param arg0
	 */
	public EventManagementException(String message) {
		super(message);
 	}

	/**
	 * @param arg0
	 */
	public EventManagementException(Throwable ex) {
		super(ex);
 	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EventManagementException: ["
				+ getMessage() + "]";
	}
	
	
	
}

