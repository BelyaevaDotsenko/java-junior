package com.acme.edu.saver;

public class ConsoleLoggerSaver implements LoggerSaver{

    @Override
    public void save(String message){
        System.out.print(message);
    }
}
