package com.acme.edu.saver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLoggerSaver implements LoggerSaver {
    private String fileName;

    public FileLoggerSaver(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(String message) throws SaverExceptions, IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)){
            fileWriter.write(message);
        } catch(IOException e){
            throw e;
        }
    }
}
