package edu.kcc.java.order.data;

/**
 * A custom Exception for working with Order data.  In this case, the IDE was
 * allowed to apply all the possible constructors.
 * 
 * @author Andrew Hanson
 */
public class OrderDataException extends Exception{
    
    /**
     * 
     */
    public OrderDataException(){
    }
    
    /**
     * 
     * @param message 
     */
    public OrderDataException(String message){
        super(message);
    }
    
    /**
     *
     * @param message
     * @param cause
     */
    public OrderDataException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause
     */
    public OrderDataException(Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public OrderDataException(String message, Throwable cause
            , boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
