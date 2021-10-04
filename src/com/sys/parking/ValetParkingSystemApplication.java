package com.sys.parking;

import com.sys.parking.services.impl.ParkingGateServiceImpl;

/**
 * @author vuducnoi
 */
public class ValetParkingSystemApplication {
    public static void main(String[] args) {
        String defaultInput = "input.txt";
        String inputFile;
        if (args != null && args.length > 0) {
            inputFile = args[0];
        } else {
            inputFile = defaultInput;
        }
        ParkingGateServiceImpl parkingGateService = new ParkingGateServiceImpl();
        parkingGateService.process(inputFile);
    }
}
