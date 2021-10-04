package com.sys.parking.exceptions;

/**
 * @author vuducnoi
 */
public class TicketNotFoundException extends Exception{
    public TicketNotFoundException() {
        super("Invalid ticket");
    }
}
