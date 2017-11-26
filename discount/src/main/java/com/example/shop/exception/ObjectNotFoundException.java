package com.example.shop.exception;

public class ObjectNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String message, Object... data) {
		super(String.format(message, data));
	}
	
	public ObjectNotFoundException(Throwable e) {
		super(e);
	}

}
