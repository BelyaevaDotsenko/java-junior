package com.acme.edu.messagetype;

import com.acme.edu.utils.TypeMessage;

import java.util.List;

public class IntMessage extends NumberMessage {
    private int messageField;

    public IntMessage(int message) {
        super(TypeMessage.Int);
        this.messageField = message;
    }

    public static String getValue(List<LoggerMessage> messageBuffer) {
        //TODO super
        return null;
    }

}
