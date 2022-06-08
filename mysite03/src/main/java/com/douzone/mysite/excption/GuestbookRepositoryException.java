package com.douzone.mysite.excption;

public class GuestbookRepositoryException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GuestbookRepositoryException(String message) {
		super(message);
	}

	public GuestbookRepositoryException() {
		super("GuestbookRepositoryException Occurs....");
	}
}
