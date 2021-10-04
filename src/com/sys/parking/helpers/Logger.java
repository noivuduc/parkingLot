package com.sys.parking.helpers;

/**
 * @author vuducnoi
 */
public interface Logger {
    /**
     * Log info to the console
     * @param args message to print
     */
    void info(String... args);

    /**
     * Log error to the console
     * @param args message to print
     */
    void error(String... args);

    /**
     * Log debug to the console
     * @param args message to print
     */
    void debug(String... args);
}
