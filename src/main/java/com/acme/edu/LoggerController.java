package com.acme.edu;

import com.acme.edu.messagetype.LoggerMessage;
import com.acme.edu.saver.LoggerSaver;

import java.io.IOException;

public class LoggerController {
    private final LoggerSaver loggerSaver;
    private final LoggerBuffer loggerBuffer = new LoggerBuffer();

    public LoggerController(LoggerSaver loggerSaver) {
        this.loggerSaver = loggerSaver;
    }

    public void log(LoggerMessage loggerMessage) throws IOException {
        //if (loggerBuffer.size() != 0 && loggerBuffer.bufferType() != loggerMessage.getTypeMessage()) flush();
        loggerBuffer.add(loggerMessage);
    }

    public void flush() throws IOException {
        if (loggerBuffer.size() == 0) return;
        String res = loggerBuffer.generateOutputValue();
        loggerSaver.save(res);
        loggerBuffer.clear();
    }
}
