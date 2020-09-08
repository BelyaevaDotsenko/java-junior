package com.acme.edu.messagetype;

import com.acme.edu.utils.TypeMessage;

public class ByteMessage implements LoggerMessage {
    private TypeMessage typeMessage = TypeMessage.Byte;

    public ByteMessage(byte message) {
        //TODO
    }

    @Override
    public Byte getMessage() {
        return null;
    }

    @Override
    public TypeMessage getTypeMessage() {
        return typeMessage;
    }
}
