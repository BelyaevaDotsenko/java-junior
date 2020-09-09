package com.acme.edu.saver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLoggerSaver implements LoggerSaver {
    private String fileName;

    public FileLoggerSaver(String fileName) {
        new File(fileName);
        this.fileName = fileName;
    }

    @Override
    public void save(String message) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(message);
        fileWriter.close();
    }
}
