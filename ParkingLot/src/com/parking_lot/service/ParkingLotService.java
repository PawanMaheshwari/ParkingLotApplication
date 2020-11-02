package com.parking_lot.service;

import java.io.*;
import java.lang.reflect.Array;

import com.parking_lot.bean.ParkingLotBean;
import com.parking_lot.dao.ParkingLotDAO;
import com.parking_lot.exception.ParkingLotException;

public class ParkingLotService {

	public void readParkingInput() {
		try {
			String projectpath = System.getProperty("user.dir") + File.separator;
			String fileToRead = projectpath + "file_input.txt";
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileToRead)));

			while (bufferedReader.ready()) {
				String eachLine = bufferedReader.readLine();
				String[] words = eachLine.trim().split(" ");
				invokeOperation(words);
			}
		} catch (FileNotFoundException fileNotFoundException) {
			throw new ParkingLotException(fileNotFoundException, "File not found");
		} catch (Exception exception) {
			throw new ParkingLotException(exception, "Something wrong occured, please try again");
		}
	}

	public void invokeOperation(String[] matchingOperation) {
		ParkingLotBean parkingBean = new ParkingLotBean();
		String operation = (String) Array.get(matchingOperation, 0);
		if (operation.matches(parkingBean.getCreate_parking_lot())) {
			createParkingLot((String) Array.get(matchingOperation, 1));
		} else if (operation.matches(parkingBean.getPark())) {
			park((String) Array.get(matchingOperation, 1));
		} else if (operation.matches(parkingBean.getLeave())) {
			unparkVehicle((String) Array.get(matchingOperation, 1), (String) Array.get(matchingOperation, 2));
		} else if (operation.matches(parkingBean.getStatus())) {
			statusParkingLot();
		}
	}

	public void createParkingLot(String parkingLotSize) {
		ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
		parkingLotDAO.initializePakringSize(parkingLotSize);
	}

	public void park(String vehicleNumber) {
		ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
		parkingLotDAO.parkVehicle(vehicleNumber);
	}

	public void unparkVehicle(String vehicleNumber, String hours) {
		ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
		parkingLotDAO.unparkVehicle(vehicleNumber, hours);
	}

	public void statusParkingLot() {
		ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
		parkingLotDAO.statusParkingLot();
	}
}
