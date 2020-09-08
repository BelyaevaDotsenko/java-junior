package com.acme.edu.messagetype;

import com.acme.edu.utils.TypeMessage;

import static com.acme.edu.utils.MessagePrefix.PRIMITIVE_PREFIX;
import static java.lang.System.lineSeparator;

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

    @Override
    public void reduce(LoggerMessage loggerMessage) {
        this.messageField = this.getMessage() + (int) loggerMessage.getMessage();
    }

    @Override
    public String decorate() {
        return PRIMITIVE_PREFIX + this.getMessage() + lineSeparator();
    }
}
