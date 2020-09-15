package com.acme.edu;

import com.acme.edu.messagetype.LoggerMessage;
import com.acme.edu.saver.LoggerSaver;
import com.acme.edu.saver.SaverExceptions;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        final List<Exception> exceptionDuringSaving = new LinkedList<Exception>();
        loggerSaver.forEach(
                loggerSaver -> {
                    try {
                        loggerSaver.save(res);
                    } catch (IOException e) {
                        exceptionDuringSaving.add(e);
                    } finally {
                        loggerBuffer.clear();
                    }
                }
        );
        Optional<Exception> optionalException = exceptionDuringSaving.stream().reduce((e1, e2) -> {
            e1.addSuppressed(e2);
            return e1;
        });
        if (optionalException.isPresent())
            throw new SaverExceptions("exception from bankomat c:", optionalException.get());
    }

    public void close() throws Exception {
        for (LoggerSaver loggerSaver : loggerSaver) {
            loggerSaver.close();
        }
    }
}
