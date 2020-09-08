package com.acme.edu;

import com.acme.edu.messagetype.ByteMessage;
import com.acme.edu.messagetype.IntMessage;
import com.acme.edu.messagetype.StringMessage;

import java.io.IOException;

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

    public static void flush() throws IOException {
        controller.flush();
    }
}
