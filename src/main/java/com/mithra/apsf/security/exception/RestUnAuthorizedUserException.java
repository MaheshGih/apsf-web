package com.mithra.apsf.security.exception;

/**
 * 
 * Throw Exception when Un Authorized user accessing the PayTxt 
 * 
 **/
public class RestUnAuthorizedUserException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor -
     */
	public RestUnAuthorizedUserException() {
		super();
	}

    /**
     * Constructor - @param message Constructor - @param cause
     */
	public RestUnAuthorizedUserException(String message, Throwable cause) {
		super(message, cause);
	}

    /**
     * Constructor - @param message
     */
    public RestUnAuthorizedUserException( String message )
    {
        super( message );
    }

    /**
     * Constructor - @param cause
     */
	public RestUnAuthorizedUserException(Throwable cause) {
		super(cause);
	}
}
