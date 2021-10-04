package com.sys.parking.helpers;

/**
 * @author vuducnoi
 */
public class LoggerImpl implements Logger{

    private static LoggerImpl logger;

    private LoggerImpl(){}

    public static LoggerImpl getInstance() {
        if (logger == null) {
            logger = new LoggerImpl();
        }
        return logger;
    }

    /**
     * Log info to the console
     *
     * @param args message to print
     */
    @Override
    public void info(String... args) {
        System.out.print("INFO [" + System.currentTimeMillis() + "] ");
        for (String arg : args) {
            System.out.print(arg + " ");
        }
        System.out.println();
    }

    /**
     * Log error to the console
     *
     * @param args message to print
     */
    @Override
    public void error(String... args) {
        System.out.print("ERROR [" + System.currentTimeMillis() + "] ");
        for (String arg : args) {
            System.out.print(arg + " ");
        }
        System.out.println();
    }

    /**
     * Log debug to the console
     *
     * @param args message to print
     */
    @Override
    public void debug(String... args) {
        System.out.print("DEBUG [" + System.currentTimeMillis() + "] ");
        for (String arg : args) {
            System.out.print(arg + " ");
        }
        System.out.println();
    }
}
