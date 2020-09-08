package com.acme.edu;

import com.acme.edu.messagetype.IntMessage;
import com.acme.edu.messagetype.StringMessage;

import java.util.ArrayList;
import java.util.List;

import static com.acme.edu.utils.MessagePrefix.PRIMITIVE_PREFIX;
import static com.acme.edu.utils.MessagePrefix.STRING_PREFIX;
import static java.lang.System.lineSeparator;

public class GeneratorMessage {
    public String generate(IntMessage[] messageBuffer){
        StringBuilder resultMessage = new StringBuilder();
        List<Long> result = new ArrayList<>();
        List<Long> copyBuffer = new ArrayList<>();
        for (IntMessage intMessage : messageBuffer) {
            copyBuffer.add((long) intMessage.getMessageField());
        }
        long num = 0L;
        for (Long aLong : copyBuffer) {
            if (aLong + num > Integer.MAX_VALUE) {
                result.add(num);
                num = aLong;
            } else {
                num += aLong;
            }
        }
        result.add(num);
        for (Long ans : result) {
            resultMessage.append(PRIMITIVE_PREFIX).append(ans).append(lineSeparator());
        }
        return resultMessage.toString();
    }

    public String generate(StringMessage[] messageBuffer){
        StringBuilder resultMessage=new StringBuilder();
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < messageBuffer.length; ) {
            String str = messageBuffer[i].getMessageField();
            int counter = 1;
            while ((i + counter < messageBuffer.length) && str.equals(messageBuffer[i + counter].getMessageField())) {
                counter++;
            }
            if (counter > 1) {
                answer.add(str + " (x" + counter + ")");
            } else {
                answer.add(str);
            }
            i += counter;
        }
        for (String str : answer) resultMessage.append(STRING_PREFIX).append(str).append(lineSeparator());
        return resultMessage.toString();
    }/*
    public String generate(List<ByteMessage> messageBuffer){

        return null;
    }*/
}
