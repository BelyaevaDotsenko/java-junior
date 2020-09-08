package com.acme.edu.saver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver implements LoggerSaver {
    private File file;

    public FileSaver(String fileName) {
        file = new File(fileName);
    }

    @Override
    public void save(String message) throws IOException {
        new FileWriter(file).write(message);
    }
}
