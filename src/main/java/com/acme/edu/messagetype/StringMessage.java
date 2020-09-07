package com.acme.edu.messagetype;

import com.acme.edu.utils.MessagePrefix;
import com.acme.edu.utils.TypeMessage;

import java.util.ArrayList;
import java.util.List;

public class StringMessage extends LoggerMessage {
    private String messageField;

    public StringMessage(String messageField) {
        super(TypeMessage.String);
        this.messageField = messageField;
    }

    public String getMessageField() {
        return messageField;
    }

    public static String getValue(List<LoggerMessage> messageBuffer) {
        List<String> answer = new ArrayList<>();
        int length = messageBuffer.size();
        StringBuilder resultMessage = new StringBuilder();
        for (int i = 0; i < length; ) {
            String str = ((StringMessage) messageBuffer.get(i)).getMessageField();
            int counter = 1;
            while ((i + counter < length) && str.equals(((StringMessage) messageBuffer.get(i + counter)).getMessageField())) {
                counter++;
            }
            if (counter > 1) {
                answer.add(str + " (x" + counter + ")");
            } else {
                answer.add(str);
            }
            i += counter;
        }
        for (String str : answer) resultMessage.append(MessagePrefix.STRING_PREFIX).append(str);
        return resultMessage.toString();
    }
}
