package com.parking_lot.ui;

import com.parking_lot.exception.ParkingLotException;
import com.parking_lot.service.ParkingLotService;

public class ParkingLotController {

	public static void main(String[] args) throws ParkingLotException {

		ParkingLotService service = new ParkingLotService();
		service.readParkingInput();
	}
}
