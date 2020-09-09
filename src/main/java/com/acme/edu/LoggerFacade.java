package com.acme.edu;

import com.acme.edu.messagetype.IntMessage;
import com.acme.edu.messagetype.StringMessage;
import com.acme.edu.saver.ConsoleLoggerSaver;

import java.io.IOException;

public class LoggerFacade {
    static LoggerController controller = new LoggerController(new ConsoleLoggerSaver());

    public static void log(int message) throws IOException {
        controller.log(new IntMessage(message));
    }

    public static void log(String message) throws IOException {
        controller.log(new StringMessage(message));
    }

    public static void flush() throws IOException {
        controller.flush();
    }
}
