package com.parking_lot.exception;

public class ParkingLotException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String description;
	
	public ParkingLotException(Exception exception, String description) {
		super();
		this.message = exception.getMessage();
		this.description = description;
	}

	@Override
	public String toString() {
		return "Application is not available " + this.message + " " + this.description;
	}	
	
	
}
