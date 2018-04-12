package com.imd.telemaco.business;

public class EpisodeInvalidException extends RuntimeException {
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
