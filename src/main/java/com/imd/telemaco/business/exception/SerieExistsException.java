/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.business.exception;

/**
 *
 * @author valmir
 */

public class SerieExistsException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Default Constructor
     */
    public SerieExistsException () {
            super ();
    }
    
    /**
     * 
     * @param message 
     */
    public SerieExistsException (String message) {
            super (message);
    }
	
    /**
     *
     * @param message
     * @param cause
     */
    public SerieExistsException (String message, Throwable cause) {
		super (message, cause);
	}
}
