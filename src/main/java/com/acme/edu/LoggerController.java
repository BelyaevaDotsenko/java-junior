package com.acme.edu;

import com.acme.edu.messagetype.LoggerMessage;
import com.acme.edu.saver.LoggerSaver;
import com.acme.edu.saver.SaverExceptions;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class LoggerController {
    private final Iterable<LoggerSaver> loggerSaver;
    private final LoggerBuffer loggerBuffer = new LoggerBuffer();

    public LoggerController(LoggerSaver... loggerSaver) {
        this.loggerSaver = Arrays.asList(loggerSaver);
    }

    public void log(LoggerMessage loggerMessage) {
        loggerBuffer.add(loggerMessage);
    }

    public void flush() throws SaverExceptions {
        if (loggerBuffer.isEmpty()) return;
        String res = loggerBuffer.generateOutputValue();
        for (LoggerSaver loggerSaver : loggerSaver) {
            try {
                loggerSaver.save(res);
            } catch (IOException e) {
                throw new SaverExceptions(e);
            } finally {
                loggerBuffer.clear();
            }
        }
    }

    public void close() throws Exception {
        for (LoggerSaver loggerSaver : loggerSaver) {
            loggerSaver.close();
        }
    }
}
