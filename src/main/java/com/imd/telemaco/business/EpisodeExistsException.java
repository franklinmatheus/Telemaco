package com.imd.telemaco.business;

public class EpisodeExistsException extends RuntimeException {
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