package com.sys.parking.exceptions;

/**
 * @author vuducnoi
 */
public class ParkingLotFullException extends Exception {
    public ParkingLotFullException() {
        super("ParkingLot is full");
    }
}
