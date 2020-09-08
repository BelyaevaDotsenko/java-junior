package com.acme.edu;

import com.acme.edu.messagetype.IntMessage;
import com.acme.edu.messagetype.LoggerMessage;
import com.acme.edu.messagetype.StringMessage;
import com.acme.edu.utils.TypeMessage;

import java.util.ArrayList;
import java.util.List;

public class LoggerBuffer {
    private List<LoggerMessage> buffer = new ArrayList<>();
    private GeneratorMessage generatorMessage = new GeneratorMessage();


    public String generateOutputValue() {
        switch (bufferType()) {
            case Int:
                return printInteger();
            case Byte:
//                return printByte();
            case String:
                return printString();
            default:
                throw new IllegalStateException("bad Input");
        }
    }

    private String printInteger() {
        return generatorMessage.generate(buffer.toArray(new IntMessage[0]));
    }

    /*private void printByte() {
        String resultMessage = ByteMessage.getValue(messageBuffer);
        loggerSaver.save(resultMessage);
    }*/

    private String printString() {
        return generatorMessage.generate(buffer.toArray(new StringMessage[0]));
    }

    public LoggerMessage get(int index) {
        return buffer.get(index);
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
