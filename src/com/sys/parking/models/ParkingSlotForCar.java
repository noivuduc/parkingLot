package com.sys.parking.models;

/**
 * @author vuducnoi
 */
public class ParkingSlotForCar extends BaseParkingSlot {
    public ParkingSlotForCar(long id) {
        super(ParkingSlotType.CAR_LOT, id);
    }
}
