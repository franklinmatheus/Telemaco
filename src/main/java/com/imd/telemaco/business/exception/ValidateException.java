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
public class ValidateException extends Exception {

    /**
     * Creates a new instance of <code>ValidateException</code> without detail
     * message.
     */
    public ValidateException() {
        super();
    }

    /**
     * Constructs an instance of <code>ValidateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ValidateException(String msg) {
        super(msg);
    }
}
