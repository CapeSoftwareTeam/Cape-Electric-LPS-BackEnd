/**
 * 
 */
package com.capeelectric.exception;

/**
 * @author CAPE-SOFTWARE
 *
 */
public class SummaryLpsException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	private String message;

	public SummaryLpsException() {

	}

	public SummaryLpsException(String message) {
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
