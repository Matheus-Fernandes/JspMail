/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.email.exception;

/**
 *
 * @author User
 */
public class InvalidPageException extends Exception {

    public InvalidPageException(int page) {
        super("Invalid email page (" + page + ").");
    }
    
}
