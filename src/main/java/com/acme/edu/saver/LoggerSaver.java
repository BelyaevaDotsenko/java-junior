package com.acme.edu.saver;

import java.io.IOException;

public interface LoggerSaver extends AutoCloseable{
    void save(String message) throws IOException;
}
