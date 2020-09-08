package com.acme.edu.messagetype;

import com.acme.edu.utils.TypeMessage;

public class IntMessage implements LoggerMessage {
    private int messageField;
    private final TypeMessage typeMessage = TypeMessage.Int;

    public IntMessage(int message) {
        this.messageField = message;
    }

    @Override
    public Integer getMessage() {
        return messageField;
    }

    @Override
    public TypeMessage getTypeMessage() {
        return typeMessage;
    }
}
