package com.acme.edu;

import com.acme.edu.messagetype.IntMessage;
import com.acme.edu.messagetype.StringMessage;

public class LoggerFacade {

    private static LoggerController controller = new LoggerController();

    public static void log(int message) {
        controller.log(new IntMessage(message));
    }

    public static void log(byte message) {
        controller.log(new ByteMessage(message));
    }

    public static void log(String message) {
        controller.log(new StringMessage(message));
    }
/*
    public static void log(int[] message) {
        messageBuffer.add(message);
    }

    public static void log(int[][] message) {
        messageBuffer.add(message);
    }


    public static void log(boolean message) {
        print(PRIMITIVE_PREFIX + message);
    }

    public static void log(char message) {
        print(CHAR_PREFIX + message);
    }

    public static void log(Object message) {
        print(REFERENCE_PREFIX + message);
    }*/

    public static void flush() {
        controller.flush();
    }
}
