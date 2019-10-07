package com.stackroute.moviecruiserserverapplication.exception;

public class MoviewAlreadyExistException extends Exception {
	
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param message
	 */
	public MoviewAlreadyExistException(final String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "MoviewAlreadyExistException [message=" + message + "]";
	}

}
