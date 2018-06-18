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
public class RatingInvalidException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Default Constructor
     */
    public RatingInvalidException () {
        super ();
    }

    public RatingInvalidException (String message) {
        super (message);
    }

    public RatingInvalidException (String message, Throwable cause) {
        super (message, cause);
    }
}

