package com.acme.edu.messagetype;

import com.acme.edu.utils.TypeMessage;

import java.util.List;

public class NumberMessage extends LoggerMessage{

    public NumberMessage(TypeMessage typeMessage) {
        super(typeMessage);
    }


    public static String getValue(List<LoggerMessage> messageBuffer, int maxValue) {
        return null;
    }
}
