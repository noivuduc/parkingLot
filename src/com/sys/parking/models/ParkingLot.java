package com.sys.parking.models;

import com.sys.parking.exceptions.NoVehicleTypeSupportException;
import com.sys.parking.exceptions.ParkingLotFullException;
import com.sys.parking.exceptions.TicketNotFoundException;
import com.sys.parking.services.impl.PricingServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author vuducnoi
 */
public class ParkingLot {
    private static ParkingLot parkingLot;
    private int maxSlotForCars;
    private int maxSlotForMotorCycle;
    /**
     * ConcurrentSkipList is used for the case where we might have multiple entrances and exits.
     * Alternatively, We can use TreeMap for the synchronous application.
     * By Using this data structure, we will archive log(N) for both enter and exit operation.
     */
    private final ConcurrentSkipListMap<Long, ParkingSlotForCar> availableSlotsForCars;
    private final ConcurrentSkipListMap<Long, ParkingSlotForMotorCycle> availableSlotsForMotorCycle;
    private final Map<String, Ticket> activeTickets;
    private final Map<String, Ticket> histories;
    private final PricingServiceImpl pricingService;

    private ParkingLot() {
        this.activeTickets = new HashMap<>();
        this.histories = new HashMap<>();
        this.availableSlotsForCars = new ConcurrentSkipListMap<>();
        this.availableSlotsForMotorCycle = new ConcurrentSkipListMap<>();
        this.pricingService = PricingServiceImpl.getInstance();
    }

    /**
     * SingleTon pattern to make sure only one parkingLot is available in the system
     *
     * @return ParkingLot
     */
    public static ParkingLot getInstance() {
        if (parkingLot == null) {
            parkingLot = new ParkingLot();
        }
        return parkingLot;
    }

    /**
     * <p>1. Find the empty parking slot for the respective car type.</p>
     * <p>2. Create the ticket and save to activeTickets.</p>
     * <p>3. Print the result reject or accept</p>
     *
     * @param vehicle   In coming Vehicle
     * @param startTime the time of the vehicle come
     */
    public Ticket enter(BaseVehicle vehicle, long startTime) throws NoVehicleTypeSupportException, ParkingLotFullException {
        BaseParkingSlot parkingSlot = pickParkingSlot(vehicle);
        Ticket ticket = new Ticket(startTime, vehicle, parkingSlot);
        activeTickets.put(vehicle.getNumber(), ticket);
        return ticket;
    }

    /**
     * <p>1. Remove the vehicle from parkingLot</p>
     * <p>2. Calculate the price</p>
     * <p>3. Display the parkingSlot and price</p>
     * <p>4. Make the parkingSLot available for the next vehicle</p>
     * <p>5. Save to the history for the auditing purpose if any</p>
     *
     * @param vehicle Vehicle to remove
     * @param endTime Timestamp
     *
     * @throws NoVehicleTypeSupportException when the vehicle is invalid
     */
    public Ticket exit(BaseVehicle vehicle, long endTime) throws NoVehicleTypeSupportException, TicketNotFoundException {
        Ticket ticket = activeTickets.get(vehicle.getNumber());
        if (ticket != null) {
            BaseParkingSlot parkingSlot = ticket.getParkingLot();
            switch (vehicle.getType()) {
                case CARS:
                    availableSlotsForCars.put(parkingSlot.getId(), (ParkingSlotForCar) parkingSlot);
                    break;
                case MOTORCYCLE:
                    availableSlotsForMotorCycle.put(parkingSlot.getId(), (ParkingSlotForMotorCycle) parkingSlot);
                    break;
                default:
                    throw new NoVehicleTypeSupportException();
            }
            ticket.setEndTime(endTime);
            activeTickets.remove(vehicle.getNumber());
            long duration = ticket.getDuration();
            long price = pricingService.calculatePrice(duration, ticket.getParkingLot());
            ticket.setPrice(price);
            histories.put(vehicle.getNumber(), ticket);
        } else {
            throw new TicketNotFoundException();
        }
        return ticket;
    }

    /**
     * This 'synchronized' is to allow multiple entry in the parking lot
     *
     * @param vehicle In coming Vehicle
     *
     * @return ParkingLot
     *
     * @throws ParkingLotFullException       when parking lot is full
     * @throws NoVehicleTypeSupportException when the vehicle type is not supported
     */
    private synchronized BaseParkingSlot pickParkingSlot(BaseVehicle vehicle) throws ParkingLotFullException, NoVehicleTypeSupportException {
        BaseParkingSlot parkingSlot;
        switch (vehicle.getType()) {
            case CARS:
                if (availableSlotsForCars.isEmpty()) {
                    throw new ParkingLotFullException();
                }
                parkingSlot = availableSlotsForCars.firstEntry().getValue();
                availableSlotsForCars.remove(parkingSlot.getId());
                break;
            case MOTORCYCLE:
                if (availableSlotsForMotorCycle.isEmpty()) {
                    throw new ParkingLotFullException();
                }
                parkingSlot = availableSlotsForMotorCycle.firstEntry().getValue();
                availableSlotsForMotorCycle.remove(parkingSlot.getId());
                break;
            default:
                throw new NoVehicleTypeSupportException();
        }
        return parkingSlot;
    }

    /**
     * Create the parking lot for car base on the Max slot
     *
     * @param slot Max slot available for Car of this parkingLot
     */
    public void setMaxSlotForCars(int slot) {
        this.maxSlotForCars = slot;
        for (int i = 1; i <= slot; i++) {
            ParkingSlotForCar parkingSlot = new ParkingSlotForCar(i);
            availableSlotsForCars.put((long) i, parkingSlot);
        }
    }

    /**
     * Create the parking lot for car MotorCycle on the Max slot
     *
     * @param maxSlotForMotorCycle Max slot available for MotorCycle of this parkingLot
     */
    public void setMaxSlotForMotorCycle(int maxSlotForMotorCycle) {
        this.maxSlotForMotorCycle = maxSlotForMotorCycle;
        for (int i = 1; i <= maxSlotForMotorCycle; i++) {
            ParkingSlotForMotorCycle parkingSlotForMotorCycle = new ParkingSlotForMotorCycle(i);
            availableSlotsForMotorCycle.put((long) i, parkingSlotForMotorCycle);
        }
    }

    public int getMaxSlotForCars() {
        return maxSlotForCars;
    }

    public int getMaxSlotForMotorCycle() {
        return maxSlotForMotorCycle;
    }
}
