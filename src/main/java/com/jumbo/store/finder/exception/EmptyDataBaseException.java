package com.jumbo.store.finder.exception;

/**
 * Empty Data Base exception.
 */
public class EmptyDataBaseException extends RuntimeException {

    /**
     * Create an Empty Data Base exception.
     *
     * @param message is the message for the exception.
     */
    public EmptyDataBaseException(String message){
        super(message);
    }

    /**
     * Create an Empty Data Base exception.
     *
     * @param message is the message for the exception.
     * @param e       is an exception to be used as cause for Empty Data Base exception.
     */
    public EmptyDataBaseException(String message, Exception e){
        super(message,e);
    }
}