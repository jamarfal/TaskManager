package org.example.taskmanager.common;

/**
 * Created by jamarfal on 4/2/18.
 */

public class NumberConverter {

    public static int tryParseInt(String intNumber) {
        int parsedNumber;
        try {
            parsedNumber = Integer.parseInt(intNumber);
        } catch (NumberFormatException ex) {
            parsedNumber = 0;
        }
        return parsedNumber;
    }
}
