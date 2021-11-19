package com.capeelectric.exception;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public class AirTerminationException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	private String message;

	public AirTerminationException() {
		
	}

	public AirTerminationException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
		
	
}
