package com.acme.edu;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static final String PRIMITIVE_PREFIX = "primitive: ";
    private static final String STRING_PREFIX = "string: ";
    private static final String CHAR_PREFIX = "char: ";
    private static final String REFERENCE_PREFIX = "reference: ";
    private static final String PRIMITIVE_ARRAY_PREFIX = "primitives array: ";
    private static final String PRIMITIVE_MATRIX_PREFIX = "primitives matrix: ";

    private static List<Object> messageBuffer = new ArrayList<>();

    public static void log(int message) {
        messageBuffer.add(message);
    }

    public static void log(int[] message) {
        messageBuffer.add(message);
    }

    public static void log(int[][] message) {
        messageBuffer.add(message);
    }

    public static void log(byte message) {
        messageBuffer.add(message);
    }

    public static void log(String message) {
        messageBuffer.add(message);
    }

    public static void log(boolean message) {
        print(PRIMITIVE_PREFIX + message);
    }

    public static void log(char message) {
        print(CHAR_PREFIX + message);
    }

    public static void log(Object message) {
        print(REFERENCE_PREFIX + message);
    }

     // Logic with only String - buffer

    private static void printStrings() {
        List<String> answer = new ArrayList<>();
        int length = messageBuffer.size();
        for (int i = 0; i < length; ) {
            Object str = messageBuffer.get(i);
            int counter = 1;
            while ((i + counter < length) && str.equals(messageBuffer.get(i + counter))) {
                counter++;
            }
            if (counter > 1) {
                answer.add(str.toString() + " (x" + counter + ")");
            } else {
                answer.add(str.toString());
            }
            i += counter;
        }
        for (String str : answer) print(STRING_PREFIX + str);
    }

    private static void print(String str) {
        System.out.println(str);
    }

    private static void printBuffer() {
        if (messageBuffer.get(0) instanceof String) {
            printStrings();
        } else if (messageBuffer.get(0) instanceof Byte) {
            printBytes();
        } else if (messageBuffer.get(0) instanceof Integer) {
            printIntegers();
        } else if (messageBuffer.get(0) instanceof int[]) {
            printIntegerArray();
        } else if (messageBuffer.get(0) instanceof int[][]) {
            printIntegerMatrix();
        }
    }

    private static void printIntegerArray() {
        int[] ans = (int[]) messageBuffer.get(0);
        print(PRIMITIVE_ARRAY_PREFIX + printArray(ans));
    }

    private static String printArray(int[] ans) {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i : ans) {
            sb.append(i + ", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append('}');
        return sb.toString();
    }

    private static void printIntegerMatrix() {
        int[][] ans = (int[][]) messageBuffer.get(0);
        print(PRIMITIVE_MATRIX_PREFIX + "{");
        for (int i = 0; i < ans.length; i++) {
            print(printArray(ans[i]));
        }
        print("}");
    }

    private static void printIntegers() {
        printNumbers(Integer.MAX_VALUE, 0);
    }

    private static void printBytes() {
        printNumbers(Byte.MAX_VALUE, 1);
    }

    private static void printNumbers(int maxValue, int type) {
        List<Long> result = new ArrayList<>();
        List<Long> copyBuffer = new ArrayList<>();

        if (type == 0) {
            for (Object obj : messageBuffer) {
                copyBuffer.add(Long.valueOf((Integer) obj));
            }
        } else if (type == 1) {
            for (Object obj : messageBuffer) {
                copyBuffer.add(Long.valueOf((Byte) obj));
            }
        }

        long num = 0L;
        for (int i = 0; i < copyBuffer.size(); i++) {
            if (copyBuffer.get(i) + num > maxValue) {
                result.add(num);
                num = copyBuffer.get(i);
            } else {
                num += copyBuffer.get(i);
            }
        }
        result.add(num);
        for (Long ans : result) {
            print(PRIMITIVE_PREFIX + ans);
        }
    }


    // Print buffer

    public static void flush() {
        if (messageBuffer.size() == 0) return;
        printBuffer();
        messageBuffer = new ArrayList<>();
    }

    public static void clear() {
        messageBuffer.clear();
    }
}