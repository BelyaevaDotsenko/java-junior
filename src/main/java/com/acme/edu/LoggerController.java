package com.acme.edu;

import com.acme.edu.messagetype.LoggerMessage;
import com.acme.edu.saver.LoggerSaver;
import com.acme.edu.saver.SaverExceptions;

import java.io.IOException;

public class LoggerController {
    private final LoggerSaver loggerSaver;
    private final LoggerBuffer loggerBuffer = new LoggerBuffer();

    public LoggerController(LoggerSaver loggerSaver) {
        this.loggerSaver = loggerSaver;
    }

    public void log(LoggerMessage loggerMessage) {
        //if (loggerBuffer.size() != 0 && loggerBuffer.bufferType() != loggerMessage.getTypeMessage()) flush();
        loggerBuffer.add(loggerMessage);
    }

    public void flush() throws SaverExceptions {
        if (loggerBuffer.size() == 0) return;
        String res = loggerBuffer.generateOutputValue();
        try {
            loggerSaver.save(res);
        } catch (SaverExceptions | IOException e){
            throw new SaverExceptions("Save exception occurs during flush()", e);
        };
        loggerBuffer.clear();
    }
}
