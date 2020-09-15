package com.acme.edu.saver;

import java.io.*;

public class FileLoggerSaver implements LoggerSaver {
    private BufferedWriter bw;

    public FileLoggerSaver(String fileName) {
        try {
            bw = new BufferedWriter(new OutputStreamWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(fileName)
                    )
            ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(String message) throws IOException {
        try {
            bw.write(message);
        } catch(IOException e){
            throw e;
        }
    }

    @Override
    public void close() throws Exception {
        bw.close();
    }
}
