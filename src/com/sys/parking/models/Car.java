package com.sys.parking.models;

/**
 * @author vuducnoi
 */
public class Car extends BaseVehicle {
    public Car(String number) {
        super(VehicleType.CARS);
        this.number = number;
    }
}
