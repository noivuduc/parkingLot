package com.sys.parking.models;

/**
 * @author vuducnoi
 */
public class MotorCycle extends BaseVehicle {
    public MotorCycle(String number) {
        super(VehicleType.MOTORCYCLE);
        this.number = number;
    }
}
