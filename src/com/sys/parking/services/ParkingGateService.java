package com.sys.parking.services;

import com.sys.parking.exceptions.NoVehicleFoundException;
import com.sys.parking.exceptions.NoVehicleTypeSupportException;
import com.sys.parking.exceptions.ParkingLotFullException;
import com.sys.parking.exceptions.TicketNotFoundException;

/**
 * @author vuducnoi
 */
public interface ParkingGateService {
    /**
     * Vehicle enters the gate
     *
     * @param line is a line read from input file, the format will be: "Enter motorcycle SGX1234A 1613541902".
     *
     * @throws NoVehicleTypeSupportException If the vehicle type is invalid.
     */
    void enter(String[] line) throws NoVehicleTypeSupportException, ParkingLotFullException;

    /**
     * Vehicle exit parking lot
     *
     * @param line is a line read from input file, the format will be: "Exit SGX1234A 1613545602".
     */
    void exit(String[] line) throws NoVehicleFoundException, NoVehicleTypeSupportException, TicketNotFoundException;

    /**
     * Read data from input file
     *
     * @param inputPath is the input file
     */
    void process(String inputPath);
}
