package com.iq.code.service;

import org.jboss.logging.Logger;

/**
 * A Thread class to print the message saved in DB.
 * @author arunkumar.pushparaj
 *
 */
public class MessageThread implements Runnable {

	private String message;

	Logger logger = Logger.getLogger(MessageThread.class);

	MessageThread(String message) {
		this.message = message;
	}

	@Override
	public void run() {
		logger.debug("Scheduler Initiated..");
		logger.info(this.message);
	}

}
