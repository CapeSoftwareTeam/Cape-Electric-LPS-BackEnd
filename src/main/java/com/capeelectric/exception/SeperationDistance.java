package com.capeelectric.exception;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public class SeperationDistance extends Throwable {
	
	private static final long serialVersionUID = 1L;

	private String message;

	public SeperationDistance() {

	}

	public SeperationDistance(String message) {
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
