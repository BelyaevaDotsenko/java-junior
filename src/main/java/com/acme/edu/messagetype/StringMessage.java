package com.acme.edu.messagetype;

import com.acme.edu.utils.TypeMessage;

import static com.acme.edu.utils.MessagePrefix.STRING_PREFIX;
import static java.lang.System.lineSeparator;


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

    private int counter = 1;

    @Override
    public void reduce(LoggerMessage loggerMessage) {
        if (this.getMessage().contains((CharSequence) loggerMessage.getMessage())) {
            counter++;
        }
        this.messageField = this.getMessage() + " (x" + counter + ")";
    }

    String countRepetitions(String messageField) {
        StringBuilder sb = new StringBuilder(messageField);
        int minIndex = sb.indexOf(" (x");
        int maxIndex = sb.lastIndexOf(" (x");
        if (minIndex < 0) {
            return messageField;
        } else {
            sb.delete(minIndex, maxIndex);
            return sb.toString();
        }
    }

    @Override
    public String decorate() {
        return STRING_PREFIX + countRepetitions(this.getMessage()) + lineSeparator();
    }
}
