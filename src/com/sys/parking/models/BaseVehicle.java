package com.sys.parking.models;

import java.util.Objects;

/**
 * @author vuducnoi
 */
public abstract class BaseVehicle {
    private final VehicleType type;
    protected String number;

    protected BaseVehicle(final VehicleType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseVehicle vehicle = (BaseVehicle) o;
        return Objects.equals(number, vehicle.getNumber()) && type == vehicle.getType();
    }

    public String getNumber() {
        return number;
    }

    public VehicleType getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, type);
    }
}
