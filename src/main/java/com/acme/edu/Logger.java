package com.acme.edu;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Logger {
    private static final String PRIMITIVE = "primitive: ";
    private static final String STRING = "string: ";
    private static final String CHAR = "char: ";
    private static final String REFERENCE = "reference: ";

    private static List<Object> messageBuffer = new ArrayList<>();
    //log.int(num)

    //if(lastInput instanceof Int) lastInput+=num;
    //else assert()
    //flush() sout(lastInput)


    // string: string1 + ... string_n
    // primitive: int1+... + int_n
    public static void log(int message) {
        messageBuffer.add(message);
    }

    public static void log(byte message) {
        print(PRIMITIVE + message);
    }

    public static void log(String message) {
        messageBuffer.add(message);
    }

    public static void log(boolean message) {
        print(PRIMITIVE + message);
    }

    public static void log(char message) {
        print(CHAR + message);
    }

    public static void log(Object message) {
        print(REFERENCE + message);
    }

    /**
     * Logic with only String - buffer
     */
    private static void printStrings() {
        final String[] lastString = {""};
        List<String> ans = new ArrayList<>();
        AtomicInteger cnt = new AtomicInteger();
        messageBuffer.add("");
        messageBuffer.forEach(str -> {
            if (str.equals(lastString[0])) {
                cnt.getAndIncrement();
            } else {
                if (cnt.get() != 0) {
                    ans.add(lastString[0] + getCnt(cnt));
                }
                cnt.lazySet(1);
                lastString[0] = String.valueOf(str);
            }
        });
        for (String str : ans) print(STRING + str);
    }

    private static void print(String str) {
        System.out.println(str);
    }

    private static void printBuffer() {
        if (messageBuffer.get(0) instanceof String) {
            printStrings();
        } else if (messageBuffer.get(0) instanceof Byte) {
            //printBytes();TODO
        } else if (messageBuffer.get(0) instanceof Integer) {
            printIntegers();
        }
    }

    private static void printIntegers() {
        List<Long> result = new ArrayList<>();
        List<Long> copyBuffer = new ArrayList<>();
        for(Object obj:messageBuffer){
            copyBuffer.add(Long.valueOf((Integer)obj));
        }
        long num=0L;
        for(int i=0;i<copyBuffer.size();i++){
            if(copyBuffer.get(i)+num>Integer.MAX_VALUE){
                result.add(num);
                num=copyBuffer.get(i);
            }
            else{
                num+=copyBuffer.get(i);
            }
        }
        result.add(num);
        for(Long ans: result){
            print(PRIMITIVE + ans);
        }
    }

    /**
     * One type array checker
     */
    private static boolean bufferHasOneType() {
        for (int i = 1; i < messageBuffer.size(); i++) {
            if (!messageBuffer.get(i).getClass().equals(messageBuffer.get(i - 1).getClass())) return false;
        }
        return true;
    }


    /**
     * Print buffer
     */
    public static void flush() {
        if (messageBuffer.size() == 0) return;
        assert bufferHasOneType();
        printBuffer();
        messageBuffer = new ArrayList<>();
    }

    private static String getCnt(AtomicInteger cnt) {
        if (cnt.get() == 1) {
            return "";
        }
        return " (x" + cnt.get() + ")";
    }
}