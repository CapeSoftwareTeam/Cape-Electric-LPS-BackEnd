package com.capeelectric.exception;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public class EarthingLpsException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	private String message;

	public EarthingLpsException() {

	}

	public EarthingLpsException(String message) {
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
