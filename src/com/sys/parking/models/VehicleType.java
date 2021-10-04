package com.sys.parking.models;

/**
 * @author vuducnoi
 */

public enum VehicleType {
    /**
     * Car
     */
    CARS("car"),
    /**
     * motor cycle
     */
    MOTORCYCLE("motorcycle");

    private final String value;

    VehicleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
