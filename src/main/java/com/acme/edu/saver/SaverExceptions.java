package com.acme.edu.saver;

public class SaverExceptions extends Throwable {
    public SaverExceptions() {
    }

    public SaverExceptions(String message) {
        super(message);
    }

    public SaverExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public SaverExceptions(Throwable cause) {
        super(cause);
    }

    public SaverExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
