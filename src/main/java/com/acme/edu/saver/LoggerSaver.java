package com.acme.edu.saver;

import java.io.IOException;

@FunctionalInterface
public interface LoggerSaver {
    void save(String message) throws SaverExceptions, IOException;
}
