package com.sys.parking.exceptions;

/**
 * @author vuducnoi
 */
public class NoVehicleFoundException extends Exception{
    public NoVehicleFoundException() {
        super("Vehicle not found");
    }
}
