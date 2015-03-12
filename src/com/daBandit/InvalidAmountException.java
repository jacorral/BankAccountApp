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
public class InvalidAmountException extends Exception {

    /**
     * Creates a new instance of <code>InvalidAmount</code> without detail
     * message.
     */
    public InvalidAmountException() {
    }

    /**
     * Constructs an instance of <code>InvalidAmount</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public InvalidAmountException(String msg) {
        super(msg);
    }
}
