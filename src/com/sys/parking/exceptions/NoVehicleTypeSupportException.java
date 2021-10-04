package com.sys.parking.exceptions;

/**
 * @author vuducnoi
 */
public class NoVehicleTypeSupportException extends Exception {
    public NoVehicleTypeSupportException() {
        super("Parking lot doesn't have slot for this vehicle type");
    }
}
