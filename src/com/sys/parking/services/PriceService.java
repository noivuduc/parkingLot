package com.sys.parking.services;

import com.sys.parking.models.BaseParkingSlot;

/**
 * @author vuducnoi
 */
public interface PriceService {
    /**
     * Calculate price base on startTime and endTime
     *
     * @param duration Parking duration
     *
     * @return price
     */
    long calculatePrice(long duration, BaseParkingSlot parkingSlot);
}
