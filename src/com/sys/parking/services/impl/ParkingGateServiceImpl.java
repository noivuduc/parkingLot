package com.sys.parking.services.impl;

import com.sys.parking.exceptions.NoVehicleFoundException;
import com.sys.parking.exceptions.NoVehicleTypeSupportException;
import com.sys.parking.exceptions.ParkingLotFullException;
import com.sys.parking.exceptions.TicketNotFoundException;
import com.sys.parking.helpers.LoggerImpl;
import com.sys.parking.models.*;
import com.sys.parking.services.ParkingGateService;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vuducnoi
 */
public class ParkingGateServiceImpl implements ParkingGateService {
    public static final String ACTION_ENTER = "Enter";
    public static final String ACTION_EXIT = "Exit";
    public static final String CAR = "car";
    public static final String MOTORCYCLE = "motorcycle";
    public static final String SPACE = " ";
    private Map<String, BaseVehicle> vehicleMap;
    private LoggerImpl logger;
    private ParkingLot parkingLot;

    public ParkingGateServiceImpl() {
        this.vehicleMap = new HashMap<>();
        this.parkingLot = ParkingLot.getInstance();
        this.logger = LoggerImpl.getInstance();
    }

    /**
     * Vehicle enters the gate
     *
     * @param line String line, example: Enter motorcycle SGX1234A 1613541902.
     *
     * @throws NoVehicleTypeSupportException If the vehicle type is invalid.
     */
    @Override
    public void enter(String[] line) throws NoVehicleTypeSupportException {
        BaseVehicle vehicle;
        String vehicleType = line[1];
        String vehicleNumber = line[2];
        long timeStamp = Long.parseLong(line[3]);
        switch (vehicleType) {
            case CAR:
                vehicle = new Car(vehicleNumber);
                break;
            case MOTORCYCLE:
                vehicle = new MotorCycle(vehicleNumber);
                break;
            default:
                throw new NoVehicleTypeSupportException();
        }
        vehicleMap.put(vehicleNumber, vehicle);

        try {
            Ticket ticket = parkingLot.enter(vehicle, timeStamp);
            logger.info("Accept", ticket.getParkingLot().toString());
        } catch (ParkingLotFullException ex) {
            logger.error("Reject");
        }

    }

    /**
     * Vehicle exit parking lot
     *
     * @param line String line, example: Exit SGX1234A 1613545602
     */
    @Override
    public void exit(String[] line) throws NoVehicleFoundException, NoVehicleTypeSupportException {
        String vehicleNumber = line[1];
        long timeStamp = Long.parseLong(line[2]);
        BaseVehicle vehicle = vehicleMap.get(vehicleNumber);
        if (vehicle == null) {
            throw new NoVehicleFoundException();
        }
        try {
            Ticket ticket = parkingLot.exit(vehicle, timeStamp);
            logger.info(ticket.getParkingLot().toString(), String.valueOf(ticket.getPrice()));
        } catch (TicketNotFoundException ex) {
            logger.error("Ticket not found");
        }
        vehicleMap.remove(vehicleNumber);
    }

    /**
     * Read data from input file
     *
     * @param inputPath String
     */
    @Override
    public void process(String inputPath) {
        Path path = Paths.get(inputPath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line1 = reader.readLine();
            String[] capacities = line1.split(SPACE);
            parkingLot.setMaxSlotForCars(Integer.parseInt(capacities[0]));
            parkingLot.setMaxSlotForMotorCycle(Integer.parseInt(capacities[1]));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(SPACE);
                String action = data[0];
                switch (action) {
                    case ACTION_ENTER:
                        enter(data);
                        break;
                    case ACTION_EXIT:
                        exit(data);
                        break;
                    default:
                        throw new UnsupportedOperationException("Unsupported type " + action);
                }
            }
        } catch (IOException ex) {
            logger.error("Unable to read input file");
        } catch (NoVehicleTypeSupportException ex) {
            logger.error("Invalid Vehicle type");
        } catch (UnsupportedOperationException | NumberFormatException | NoVehicleFoundException ex) {
            logger.error(ex.getMessage());
        }
    }
}
