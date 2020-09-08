package com.acme.edu.messagetype;

import com.acme.edu.utils.TypeMessage;

public interface LoggerMessage {
    Object getMessage();

    TypeMessage getTypeMessage();

    default boolean hasOneType(LoggerMessage message) {
        return this.getTypeMessage() == message.getTypeMessage();
    }

    void reduce(LoggerMessage loggerMessage);

    String decorate();

}
