package com.acme.edu.messagetype;

import com.acme.edu.utils.TypeMessage;

import static com.acme.edu.utils.MessagePrefix.STRING_PREFIX;
import static java.lang.System.lineSeparator;


public class StringMessage implements LoggerMessage {
    private String messageField;
    private TypeMessage typeMessage = TypeMessage.String;
    private int counterInCurrentBlock;
    private int counterBlocks = -1;


    private String currentBlock;

    public StringMessage(String messageField) {
        this.messageField = "";
        this.currentBlock = messageField;
        refreshCounting();
    }

    @Override
    public String getMessage() {
        return messageField;
    }

    @Override
    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    @Override
    public void reduce(LoggerMessage loggerMessage) {
        if (currentBlock.equals(((StringMessage) loggerMessage).getCurrentBlock())) {
            counterInCurrentBlock++;
            return;
        }
        this.messageField = this.messageField + addDeltaString();
        currentBlock = ((StringMessage) loggerMessage).getCurrentBlock();
        refreshCounting();
    }

    private void refreshCounting() {
        this.counterBlocks++;
        this.counterInCurrentBlock = 1;
    }

    private String getCount() {
        if (counterInCurrentBlock > 1) return " (x" + counterInCurrentBlock + ")";
        return "";
    }

    private String getComma() {
        if (counterBlocks > 0) return ", ";
        return "";
    }

    @Override
    public String decorate() {
        return STRING_PREFIX + this.getMessage() + addDeltaString() + lineSeparator();
    }

    private String addDeltaString() {
        return getComma() + this.currentBlock + getCount();
    }

    public String getCurrentBlock() {
        return currentBlock;
    }
}
