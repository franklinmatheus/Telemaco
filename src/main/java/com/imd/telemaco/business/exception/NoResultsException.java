/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.business.exception;

/**
 *
 * @author franklin
 */
public class NoResultsException extends Exception {

    /**
     * Creates a new instance of <code>NoResultsException</code> without detail
     * message.
     */
    public NoResultsException() {
        super();
    }

    /**
     * Constructs an instance of <code>NoResultsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoResultsException(String msg) {
        super(msg);
    }
}
