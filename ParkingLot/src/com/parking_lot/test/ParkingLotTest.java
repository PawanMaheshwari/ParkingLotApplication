package com.parking_lot.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.parking_lot.dao.ParkingLotDAO;
import com.parking_lot.exception.ParkingLotException;

public class ParkingLotTest {
	ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
	Map<String, Integer> parkingLotMap = ParkingLotDAO.PARKINGLOTMAP;
    
	@Test
	public void createParkingLot() throws ParkingLotException {
		parkingLotDAO.initializePakringSize("1");
		assertEquals(parkingLotMap.isEmpty(), true);
	}

	@Test
	public void testPark() throws ParkingLotException {
		parkingLotDAO.parkVehicle("KA-01-HH-3141");
		parkingLotDAO.parkVehicle("DL-25-LO-9984");
		assertEquals(parkingLotMap.size(), 1);
	}

	@Test
	public void testUnpark() throws ParkingLotException
	{
		parkingLotDAO.unparkVehicle("KA-01-HH-3141", "8");
		parkingLotDAO.unparkVehicle("DL-25-LO-9984", "5");
		assertEquals(parkingLotMap.size(), 0);
	}

	@Test
	public void status() throws ParkingLotException {
		parkingLotDAO.statusParkingLot();
	}
}
