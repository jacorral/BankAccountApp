/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daBandit;

/**
 *
 * @author angel
 */
public class InsufficientFundsException extends Exception {

    /**
     * Creates a new instance of <code>InsufficientFundsExceptions</code>
     * without detail message.
     */
    public InsufficientFundsException() {
    }

    /**
     * Constructs an instance of <code>InsufficientFundsExceptions</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InsufficientFundsException(String msg) {
        super(msg);
    }
}
