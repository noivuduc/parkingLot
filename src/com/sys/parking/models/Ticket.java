package com.sys.parking.models;

/**
 * @author vuducnoi
 */
public class Ticket {
    private final Long startTime;
    private final BaseVehicle vehicle;
    private final BaseParkingSlot parkingLot;
    private Long endTime;
    private long price;

    public Ticket(final Long startTime, final BaseVehicle vehicle, final BaseParkingSlot parkingLot) {
        this.startTime = startTime;
        this.vehicle = vehicle;
        this.parkingLot = parkingLot;
    }

    public long getDuration() {
        long seconds = endTime - startTime;
        double hour = (double) seconds / 3600;
        if (hour < 0) {
            return 1;
        }
        return (long) Math.ceil(hour);
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public BaseParkingSlot getParkingLot() {
        return parkingLot;
    }

    public BaseVehicle getVehicle() {
        return vehicle;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPrice() {
        return price;
    }
}
