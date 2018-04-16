package com.imd.telemaco.business.exception;

public class EpisodeExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public EpisodeExistsException () {
		super ();
	}
	
	public EpisodeExistsException (String message) {
		super (message);
	}
	
	public EpisodeExistsException (String message, Throwable cause) {
		super (message, cause);
	}
}