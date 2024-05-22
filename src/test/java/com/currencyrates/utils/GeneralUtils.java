package com.currencyrates.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * Utility class providing general-purpose methods.
 */
public class GeneralUtils {

    /**
     * Generates a timestamp-based file name using the current date and time.
     *
     * @return A string representing the current date and time formatted as "yyyy-MM-dd_HH-mm-ss".
     */
    public static String getFileName() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedNow = now.format(formatter);
        return formattedNow;
    }
}
