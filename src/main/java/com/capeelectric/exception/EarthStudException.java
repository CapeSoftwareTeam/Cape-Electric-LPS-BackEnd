package com.capeelectric.exception;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public class EarthStudException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	private String message;

	public EarthStudException() {

	}

	public EarthStudException(String message) {
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
