package com.imd.telemaco.business.exception;

public class SeasonExistsException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public SeasonExistsException () {
		super ();
	}
	
	public SeasonExistsException (String message) {
		super (message);
	}
	
	public SeasonExistsException (String message, Throwable cause) {
		super (message, cause);
	}
}
