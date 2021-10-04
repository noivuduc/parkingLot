package com.sys.parking.services.impl;

import com.sys.parking.models.BaseParkingSlot;
import com.sys.parking.services.PriceService;

/**
 * @author vuducnoi
 */
public class PricingServiceImpl implements PriceService {
    private static PricingServiceImpl pricingService;
    /**
     * Calculate price base on startTime and endTime
     *
     * @param duration Parking duration
     *
     * @return price
     */
    @Override
    public long calculatePrice(long duration, BaseParkingSlot parkingSlot) {
        return parkingSlot.getType().price(duration);
    }

    public static PricingServiceImpl getInstance() {
        if (pricingService == null) {
            pricingService = new PricingServiceImpl();
        }
        return pricingService;
    }
}
