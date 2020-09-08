package com.acme.edu;

import com.acme.edu.messagetype.LoggerMessage;

public class LoggerController {
    private final ConsoleLoggerSaver loggerSaver = new ConsoleLoggerSaver();
    private final LoggerBuffer loggerBuffer = new LoggerBuffer();

    public void log(LoggerMessage loggerMessage) {
        loggerBuffer.add(loggerMessage);
    }

    public void flush() {
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
