package com.acme.edu;

import com.acme.edu.messagetype.LoggerMessage;
import com.acme.edu.saver.ConsoleLoggerSaver;
import com.acme.edu.saver.FileSaver;
import com.acme.edu.saver.LoggerSaver;

import java.io.IOException;

public class LoggerController {
    private final LoggerSaver loggerSaver = new ConsoleLoggerSaver();
    private final LoggerBuffer loggerBuffer = new LoggerBuffer();

    public void log(LoggerMessage loggerMessage) {
        loggerBuffer.add(loggerMessage);
    }

    public void flush() throws IOException {
        if (loggerBuffer.size() == 0) return;
        if (notOneType()) throw new IllegalArgumentException("Not one type in sequence");
        loggerSaver.save(loggerBuffer.generateOutputValue());
        loggerBuffer.clear();
    }

    private boolean notOneType() {
        for (int i = 1; i < loggerBuffer.size(); i++) {
            if (loggerBuffer.getType(i) != loggerBuffer.getType(i - 1)) return true;
        }
        return false;
    }
}
