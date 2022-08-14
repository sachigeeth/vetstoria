package com.operation.util;

public class NoRecordsFoundException extends RuntimeException{
    public NoRecordsFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public NoRecordsFoundException(String msg) {
        super(msg);
    }
}
