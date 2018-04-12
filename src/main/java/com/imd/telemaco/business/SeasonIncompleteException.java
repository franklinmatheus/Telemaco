package com.imd.telemaco.business;

public class SeasonIncompleteException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public SeasonIncompleteException () {
		super ();
	}
	
	public SeasonIncompleteException (String message) {
		super (message);
	}
	
	public SeasonIncompleteException (String message, Throwable cause) {
		super (message, cause);
	}
}
