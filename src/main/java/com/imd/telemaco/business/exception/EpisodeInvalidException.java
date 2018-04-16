package com.imd.telemaco.business.exception;

public class EpisodeInvalidException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public EpisodeInvalidException () {
		super ();
	}
	
	public EpisodeInvalidException (String message) {
		super (message);
	}
	
	public EpisodeInvalidException (String message, Throwable cause) {
		super (message, cause);
	}
}
