package com.sys.parking.models;

/**
 * @author vuducnoi
 */
public abstract class BaseParkingSlot {
    private final long id;
    private final ParkingSlotType type;
    private boolean occupied;

    protected BaseParkingSlot(final ParkingSlotType type, final long id) {
        this.type = type;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public ParkingSlotType getType() {
        return type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return type.getName() + id;
    }
}
