package com.acme.edu;

import com.acme.edu.messagetype.IntMessage;
import com.acme.edu.messagetype.StringMessage;
import com.acme.edu.saver.ConsoleLoggerSaver;
import com.acme.edu.saver.FileLoggerSaver;
import com.acme.edu.saver.SaverExceptions;

import java.io.IOException;

public class LoggerFacade {
    static LoggerController controller = new LoggerController(
            new ConsoleLoggerSaver(),
            new FileLoggerSaver("outputFile.txt"));

    public static void log(int message) {
        controller.log(new IntMessage(message));
    }

    public static void log(String message) {
        controller.log(new StringMessage(message));
    }

    public static void flush() throws SaverExceptions {
        controller.flush();
    }

    public static void close() throws Exception {
        controller.close();
    }
}
