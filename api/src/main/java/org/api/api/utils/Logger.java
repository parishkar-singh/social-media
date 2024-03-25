package org.api.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss" );
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_TEAL = "\u001B[38;2;0;128;128m";
    private static final String ANSI_PURPLE = "\u001B[38;2;128;0;128m";

    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String ANSI_MAGENTA = "\u001B[35m";
    private static final String ANSI_SUNSET_ORANGE = "\u001B[38;2;255;93;65m";
    private static final String ANSI_OCEAN_BLUE = "\u001B[38;2;0;119;190m";
    private static final String ANSI_FOREST_GREEN = "\u001B[38;2;34;139;34m";
    private static final String ANSI_SKY_BLUE = "\u001B[38;2;135;206;235m";
    private static final String ANSI_LAVENDER = "\u001B[38;2;230;230;250m";
    private static final String ANSI_GOLDENROD_YELLOW = "\u001B[38;2;218;165;32m";
    private static final String ANSI_MAROON = "\u001B[38;2;128;0;0m";
    private static final String ANSI_INDIGO = "\u001B[38;2;75;0;130m";
    private static final String ANSI_CHERRY_BLOSSOM_PINK = "\u001B[38;2;255;183;197m";
    private static final String ANSI_AQUAMARINE = "\u001B[38;2;127;255;212m";
    private static final String ANSI_PEACH_PUFF = "\u001B[38;2;255;218;185m";
    private static final String ANSI_LIME = "\u001B[38;2;0;255;0m";
    private static final String ANSI_LILAC = "\u001B[38;2;200;162;200m";
    private static final String ANSI_CHOCOLATE = "\u001B[38;2;210;105;30m";
    private static final String ANSI_TURQUOISE = "\u001B[38;2;64;224;208m";

    private final String extraTag;

    public Logger() {
        this.extraTag = "";
    }

    public Logger(String tag) {
        this.extraTag = "[" + tag + "]";
    }

    public void logInfo(String message) {
        logMessage(ANSI_BLUE, "[Info] " , message);
    }

    public void logWarning(String message) {
        logMessage(ANSI_YELLOW, "[Warning] " , message);
    }

    public void logError(String message) {
        logMessage(ANSI_RED, "[Error] " ,message);
    }

    public void logSuccess(String message) {
        logMessage(ANSI_GREEN, "[Success] " , message);
    }

    public void logDatabase(String message) {
        logMessage(ANSI_GREEN, "[Database] " , message);
    }
    public void logStackTrace(Exception e) {
        String stackTrace = getStackTraceAsString(e);
        logMessage(ANSI_PURPLE, "[StackTrace] ", stackTrace);
    }
    private String getStackTraceAsString(Exception e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
    private void logMessage(String color, String tag, String message) {
        String timestamp = dateFormat.format(new Date());
        String formattedTimestamp = "[" + timestamp + "]";
        String alignedTimestamp = String.format("%1$-23s", formattedTimestamp); // Aligns the timestamp to the right with a width of 23 characters

        System.out.println(ANSI_OCEAN_BLUE + alignedTimestamp + ANSI_RESET + ANSI_MAGENTA + (this.extraTag.isEmpty() ? "" : this.extraTag + " -> ") + ANSI_RESET + color + tag + ANSI_RESET + ": " + message);
    }

}
