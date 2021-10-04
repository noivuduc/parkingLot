package com.sys.parking.models;

/**
 * @author vuducnoi
 */

public enum ParkingSlotType {
    /**
     * This type is available for Car only
     */
    CAR_LOT("CarLot") {
        @Override
        public long price(long duration) {
            return 2 * duration;
        }
    },
    /**
     * This type is available for motorcycle only
     */
    MOTORCYCLE_LOT("MotorcycleLot") {
        @Override
        public long price(long duration) {
            // The price is $1 per hour
            return duration;
        }
    };

    private final String name;

    ParkingSlotType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract long price(long duration);
}
