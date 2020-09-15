package com.acme.edu;

import com.acme.edu.messagetype.LoggerMessage;
import com.acme.edu.saver.LoggerSaver;
import com.acme.edu.saver.SaverExceptions;

import java.io.IOException;
import java.util.Arrays;

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
        Exception exceptionDuringSaving = null;
        for (LoggerSaver loggerSaver : loggerSaver) {
            try {
                loggerSaver.save(res);
            } catch (IOException e) {
                if (exceptionDuringSaving != null)
                    exceptionDuringSaving.addSuppressed(e);
                else
                    exceptionDuringSaving = new Exception(e);
            } finally {
                loggerBuffer.clear();
            }
        }
        if (exceptionDuringSaving != null)
            throw new SaverExceptions("exceptions from bankomat c:", exceptionDuringSaving);
    }

    public void close() throws Exception {
        for (LoggerSaver loggerSaver : loggerSaver) {
            loggerSaver.close();
        }
    }
}
