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
public class UserNotExistsException extends Exception {

    /**
     * Creates a new instance of <code>UserNotExistsException</code> without
     * detail message.
     */
    public UserNotExistsException() {
        super();
    }

    /**
     * Constructs an instance of <code>UserNotExistsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserNotExistsException(String msg) {
        super(msg);
    }
}
