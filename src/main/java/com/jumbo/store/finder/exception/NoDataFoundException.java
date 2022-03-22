package com.jumbo.store.finder.exception;

/**
 * No Data Found exception
 */
public class NoDataFoundException extends RuntimeException {

    /**
     * Create a No Data Found exception.
     *
     * @param message is the message for the exception.
     */
    public NoDataFoundException(String message){
        super(message);
    }

    /**
     * Create a No Data Found exception.
     *
     * @param message is the message for the exception.
     * @param e       is an exception to be used as cause for No Data Found exception.
     */
    public NoDataFoundException(String message, Exception e){
        super(message,e);
    }
}
