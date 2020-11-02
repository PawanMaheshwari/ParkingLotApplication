package com.parking_lot.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ParkingLotDAO {

	public static Map<String, Integer> PARKINGLOTMAP = new HashMap<String, Integer>();
	static int parkingLotSize;
	static int availableSlots;

	public void initializePakringSize(String parkingLotSize) {
		synchronized (PARKINGLOTMAP) {
			ParkingLotDAO.parkingLotSize = Integer.valueOf(parkingLotSize);
			ParkingLotDAO.availableSlots = ParkingLotDAO.parkingLotSize;
			displayOperationStatus("Created parking lot with " + parkingLotSize + " Slots");
		}
	}

	public void parkVehicle(String vehicleNumber) {
		synchronized (PARKINGLOTMAP) {
			if (PARKINGLOTMAP.keySet().contains(vehicleNumber)) {
				displayOperationStatus("Vehicle with vehicle number " + vehicleNumber + " already exist in slot number "
						+ PARKINGLOTMAP.get(vehicleNumber));
				return;
			}

			if (availableSlots == 0) {
				displayOperationStatus("Sorry, parking lot is full");
				return;
			}

			if (availableSlots != 0) {
				Collection<Integer> allocatedparkingLots = PARKINGLOTMAP.values();
				for (int allotSlot = 1; allotSlot <= parkingLotSize; allotSlot++) {
					if (!allocatedparkingLots.contains(allotSlot)) {
						PARKINGLOTMAP.put(vehicleNumber, allotSlot);
						displayOperationStatus("Allocated slot number : " + allotSlot);
						availableSlots--;
						break;
					}
				}
			}
		}
	}

	public void unparkVehicle(String vehicleNumber, String hours) {
		synchronized (PARKINGLOTMAP) {
			if (!PARKINGLOTMAP.containsKey(vehicleNumber)) {
				displayOperationStatus("Vehicle with vehicle number " + vehicleNumber + " does not exist in parking");
				return;
			}
			if (PARKINGLOTMAP.containsKey(vehicleNumber)) {
				int unparkVehicleSlot = PARKINGLOTMAP.get(vehicleNumber);
				PARKINGLOTMAP.remove(vehicleNumber);
				availableSlots++;
				int bill = calculateBill(hours);
				displayOperationStatus("Registration number " + vehicleNumber + " with Slot number " + unparkVehicleSlot
						+ " is fee with Charge " + bill);
			}
		}
	}

	public void statusParkingLot() {
		synchronized (PARKINGLOTMAP) {
			displayOperationStatus("Slot No. " + "  " + "Registration No .");
			Map<String, Integer> sortedParkingMap = PARKINGLOTMAP.entrySet().stream()
                    .sorted(Entry.comparingByValue())
                    .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
			sortedParkingMap.forEach((k, v) -> displayOperationStatus(v + "     " + k));
		}
	}

	public int calculateBill(String hours) {
		int parkingBill;
		if (Integer.valueOf(hours) > 2) {
			parkingBill = 10 + 10 * (Integer.valueOf(hours) - 2);
		} else {
			parkingBill = 10;
		}
		return parkingBill;
	}

	public void displayOperationStatus(String displayStatus) {
		System.out.println(displayStatus);
	}
}
