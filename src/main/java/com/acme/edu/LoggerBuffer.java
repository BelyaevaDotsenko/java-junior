package com.acme.edu;

import com.acme.edu.messagetype.LoggerMessage;
import com.acme.edu.utils.TypeMessage;

import java.util.ArrayList;
import java.util.List;


public class LoggerBuffer {
    private List<LoggerMessage> buffer = new ArrayList<>();

    public String generateOutputValue() {
        LoggerMessage message = buffer.get(0);
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < buffer.size(); i++) {
            if (buffer.get(i).hasOneType(message)) {
                message.reduce(buffer.get(i));
            } else {
                result.append(message.decorate());
                message = buffer.get(i);
            }
        }
        return result.append(message.decorate()).toString();
    }

    public void add(LoggerMessage loggerMessage) {
        buffer.add(loggerMessage);
    }

    public int size() {
        return buffer.size();
    }

    public void clear() {
        buffer.clear();
    }
}
