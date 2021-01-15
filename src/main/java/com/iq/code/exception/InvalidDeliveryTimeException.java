package com.iq.code.exception;

public class InvalidDeliveryTimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDeliveryTimeException() {
		super();
	}

	public InvalidDeliveryTimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidDeliveryTimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDeliveryTimeException(String message) {
		super(message);
	}

	public InvalidDeliveryTimeException(Throwable cause) {
		super(cause);
	}
	
	

}
