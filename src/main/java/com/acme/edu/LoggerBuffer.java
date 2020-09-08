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
                result.append(message.getMessage());
                message = buffer.get(i);
            }
        }
        return message.decorate();
    }

    public void add(LoggerMessage loggerMessage) {
        buffer.add(loggerMessage);
    }

    public TypeMessage getType(int index) {
        return buffer.get(index).getTypeMessage();
    }

    public int size() {
        return buffer.size();
    }

    public TypeMessage bufferType() {
        return getType(0);
    }

    public void clear() {
        buffer.clear();
    }
}
