package com.acme.edu.messagetype;

import com.acme.edu.utils.TypeMessage;


public class StringMessage implements LoggerMessage {
    private String messageField;
    private TypeMessage typeMessage = TypeMessage.String;

    public StringMessage(String messageField) {
        this.messageField = messageField;
    }

    @Override
    public String getMessage() {
        return messageField;
    }

    @Override
    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

}
