package com.acme.edu.messagetype;

import com.acme.edu.utils.TypeMessage;

public class LoggerMessage {

    private TypeMessage typeMessage;

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public LoggerMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }
}
