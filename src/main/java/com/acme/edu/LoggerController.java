package com.acme.edu;

import com.acme.edu.messagetype.ByteMessage;
import com.acme.edu.messagetype.IntMessage;
import com.acme.edu.messagetype.LoggerMessage;
import com.acme.edu.messagetype.StringMessage;

import java.util.ArrayList;
import java.util.List;

public class LoggerController {
    private ConsoleLoggerSaver loggerSaver = new ConsoleLoggerSaver();
    private List<LoggerMessage> messageBuffer = new ArrayList<>();

    public void log(LoggerMessage loggerMessage) {
        messageBuffer.add(loggerMessage);
    }



    public void flush() {
        if(notOneType()) throw  new IllegalArgumentException("Not one type in sequence");
        printBuffer();
        messageBuffer.clear();
    }

    private boolean notOneType() {
        for(int i=1;i<messageBuffer.size();i++){
            if(messageBuffer.get(i).getTypeMessage()!=messageBuffer.get(i-1).getTypeMessage()) return true;
        }
        return false;
    }

    private void printBuffer() {
        switch (messageBuffer.get(0).getTypeMessage()) {
            case Int:
                printInteger();
                break;
            case Byte:
                printByte();
                break;
            case String:
                printString();
                break;
            default:
                throw new IllegalArgumentException("bad Input");

        }
    }

    private void printInteger() {
        String resultMessage = IntMessage.getValue(messageBuffer);
        loggerSaver.save(resultMessage);
    }
    private void printByte() {
        String resultMessage = ByteMessage.getValue(messageBuffer);
        loggerSaver.save(resultMessage);
    }
    private void printString() {
        String resultMessage = StringMessage.getValue(messageBuffer);
        loggerSaver.save(resultMessage);
    }
}
