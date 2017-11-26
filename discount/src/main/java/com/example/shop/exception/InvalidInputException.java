package com.example.shop.exception;

public class InvalidInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidInputException(String message, Object... data) {
		super(String.format(message, data));
	}
	
	public InvalidInputException(Throwable e) {
		super(e);
	}

}
