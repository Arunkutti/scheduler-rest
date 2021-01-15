package com.iq.code.exception;

/**
 * Exception class for invalid delivery time.Both format and negative time.
 * @author arunkumar.pushparaj
 *
 */
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
