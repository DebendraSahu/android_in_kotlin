package practice.utility;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Printer {

    // ANSI Color Codes
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

    public static void print(String color, String msg) {
        System.out.println(color + msg + RESET);
    }

    public static void printDescription(String description) {
        if (description == null)
            return;

        final int TOTAL_WIDTH = 50;
        final int MAX_MSG_WIDTH = 30;

        String remaining = description.trim();

        while (!remaining.isEmpty()) {
            String linePart;

            if (remaining.length() <= MAX_MSG_WIDTH) {
                linePart = remaining;
                remaining = "";
            } else {
                // Find last space within limit to avoid word break
                int breakIndex = remaining.lastIndexOf(' ', MAX_MSG_WIDTH);
                if (breakIndex == -1) {
                    // no space found, break hard
                    breakIndex = MAX_MSG_WIDTH;
                }

                linePart = remaining.substring(0, breakIndex).trim();
                remaining = remaining.substring(breakIndex).trim();
            }

            int padding = TOTAL_WIDTH - linePart.length() - 2; // spaces around message
            int padLeft = padding / 2;
            int padRight = padding - padLeft;

            String line = "═".repeat(padLeft) + " " + linePart + " " + "═".repeat(padRight);
            print(CYAN, line);
        }
    }

    private static String getTimestamp() {
        return "[" + LocalTime.now().format(TIME_FORMATTER) + "]";
    }

    public static void printWarning(String msg) {
        print(YELLOW, getTimestamp() + " " + msg);
    }

    public static void printError(String msg) {
        print(RED, getTimestamp() + " " + msg);
    }

    public static void printInfo(String msg) {
        print(GREEN, getTimestamp() + " " + msg);
    }
}
