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
public class ConfirmInputsException extends Exception {

    /**
     * Creates a new instance of <code>InputsConfirmException</code> without
     * detail message.
     */
    public ConfirmInputsException() {
        super();
    }

    /**
     * Constructs an instance of <code>InputsConfirmException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ConfirmInputsException(String msg) {
        super(msg);
    }
}
