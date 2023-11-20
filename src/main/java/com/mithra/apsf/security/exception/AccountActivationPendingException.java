package com.mithra.apsf.security.exception;

import org.springframework.security.authentication.AccountStatusException;

/**
 * @author mtoluchuri
 *
 */
public class AccountActivationPendingException extends AccountStatusException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor - @param message Constructor - @param cause
     */
	public AccountActivationPendingException(String message, Throwable cause) {
		super(message, cause);
	}

    /**
     * Constructor - @param message
     */
	public AccountActivationPendingException(String message) {
		super(message);
	}
}
