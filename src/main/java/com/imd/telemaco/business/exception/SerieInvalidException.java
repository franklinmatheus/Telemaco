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
public class SerieInvalidException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Default Constructor
     */
    public SerieInvalidException() {
        super ();
    }

    /**
     * 
     * @param message 
     */
    public SerieInvalidException(String message) {
        super (message);
    }

    /**
     * 
     * @param message
     * @param cause 
     */
    public SerieInvalidException(String message, Throwable cause) {
        super (message, cause);
    }
    
}
