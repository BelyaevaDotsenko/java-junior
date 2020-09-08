package com.acme.edu.saver;

import java.io.IOException;

public interface LoggerSaver {
    void save(String message) throws IOException;
}
