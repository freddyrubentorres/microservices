package com.ms.client_person.exception;

/**
 * @author : Freddy Torres
 * file :  ClientNotFoundException
 * @since : 9/3/2025, dom
 **/

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}